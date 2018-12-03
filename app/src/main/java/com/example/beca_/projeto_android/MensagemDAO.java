package com.example.beca_.projeto_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO {
    private Context context;

    public MensagemDAO(){

    }

    public String insereMensagem(Usuario usuario, Banco banco, String mensagem){

        SQLiteDatabase dbInsert = banco.getWritableDatabase();
        dbInsert.execSQL("INSERT INTO Mensagem VALUES(null,"+usuario.getId()+", '"+mensagem+"')");
        dbInsert.close();

        return mensagem;
    }
}
