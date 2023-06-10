package com.donay.climatempo5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class LocalidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localidade);
        SharedPreferences sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
        int numeroDias = sharedPreferences.getInt("numeroDias", 0);
        String nrDias = String.valueOf(numeroDias);
        Intent intent = getIntent();
        String localidadeSelecionada = intent.getStringExtra("localidade");
        TextView nomeTextView = (TextView) findViewById(R.id.nomeTextView);
        nomeTextView.setText(localidadeSelecionada + nrDias);
    }

}