package com.donay.climatempo5;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
            if (statusCode == 200) {
                StringBuilder xmlResponse = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        xmlResponse.append(line);
                    }
                }

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(xmlResponse.toString())));

                Element rootElement = document.getDocumentElement();
                NodeList nodeList = rootElement.getElementsByTagName("row");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element rowElement = (Element) nodeList.item(i);
                    StringBuilder rowData = new StringBuilder();

                    for (String column : columns) {
                        String columnData = rowElement.getElementsByTagName(column).item(0).getTextContent();
                        rowData.append(columnData).append(", ");
                    }

                    // Remove espaços em branco extras e adiciona os dados na lista
                    apiData.add(rowData.toString().trim());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Trate a exceção de URL mal formada adequadamente
        } catch (IOException e) {
            e.printStackTrace();
            // Trate a exceção de IO adequadamente
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            // Trate as exceções do parser do XML adequadamente
        }

        return apiData;
    }
    private int getResponseStatusCode() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        return connection.getResponseCode();
    }
    @Override
    protected void onPostExecute(List<String> apiData) {
        URL url = null;
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        return connection.getResponseCode();
    }

}
