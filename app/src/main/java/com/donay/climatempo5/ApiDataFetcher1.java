package com.donay.climatempo5;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ApiDataFetcher1 extends AsyncTask<Void, Void, List<previsaoModel>> {
    private String apiUrl;
    private IapiCalback callback;

    public ApiDataFetcher1(String apiUrl, IapiCalback callback) {
        this.apiUrl = apiUrl;
        this.callback = callback;
    }

    protected List<previsaoModel> doInBackground(Void... voids) {
        List<previsaoModel> apiData = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

                StringBuilder xmlResponse = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    xmlResponse.append(line);
                }
                reader.close();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                InputSource inputSource = new InputSource(new StringReader(xmlResponse.toString()));
                Document document = builder.parse(inputSource);

                Element rootElement = document.getDocumentElement();
                NodeList nodeList = rootElement.getElementsByTagName("previsao");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element cidadeElement = (Element) nodeList.item(i);

                    String dia = "Data: " + cidadeElement.getElementsByTagName("dia").item(0).getTextContent();
                    String tempo = cidadeElement.getElementsByTagName("tempo").item(0).getTextContent();
                    String maxima = "Maxima: " + cidadeElement.getElementsByTagName("maxima").item(0).getTextContent();
                    String minima = "Minima: " + cidadeElement.getElementsByTagName("minima").item(0).getTextContent();
                    String iuv = "IUV: " + cidadeElement.getElementsByTagName("iuv").item(0).getTextContent();

                   previsaoModel previsao = new previsaoModel(dia,tempo,maxima,minima,iuv);
                    apiData.add(previsao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return apiData;
    }

    @Override
    protected void onPostExecute(List<previsaoModel> apiData) {
        PrevisaoAdapter adapter = new PrevisaoAdapter(callback.getContext(), apiData);
        callback.onApiFinished(adapter);
    }

}
