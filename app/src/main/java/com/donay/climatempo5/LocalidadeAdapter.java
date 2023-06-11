package com.donay.climatempo5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

            TextView nomeTextView = listItemView.findViewById(R.id.nomeTextView);
            TextView ufTextView = listItemView.findViewById(R.id.ufTextView);
            TextView idTextView = listItemView.findViewById(R.id.idTextView);

           nomeTextView.setText(localidade.getNome());
           ufTextView.setText(localidade.getUf());
           idTextView.setText(localidade.getId());

            return listItemView;
        }
    }

