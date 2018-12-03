package com.example.beca_.projeto_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void entrar(View v){
        EditText edtNome = findViewById(R.id.edtNome);
        String nome = edtNome.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("nome",nome);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
