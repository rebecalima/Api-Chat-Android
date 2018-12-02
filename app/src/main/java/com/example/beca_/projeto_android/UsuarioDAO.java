package com.example.beca_.projeto_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDAO {
    private Context context;

    public UsuarioDAO(){

    }

    public int insereUsuario(String usuario, Banco banco){
        SQLiteDatabase dbInsert = banco.getWritableDatabase();

        dbInsert.execSQL("INSERT INTO Usuario(cd_Usuario, nm_Usuario) VALUES(null, '"+usuario+"')");
        dbInsert.close();

        SQLiteDatabase dbSelect  = banco.getReadableDatabase();

        Cursor cursor = dbSelect.rawQuery("SELECT cd_Usuario FROM Usuario;", null);
        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("cd_Usuario"));
        }
        dbSelect.close();

        return id;
    }
}
