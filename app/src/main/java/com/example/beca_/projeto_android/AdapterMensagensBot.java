package com.example.beca_.projeto_android;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterMensagensBot extends BaseAdapter
{
    List<String> mensagens;
    Activity context;

    public AdapterMensagensBot(List<String> _mensagens, Activity _context){
        this.mensagens = _mensagens;
        this.context= _context;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public String getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateLisTView(List<String> _novaMsgs)
    {
       this.mensagens = _novaMsgs;
       this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        String msg = mensagens.get(position);


        if(view == null)
        {
            view = context.getLayoutInflater().inflate(R.layout.mensagem, null, false);
        }

        TextView txt = view.findViewById(R.id.txtMsg);
        txt.setText(msg);

        return view;
    }
}
