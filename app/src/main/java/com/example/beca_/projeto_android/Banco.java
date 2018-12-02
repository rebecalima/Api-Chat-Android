package com.example.beca_.projeto_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    public Banco(Context context) {
        super(context, "Chat", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Usuario(cd_Usuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nm_Usuario VARCHAR(45) not null);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Mensagem(cd_Mensagem INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cd_Usuario int, ds_Mensagem TEXT, " +
                "FOREIGN KEY(cd_Usuario) REFERENCES Usuario(cd_Usuario));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuario {
    }
}
