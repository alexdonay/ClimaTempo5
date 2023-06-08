package com.donay.climatempo5;

import android.content.Context;

import java.util.List;

public interface ApiCallback {
    void onApiFinished(LocalidadeAdapter adapter);
    Context getContext();
    void onPostExecute(List<Localidade> apiData);
}
