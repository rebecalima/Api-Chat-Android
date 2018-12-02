package com.example.beca_.projeto_android;

public class Usuario {
    private String nome;
    private int id;

    public Usuario(){

    }

    public Usuario(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId(){
        return id;
    }
}

