
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
                System.out.print(strjson);
                
                Gson
                
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