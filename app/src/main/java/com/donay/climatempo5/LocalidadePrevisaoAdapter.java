package com.donay.climatempo5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LocalidadePrevisaoAdapter extends CursorAdapter {
    public LocalidadePrevisaoAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_localidade, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nomeTextView = view.findViewById(android.R.id.text1);
        TextView ufTextView = view.findViewById(android.R.id.text2);

        String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        String uf = cursor.getString(cursor.getColumnIndexOrThrow("uf"));

        nomeTextView.setText(nome);
        ufTextView.setText(uf);
    }
}
