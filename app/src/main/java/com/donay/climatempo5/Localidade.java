package com.donay.climatempo5;

public class Localidade {
    private int _id;
    private String nome;
    private String uf;
    private String id;

    public Localidade(String nome, String uf, String id){
        this.nome = nome;
        this.uf = uf;
        this.id = id;
    }

    public Localidade(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

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
}