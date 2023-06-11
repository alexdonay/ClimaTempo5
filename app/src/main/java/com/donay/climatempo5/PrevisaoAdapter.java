package com.donay.climatempo5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class PrevisaoAdapter extends ArrayAdapter<previsaoModel> {
    private Context context;
    private List<previsaoModel> previsoes;

    public PrevisaoAdapter(Context context, List<previsaoModel> previsoes) {
        super(context, 0, previsoes);
        this.context = context;
        this.previsoes = previsoes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_exibir_previsao, parent, false);
        }

        previsaoModel previsao = previsoes.get(position);
        String siglaPrevisao = previsao.getTempo();
        int imagemId = CondicaoClimatica.getImagemBySigla(siglaPrevisao);
        ImageView img = listItemView.findViewById(R.id.imageViewPrevisao);
        img.setImageResource(imagemId);
        TextView dataView = listItemView.findViewById(R.id.textViewData);
        TextView textViewTemperaturaMaxima = listItemView.findViewById(R.id.textViewTemperaturaMaxima);
        TextView textViewTemperaturaMinima = listItemView.findViewById(R.id.textViewTemperaturaMinima);
        TextView textViewIndiceUV = listItemView.findViewById(R.id.textViewIndiceUV);
        dataView.setText(previsao.getDia());
        textViewTemperaturaMaxima.setText(previsao.getMaxima());
        textViewTemperaturaMinima.setText(previsao.getMinima());
        textViewIndiceUV.setText(previsao.getIuv());

        return listItemView;
    }
}