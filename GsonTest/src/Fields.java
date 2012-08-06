import java.util.StringTokenizer;

import com.google.gson.annotations.SerializedName;


public class Fields {

    @SerializedName("fields")
    public Object fields;
    private String content;
    private String addrX;
    private String addrY;
    private String title;
    private String addr;

    public void setAddr(String arg){
        addr = arg;
    }
    public void setTitle(String arg){
        title = arg;
    }
    public void setContent(String arg){
        content = arg;
    }
    public void setAddrX(String arg){
        addrX = arg;
    }
    public void setAddrY(String arg){
        addrY = arg;
    }
    public String getTitle()
    {
        return title;
    }
    public String getContent()
    {
        return content;
    }
    public String getAddrX()
    {
        return addrX;
    }
    public String getAddr()
    {
        return addr;
    }
    public String getAddrY()
    {
        return addrY;
    }

    //    public Fields()
    //    {
    //        StringTokenizer tk = new StringTokenizer(fields.toString(),"{,}",true);
    //        tk.nextElement();
    //    }

}
