package com.example.beca_.projeto_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MensagemDAO {
    private Context context;

    public MensagemDAO(){

    }

    public String insereMensagem(Usuario usuario, Banco banco, String mensagem){
        /*SQLiteDatabase db  = banco.getReadableDatabase();
        Cursor cursor1 = db.rawQuery("SELECT max(cd_Mensagem) FROM Mensagem WHERE cd_Usuario = "+usuario.getId()+";", null);
        int id = 0;
        if(cursor1 != null && cursor1.getCount() > 0){
            while(cursor1.moveToNext()){
                id = cursor1.getInt(cursor1.getColumnIndex("cd_Mensagem"));
            }
        }
        db.close();*/

        SQLiteDatabase dbInsert = banco.getWritableDatabase();
        dbInsert.execSQL("INSERT INTO Mensagem VALUES(null,"+usuario.getId()+", '"+mensagem+"')");
        dbInsert.close();

       // SQLiteDatabase dbSelect  = banco.getReadableDatabase();

        /*Cursor cursor2 = dbSelect.rawQuery("SELECT max(cd_Mensagem) FROM Mensagem;", null);
        int id = 0;
        while(cursor2.moveToNext()){
            id = cursor2.getColumnIndex("cd_Mensagem");
        }
        dbSelect.close();*/

        return mensagem;
    }
}
