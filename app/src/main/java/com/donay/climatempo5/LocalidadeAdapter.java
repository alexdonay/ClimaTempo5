package com.donay.climatempo5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



    public class LocalidadeAdapter extends ArrayAdapter<Localidade> {
        private Context context;
        private List<Localidade> localidades;

        public LocalidadeAdapter(Context context, List<Localidade> localidades) {
            super(context, 0, localidades);
            this.context = context;
            this.localidades = localidades;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_localidade, parent, false);
            }

            Localidade localidade = localidades.get(position);



            TextView ufTextView = listItemView.findViewById(R.id.ufTextView);
            TextView idTextView = listItemView.findViewById(R.id.idTextView);
            String textoUtf8 = localidade.getNome();
            byte[] utf8Bytes = textoUtf8.getBytes(StandardCharsets.UTF_8);
            String textoExibicao = new String(utf8Bytes, StandardCharsets.UTF_8);
            TextView nomeTextView = listItemView.findViewById(R.id.nomeTextView);
            nomeTextView.setText(textoExibicao);
           ufTextView.setText(localidade.getUf());
           idTextView.setText(localidade.getId());

            return listItemView;
        }
    }

