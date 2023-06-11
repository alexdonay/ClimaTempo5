package com.donay.climatempo5;

public class previsaoModel {
    private String dia;
    private String tempo;
    private String maxima;
    private String minima;
    private String iuv;

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getMaxima() {
        return maxima;
    }

    public void setMaxima(String maxima) {
        this.maxima = maxima;
    }

    public String getMinima() {
        return minima;
    }

    public void setMinima(String minima) {
        this.minima = minima;
    }

    public String getIuv() {
        return iuv;
    }

    public void setIuv(String iuv) {
        this.iuv = iuv;
    }

    public previsaoModel(String dia, String tempo, String maxima, String minima, String iuv) {
        this.dia = dia;
        this.tempo = tempo;
        this.maxima = maxima;
        this.minima = minima;
        this.iuv = iuv;
    }
    public previsaoModel(){}
}
