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

public class ApiDataFetcher extends AsyncTask<Void, Void, List<String>> {
    private String apiUrl;
    private List<String> columns;
    private ApiCallback callback;

    public ApiDataFetcher(String apiUrl, List<String> columns, ApiCallback callback) {
        this.apiUrl = apiUrl;
        this.columns = columns;
        this.callback = callback;
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        List<String> apiData = new ArrayList<>();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                NodeList nodeList = rootElement.getElementsByTagName("cidade");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element cidadeElement = (Element) nodeList.item(i);
                    StringBuilder rowData = new StringBuilder();

                    for (String column : columns) {
                        String columnData = cidadeElement.getElementsByTagName(column).item(0).getTextContent();
                        rowData.append(columnData).append(", ");
                    }

                    // Remove a vírgula extra no final e adiciona os dados na lista
                    apiData.add(rowData.substring(0, rowData.length() - 2));
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
    protected void onPostExecute(List<String> apiData) {
        callback.onApiFinished(String.valueOf(apiData));
    }
}
