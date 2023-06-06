package com.donay.climatempo5;

public class Localidade {
    private String nome;
    private String uf;
    private String id;
    public Localidade(String nome, String uf, String id){
        this.nome = nome;
        this.uf = uf;
        this.id = id;

    }


    public Localidade(){}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Cidade " + this.nome + " UF " + this.uf;
    }

    public String getUF() {
        return this.uf;
    }

    public String getID() {
        return this.id;
    }
}
