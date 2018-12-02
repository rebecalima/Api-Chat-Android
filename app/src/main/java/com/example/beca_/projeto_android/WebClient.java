package com.example.beca_.projeto_android;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    public String post(String json, Usuario usuario){
        try{
            String idUsuario = usuario.getNome() + usuario.getId();
            URL url = new URL("http://35.198.0.237:5005/conversations/"+idUsuario+"/respond");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("context-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");


            //http://35.198.0.237:5005/
            //"http://www.webservicex.net:500/Prices?ticker=BASMNYARA189N"
            //https://pokeapi.co/api/v2/pokemon/1/


            connection.setDoOutput(true);
            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.nextLine();
            return resposta;
        }catch(MalformedURLException e){
            e.printStackTrace();
            return e.toString();
        }
        catch (IOException e){
            e.printStackTrace();
            return e.toString();
        }

    }
}
