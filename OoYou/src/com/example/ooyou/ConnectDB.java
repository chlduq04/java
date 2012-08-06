package com.example.ooyou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import android.view.Menu;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ConnectDB {
    private ArrayList<Inner> lst;
    
    public ArrayList Connect_DB(String w_url)
    {
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL(w_url);
            conn = (HttpURLConnection)url.openConnection();
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){

                InputStream input = conn.getInputStream();
                Reader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);
                String strjson ="";
                String read = null;
                while((read=br.readLine())!=null){strjson+=read;};

                Gson g = new Gson();
                JsonParser parser = new JsonParser();
                JsonArray jarray = parser.parse(strjson).getAsJsonArray();   
                lst = new ArrayList<Inner>();
                for(JsonElement obj : jarray){
                    Inner s = g.fromJson(obj, Inner.class);
                    lst.add(s);
                }
                input.close();
                reader.close();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lst;
    }
}
class Inner {
    public Map<String,String> fields;

    public Map<String,String> getField()
    {return fields;}
    
    public String pickString(String key){return fields.get(key);}
    
    public int pickLocation(String key){
        return (int)(Double.parseDouble(fields.get(key))*1e6);
    }
}
