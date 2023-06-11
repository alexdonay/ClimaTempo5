package com.donay.climatempo5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.donay.climatempo5.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private ListView listView;
    private LocalidadeDBHelper dbHelper;
    private LocalidadePrevisaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("Preferencias", MODE_PRIVATE);
        editor = sharedPreferences.edit();
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

        listView = (ListView)findViewById(R.id.lvLocalidades);
        dbHelper = new LocalidadeDBHelper(this);
        Cursor dados = dbHelper.getAllLocalidades();

        LocalidadePrevisaoAdapter adapter = new LocalidadePrevisaoAdapter(this, dados);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cursor localidadeSelecionada = (Cursor) adapterView.getItemAtPosition(position);
                String id1 = localidadeSelecionada.getString(localidadeSelecionada.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(MainActivity.this, LocalidadeActivity.class);
                intent.putExtra("localidade", id1);
                startActivity(intent);
            }
        });

        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item3dias = menu.findItem(R.id.dias4);
        item3dias.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                editor.putInt("numeroDias", 4);
                editor.apply();
                return true;
            }
        });

        MenuItem item7dias = menu.findItem(R.id.dias7);
        item7dias.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                editor.putInt("numeroDias", 7);
                editor.apply();
                return true;
            }
        });

        return true;
    }


}