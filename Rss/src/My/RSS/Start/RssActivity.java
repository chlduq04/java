package My.RSS.Start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
public class RssActivity extends Activity{

    private ArrayList<HashMap<String, String>> list_board = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> map;
    private SimpleAdapter sa;
    private ArrayList<String> contents;
    //private ArrayList<String> rss_list;

    static final int PROGRESS_DIALOG = 0;
    private ListView lv;
    private TextView status;
    private static final int MSG_PASSING =1;
    
    /** Called when the activity is first created. **/
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {  
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         //�̱��� �⺻
        status = (TextView)findViewById(R.id.textView1);
        lv = (ListView)findViewById(R.id.listView1);
  
        //��� ����Ʈ id �˻��� �Ҵ�
        sa = new SimpleAdapter(this, list_board, R.layout.borad, new String[]{"subject", "date", "writer"},new int[]{R.id.board_subject, R.id.board_date, R.id.board_writer});
        lv.setAdapter(sa);

 
        
        contents = new ArrayList<String>();
        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Thread bt1 = new Thread(bt1T);
                bt1.start();
                // TODO Auto-generated method stub
                vibe.vibrate(400);
            }
        });
        ((Button)findViewById(R.id.List_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }
    Runnable bt1T = new Runnable() {
        public void run() {            
            mhHandler.sendEmptyMessage(1);
           // listView(((EditText)findViewById(R.id.editText1)).getText().toString());       
        }
    };
    
    Handler mhHandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what)
            {
            case 1:
                rssParse(((EditText)findViewById(R.id.editText1)).getText().toString());
                break;
            }
        }
    };
    /*
    private void listView(String url)
    {
        String date;
        rss_list_board.clear();
        list_map.put("list",url);
        date = String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
        list_map.put("date",date);
        list.notifyDataSetChanged();
        //rss_list.add(((EditText)findViewById(R.id.editText1)).getText().toString());
    }*/
    //�����Ͱ����� ����
    private void rssParse(String url)
    {
        list_board.clear();

        try{
            URL text = new URL(url);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(text.openStream(), null);

            status.setText("�Ľ���...");

            int parserEvent = parser.getEventType();
            String tag = "";

            boolean inTitle = false;    //���񿩺��Ǵ�
            boolean inItem = false;     //�����ۺ��� �Ǵ�
            boolean inWriter = false;   //�ۼ��� �Ǵ�
            boolean inDate = false;     //�ۼ���
            boolean inContent = false;  //����

            //XML ��¥ ���� ��ȯ�ϱ�
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm�� ss��");
            Date pubdate = null;

            int count = 0;

            contents.clear();      //������ �ִ� �������� ��� ������

            do{
                Log.i("test",""+count);
                switch(parserEvent)
                {
                case XmlPullParser.TEXT:
                    tag = parser.getName();
                    //����
                    if(inItem && inTitle)
                    {
                        map.put("subject", parser.getText());
                    }
                    //�ۼ���
                    if(inItem && inDate)
                    {
                        pubdate = new Date(Date.parse(parser.getText()));
                        map.put("date", sdf.format(pubdate));
                    }
                    //�ۼ���
                    if(inItem && inWriter)
                    {
                        map.put("writer", parser.getText());
                    }
                    //����
                    if(inItem && inContent)
                    {
                        //map.put("content", parser.getText());
                        contents.add( parser.getText() );
                    }
                    break;

                case XmlPullParser.END_TAG:
                    tag = parser.getName();
                    //�±װ� ������
                    if(tag.compareTo("item") == 0)
                    {
                        inItem = false;
                        list_board.add(map);
                    }
                    if(tag.compareTo("title") == 0)
                    {
                        inTitle = false;
                    }
                    if(tag.compareTo("dc:creator") == 0 || tag.compareTo("author") == 0)
                    {
                        inWriter = false;
                    }
                    if(tag.compareTo("pubDate") == 0)
                    {
                        inDate = false;
                    }
                    if(tag.compareTo("description") == 0)
                    {
                        inContent = false;
                    }
                    break;

                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    //���� item���� �����ϴ� �±׶��
                    if(tag.compareTo("item") == 0)
                    {
                        inItem = true;
                        map = new HashMap<String, String>();
                    }
                    //���� title �� �����ϴ� �±׶��
                    if(tag.compareTo("title") == 0)
                    {
                        inTitle = true;
                    }
                    //���� dc:creator �̰ų� author �±׶��
                    if(tag.compareTo("dc:creator") == 0 || tag.compareTo("author") == 0)
                    {
                        inWriter = true;
                    }
                    //���� pubDate �±׶��
                    if(tag.compareTo("pubDate") == 0)
                    {
                        inDate = true;
                    }
                    //���� description �±׶��
                    if(tag.compareTo("description") == 0)
                    {
                        inContent = true;
                    }
                    break;
                }

                status.setText("�Ľ���: " + String.valueOf(count));
                parserEvent = parser.next();
                count++;
            }while(parserEvent != XmlPullParser.END_DOCUMENT);

            status.setText("�Ľ� ��");
            sa.notifyDataSetChanged();

        }catch(Exception e){}
    }
}