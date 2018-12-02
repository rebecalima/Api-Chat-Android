package com.example.beca_.projeto_android;

public class Mensagem {

    private int cdUsuario;
    private String mensagem;
    private int id;

    public Mensagem(){

    }

    public Mensagem(int cdUsuario, String mensagem, int id){
        this.cdUsuario = cdUsuario;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String textoMensagem) {
        this.mensagem = textoMensagem;
    }



    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public int getId() {
        return id;
    }
}

