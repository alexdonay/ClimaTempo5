package com.donay.climatempo5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.donay.climatempo5.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ListView listView;
    private LocalidadeDBHelper dbHelper;
    private LocalidadePrevisaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroLocalidadeActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        listView = findViewById(R.id.lvLocalidades);
        dbHelper = new LocalidadeDBHelper(this);
        Cursor dados = dbHelper.getAllLocalidades();
        LocalidadePrevisaoAdapter adapter = new LocalidadePrevisaoAdapter(this, dados);
        listView.setAdapter(adapter);
        if (dados != null && dados.moveToFirst()) {
            do {
                int id = dados.getInt(dados.getColumnIndexOrThrow("_id"));
                String nome = dados.getString(dados.getColumnIndexOrThrow("nome"));
                String uf = dados.getString(dados.getColumnIndexOrThrow("uf"));

                String mensagem = "ID: " + id + "\nNome: " + nome + "\nUF: " + uf;
                Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "Número de registros no Cursor: " + dados.getCount());
            } while (dados.moveToNext());
        }

// Certifique-se de fechar o Cursor após utilizá-lo
        if (dados != null) {
            dados.close();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, CadastroLocalidadeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}