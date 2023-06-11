package com.donay.climatempo5;

public class previsaoModel {
    private String dia;
    private String tempo;
    private String maxima;
    private String minima;
    private String iuv;

    public String getDia() {
        String ano = this.dia.substring(6, 10);
        String mes = this.dia.substring(11, 13);
        String dia1 = this.dia.substring(14, 16);

        String dataFormatada = dia1 + "/" + mes + "/" + ano;
        return "Dia: " + dataFormatada;
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
