package com.example.beca_.projeto_android;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

class pegaDadosTask extends AsyncTask<Object, Void, String>{

    private Context context;
    private Dialog dialog;
    private TextView txtChat;

    public pegaDadosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        //dialog = ProgressDialog.show(context, "Aguarde...", "Obtendo dados...", true, true);

    }

    @Override
    protected String doInBackground(Object... params) {


        try {
            String mensagem = params[0].toString();
            txtChat = (TextView)params[1];
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
        //dialog.dismiss();


        try {
            JSONArray jsonArray = new JSONArray(resposta);

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String text = jsonObject.getString("text").toString();
            txtChat.setText(txtChat.getText().toString() + "Robo: " + text + "\n");

            if(jsonObject.has("buttons")) {
                JSONArray jsonArray1 = jsonObject.getJSONArray("buttons");

                for (int i = 0; i < jsonArray1.length(); i++) {
                    txtChat.setText(txtChat.getText() + jsonArray1.getJSONObject(i).getString("title") + "\n");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            txtChat.setText(e.toString());
        }

    }
}
