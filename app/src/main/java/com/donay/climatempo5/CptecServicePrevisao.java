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

public class CptecServicePrevisao {
    private static final String API_URL = "http://servicos.cptec.inpe.br/XML/cidade/";

    public interface OnPrevisaoListener {
        void onPrevisaoReceived(List<previsaoModel> previsoes);
        void onError(String errorMessage);
    }

    public static void buscarprevisao(String idLocalidade, int qtdeDias, OnPrevisaoListener listener) throws MalformedURLException {
        URL url = new URL(API_URL +qtdeDias +"dias/" + idLocalidade + "/previsao.xml");;

        new BuscarPrevisoesTask(listener).execute(String.valueOf(url));
    }

    private static class BuscarPrevisoesTask extends AsyncTask<String, Void, List<previsaoModel>> {
        private OnPrevisaoListener listener;

        public BuscarPrevisoesTask(OnPrevisaoListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<previsaoModel> doInBackground(String... urls) {
            List<previsaoModel> previsoes = new ArrayList<>();

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
                    previsaoModel previsao = null;

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String tagName = parser.getName();

                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                if (tagName.equalsIgnoreCase("cidade")) {
                                    previsao = new previsaoModel();
                                }
                                break;

                            case XmlPullParser.TEXT:
                                if (previsao != null) {
                                    if (tagName.equalsIgnoreCase("dia")) {
                                        previsao.setDia(parser.getText());
                                    } else if (tagName.equalsIgnoreCase("iuv")) {
                                        previsao.setIuv(parser.getText());
                                    } else if (tagName.equalsIgnoreCase("minima")) {
                                        previsao.setMinima(parser.getText());

                                    } else if (tagName.equalsIgnoreCase("maxima")) {
                                        previsao.setMaxima(parser.getText());
                                    } else if (tagName.equalsIgnoreCase("tempo")) {
                                        previsao.setTempo(parser.getText());
                                    }
                                }
                                break;

                            case XmlPullParser.END_TAG:
                                if (tagName.equalsIgnoreCase("previsao") && previsao != null) {
                                    previsoes.add(previsao);
                                    previsao = null;
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
                Log.e("previsao", "Error fetching previsoe", e);
                listener.onError("Error fetching previsoes");
            }

            return previsoes;
        }

        @Override
        protected void onPostExecute(List<previsaoModel> previsoes) {
            if (previsoes.isEmpty()) {
                listener.onError("No previsoes found");
            } else {
                listener.onPrevisaoReceived(previsoes);
            }
        }
    }
}

