package com.donay.climatempo5;

import android.content.Context;

import java.util.List;

public interface IapiCalback {

        void onApiFinished(PrevisaoAdapter adapter);
        Context getContext();
        void onPostExecute(List<previsaoModel> apiData);


}
