import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class Paser {

    public static void main(String args[]){
        URL url  = null;
        StringBuffer url_contents = new StringBuffer();
        String input = JOptionPane.showInputDialog("url");
        String check_pasing[] = {"<",">","title",".*title.*",".*link.*",".*description.*",".*/description.*",".*item.*",".*creater.*"};
        String pasing[] = new String[5];
        try{
            url = new URL(input);
            InputStream in = url.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String output = "";
            boolean check = false;
            int first_i=0,last_i=0;
            while(true){
                String read = br.readLine();
                if(read == null) break;
                url_contents.append(read);
            }
            br.close();
            isr.close();
            in.close();
            System.out.print(url_contents);
        }
        catch(Exception e){
            System.err.print(e);
        }
    }
    public void check_des(String args)
    {}
    public void check_item()
    {}
    

}
/*
 *  if(read.charAt(i)==check_pasing[0].charAt(0)){
                        for(j=i;read.charAt(j)!=check_pasing[1].charAt(0);j++)
                        {
                            check+=read.charAt(j);
                        }
                        check = check.trim();

                        if(check.matches(check_pasing[2])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else if(check.matches(check_pasing[3])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else if(check.matches(check_pasing[4])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else if(check.matches(check_pasing[5])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else if(check.matches(check_pasing[6])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else if(check.matches(check_pasing[7])){
                            for(k=j;read.charAt(k)!=check_pasing[0].charAt(0);k++){
                                output+=read.charAt(k);  
                            }
                        }
                        else{
                            break;
                        }
                    }
 */
/*
public static void main(String args[])
{

    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // parse() 메소드에 들어갈 수 있는 인자 중, 아래처럼 URI String 도 있습니다.

        Document doc = builder.parse("http://sirini.net/blog/rss.php");

        NodeList rss = doc.getElementsByTagName("rss");
        NodeList channel = rss.item(0).getChildNodes();
        // _n 변수에 <channel> ~~~ </channel> 속 노드들 정보가 들어갑니다.
        NodeList _n = channel.item(0).getChildNodes();
        int countList = (int)_n.getLength();
        String list[] = new String[countList];
        for (int i=0; i<countList; i++) {
            // 예제에서는 RSS 피드중 상단 블로그 정보 부분만 콘솔로 출력해 봅니다.
            if(_n.item(i).getChildNodes().item(0).getFirstChild() != null) {
                break;
            }
            list[i] = _n.item(i).getTextContent();
            System.out.println("item["+i+"]: "+list[i]);
        }            
    } catch(Exception e) {
        System.out.println(e.toString());
    }
}*/
