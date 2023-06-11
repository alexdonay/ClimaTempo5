package com.donay.climatempo5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LocalidadeActivity extends AppCompatActivity implements IapiCalback{
    private ListView listView;
    LocalidadePrevisaoAdapter previsao;
    private IapiCalback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_localidade);
        SharedPreferences sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
        int numeroDias = sharedPreferences.getInt("numeroDias", 0);
        String nrDias = String.valueOf(numeroDias);
        Intent intent = getIntent();
        String localidadeSelecionada = intent.getStringExtra("localidade");
        String url = null;
        if(nrDias=="7"){
             url = ("http://servicos.cptec.inpe.br/XML/cidade/"+nrDias+"dias/" + localidadeSelecionada + "/previsao.xml");
        }else{
            url = ("http://servicos.cptec.inpe.br/XML/cidade/"+ localidadeSelecionada + "/previsao.xml");
        }



        ApiDataFetcher1 dataFetcher = new ApiDataFetcher1(url, LocalidadeActivity.this);
        dataFetcher.execute();


    }


    @Override
    public void onApiFinished(PrevisaoAdapter adapter) {
        ListView ls = findViewById(R.id.lsPrevisoesAC);
        ls.setAdapter(adapter);
    }

    @Override
    public void onPostExecute(List<previsaoModel> apiData) {

        PrevisaoAdapter adapter = new PrevisaoAdapter(LocalidadeActivity.this,apiData);
        ListView ls = findViewById(R.id.lsPrevisoesAC);
        ls.setAdapter(adapter);
        callback.onApiFinished(adapter);
    }
    @Override
    public Context getContext() {
        return this;
    }
}