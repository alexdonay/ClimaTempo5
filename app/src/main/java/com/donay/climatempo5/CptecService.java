package com.donay.climatempo5;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CptecService {
    private static final String API_URL = "http://servicos.cptec.inpe.br/XML/listaCidades?city=";

    public interface OnLocalidadesListener {
        void onLocalidadesReceived(List<Localidade> localidades);
        void onError(String errorMessage);
    }

    public static void buscarLocalidades(String nomeCidade, OnLocalidadesListener listener) throws MalformedURLException {
        URL url = new URL(API_URL + nomeCidade);;

        new BuscarLocalidadesTask(listener).execute(String.valueOf(url));
    }

    private static class BuscarLocalidadesTask extends AsyncTask<String, Void, List<Localidade>> {
        private OnLocalidadesListener listener;

        public BuscarLocalidadesTask(OnLocalidadesListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<Localidade> doInBackground(String... urls) {
            List<Localidade> localidades = new ArrayList<>();

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(inputStream, null);

                    int eventType = parser.getEventType();
                    Localidade localidade = null;

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String tagName = parser.getName();

                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                if (tagName.equalsIgnoreCase("cidade")) {
                                    localidade = new Localidade();
                                }
                                break;

                            case XmlPullParser.TEXT:
                                if (localidade != null) {
                                    if (tagName.equalsIgnoreCase("nome")) {
                                        localidade.setNome(parser.getText());
                                    } else if (tagName.equalsIgnoreCase("uf")) {
                                        localidade.setUf(parser.getText());
                                    } else if (tagName.equalsIgnoreCase("id")) {
                                        localidade.setId(parser.getText());
                                    }
                                }
                                break;

                            case XmlPullParser.END_TAG:
                                if (tagName.equalsIgnoreCase("cidade") && localidade != null) {
                                    localidades.add(localidade);
                                    localidade = null;
                                }
                                break;
                        }

                        eventType = parser.next();
                    }
                } else {
                    throw new Exception("HTTP response code: " + responseCode);
                }

                connection.disconnect();
            } catch (Exception e) {
                Log.e("LocalidadeService", "Error fetching localidades", e);
                listener.onError("Error fetching localidades");
            }

            return localidades;
        }

        @Override
        protected void onPostExecute(List<Localidade> localidades) {
            if (localidades.isEmpty()) {
                listener.onError("No localidades found");
            } else {
                listener.onLocalidadesReceived(localidades);
            }
        }
    }
}

