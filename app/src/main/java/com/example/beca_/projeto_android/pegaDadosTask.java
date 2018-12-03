package com.example.beca_.projeto_android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Adapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.List;

class pegaDadosTask extends AsyncTask<Object, Void, String>{

    private Activity context;
    private Dialog dialog;
    private AdapterMensagensBot adapter;
    private List<String> msgsBot;

    public pegaDadosTask(Activity context, AdapterMensagensBot adapterMsgBot, List<String> _msgsBot) {
        this.context = context;
        this.adapter = adapterMsgBot;
        this.msgsBot = _msgsBot;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(Object... params) {


        try {
            String mensagem = params[0].toString();

            JSONStringer js = new JSONStringer();
            String json = js.object().key("query").value(mensagem).endObject().toString();
            WebClient webClient = new WebClient();
            String resposta = webClient.post(json, (Usuario)params[2]);




            return resposta;

        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }



    }

    @Override
    protected void onPostExecute(String resposta) {
        try {
            JSONArray jsonArray = new JSONArray(resposta);

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String text = jsonObject.getString("text").toString();

            msgsBot.add("Robo: " + text);
            if(jsonObject.has("buttons")) {
                JSONArray jsonArray1 = jsonObject.getJSONArray("buttons");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    msgsBot.add(jsonArray1.getJSONObject(i).getString("title"));
                }
                adapter.updateLisTView(msgsBot);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<String> getMensgs(){
        return this.msgsBot;
    }
}
