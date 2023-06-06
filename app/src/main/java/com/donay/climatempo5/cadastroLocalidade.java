package com.donay.climatempo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class cadastroLocalidade extends AppCompatActivity implements ApiCallback {

    private static final String API_URL = "http://servicos.cptec.inpe.br/XML/listaCidades?city=sao%20paulo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_localidade);

        Button button = findViewById(R.id.btnPesquisaLocalidade);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    List<String> columns = new ArrayList<>();
                    columns.add("nome");
                    columns.add("uf");
                    columns.add("id");

                    ApiDataFetcher dataFetcher = new ApiDataFetcher(API_URL, columns, cadastroLocalidade.this);
                    dataFetcher.execute();
            }
        });
    }


    @Override
    public void onApiFinished(String response) {
        // Aqui você pode lidar com a resposta da API
        Toast.makeText(this, "Resposta da API: " + response, Toast.LENGTH_SHORT).show();
    }
}
