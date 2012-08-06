
import java.io.BufferedReader;
import com.google.gson.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Connect_db {
    private String name;
    private String address;
    private String desc; 
    public Connect_db()
    {
        URL url;
        HttpURLConnection conn;
        try {
            url = new URL("http://192.168.0.60:8000/mobilemap");
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
                
                ArrayList<Fields> lst = new ArrayList<Fields>();
                for(JsonElement obj : jarray){
                    Fields s = g.fromJson(obj, Fields.class);
                    
                    StringTokenizer tk = new StringTokenizer(s.fields.toString(),"{,}",false);                   
                    s.setContent(tk.nextToken());
                    s.setAddrX(tk.nextToken());
                    s.setAddrY(tk.nextToken());
                    s.setAddr(tk.nextToken());
                    s.setTitle(tk.nextToken());
                    lst.add(s);
                }
                System.out.println(lst.get(0).fields);
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
    }
}