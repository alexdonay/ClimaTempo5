package com.donay.climatempo5;

import android.content.Context;
import android.content.Intent;
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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

        listView = (ListView)findViewById(R.id.lvLocalidades);
        dbHelper = new LocalidadeDBHelper(this);
        Cursor dados = dbHelper.getAllLocalidades();
        CursorAdapter adapter = new CursorAdapter(this, dados, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                // Cria uma nova view para um item da lista
                LayoutInflater inflater = LayoutInflater.from(context);
                return inflater.inflate(R.layout.list_item_localidade, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                // Obtém as referências das views do item da lista
                TextView nomeTextView = view.findViewById(R.id.nomeTextView);
                TextView ufTextView = view.findViewById(R.id.ufTextView);


                // Obtém os dados do cursor
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                String uf = cursor.getString(cursor.getColumnIndexOrThrow("uf"));


                // Define os dados nas views
                nomeTextView.setText(nome);
                ufTextView.setText(uf);

            }
        };

        listView.setAdapter(adapter);



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