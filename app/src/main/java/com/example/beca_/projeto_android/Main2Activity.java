package com.example.beca_.projeto_android;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    EditText edtMensagem;
    TextView txtChat;
    Usuario usuario1;
    UsuarioDAO usuarioDAO;
    MensagemDAO mensagemDAO;
    Banco banco;
    ListView listaMsgs;
    List<String> msgsBot;
    AdapterMensagensBot adapter;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtMensagem = (EditText) findViewById(R.id.edtMensagem);
        //txtChat = (TextView) findViewById(R.id.txtChat);

        listaMsgs = (ListView) findViewById(R.id.listview);
        msgsBot = new ArrayList<String>();
        msgsBot.add("Digite 'Oi' para começar a conversa");
        if(adapter == null)
            adapter = new AdapterMensagensBot(msgsBot, this);

        listaMsgs.setAdapter(adapter);

        banco = new Banco(this);

        /*File file = new File(banco.getWritableDatabase().getPath());
        SQLiteDatabase.deleteDatabase(file);*/
        usuarioDAO = new UsuarioDAO();
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        int id = usuarioDAO.insereUsuario(nome, banco);
        usuario1 = new Usuario(nome, id);


    }

    public void falar(View v){
        promptSpeechInput();
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                edtMensagem.getText());
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    edtMensagem.getText(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edtMensagem.setText(result.get(0));
                }
                break;
            }

        }
    }

    public void obterDados(View v){
        String mensagem = edtMensagem.getText().toString();

        mensagemDAO = new MensagemDAO();

        String novaMsg = mensagemDAO.insereMensagem(usuario1, banco, mensagem);

        msgsBot.add(usuario1.getNome()+ ": " + novaMsg);
        //adapter.updateLisTView(msgsBot);

        //Mensagem novaMsg = new Mensagem(usuario1.getId(), mensagem, mensagemId);

        //txtChat.setText(txtChat.getText() + "                                                           "+usuario1.getNome() + ": "+ novaMsg + "\n");
        pegaDadosTask a = new pegaDadosTask(this, adapter, msgsBot);
        a.execute(mensagem, adapter, usuario1);

        //msgsBot.add("UIHASIUDHASUDHAUDHAUDHAUIHASDUIHDSUIHSAD");
        adapter.updateLisTView(msgsBot);
        edtMensagem.setText("");

    }
}


