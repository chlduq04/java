package com.example.culture;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ArrayList<HashMap<String, String>> list_board = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> map;
    private SimpleAdapter sa;
    
    static final int PROGRESS_DIALOG = 0;
    private StringTokenizer tk;

    @Override
    public void onCreate(Bundle savedInstanceState){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //�̱��� �⺻
        status = (TextView)findViewById(R.id.textView1);
        lv = (ListView)findViewById(R.id.listView1);

        //��� ����Ʈ id �˻��� �Ҵ�
        sa = new SimpleAdapter(this, list_board, R.layout.borad, new String[]{"subject", "date", "content"},new int[]{R.id.board_subject, R.id.board_date, R.id.board_writer});
        lv.setAdapter(sa);
    }
    public class parser extends AsyncTask<String, String, String>{
        protected String doInBackground(String... params) {
            rssParse(((EditText)findViewById(R.id.editText1)).getText().toString());
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            sa.notifyDataSetChanged();
        }
    }
    private void rssParse(String url)
    {
        list_board.clear();
        try{
            URL text = new URL(url);
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(text.openStream(), null);

            int parserEvent = parser.getEventType();
            String tag = "";

            boolean inItem = false;     //�����ۺ��� �Ǵ�

            boolean inTitle = false;    //���񿩺��Ǵ�
            boolean inPlace= false;   //�ۼ��� �Ǵ�
            boolean inRealmName = false;     //�ۼ���
            boolean inContent = false;  //����
            boolean inUrl = false;  //����
          
            int count = 0;
            do{
                Log.i("test",""+count);
                switch(parserEvent){
                case XmlPullParser.TEXT:
                    tag = parser.getName();
                    //����
                    if(inItem && inTitle){
                        map.put("title", parser.getText());
                    }
                    //�ۼ���
                    if(inItem && inPlace){
                        map.put("place", parser.getText());
                    }
                    //����
                    if(inItem && inRealmName){
                        map.put("realmName", parser.getText());
                    }
                    if(inItem && inContent){
                        map.put("content", parser.getText());
                    }
                    if(inItem && inUrl){
                        map.put("url", parser.getText());
                    }
                    break;

                case XmlPullParser.END_TAG:
                    tag = parser.getName();
                    //�±װ� ������
                    if(tag.compareTo("perforInfo") == 0){
                        inItem = false;
                        list_board.add(map);
                    }
                    if(tag.compareTo("title") == 0){
                        inTitle = false;
                    }
                    if(tag.compareTo("place") == 0){
                        inPlace = false;
                    }
                    if(tag.compareTo("realmName") == 0){
                        inRealmName = false;
                    }
                    if(tag.compareTo("content") == 0){
                        inContent = false;
                    }
                    if(tag.compareTo("url") == 0){
                        inUrl = false;
                    }
                    break;

                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    //���� item���� �����ϴ� �±׶��
                    if(tag.compareTo("perforList") == 0){
                        inItem = true;
                        map = new HashMap<String, String>();
                    }
                    //���� title �� �����ϴ� �±׶��
                    if(tag.compareTo("title") == 0){
                        inTitle = true;
                    }
                    //���� pubDate �±׶��
                    if(tag.compareTo("place") == 0){
                        inPlace = true;
                    }
                    //���� description �±׶��
                    if(tag.compareTo("realmName") == 0){
                        inRealmName = true;
                    }
                    if(tag.compareTo("realmName") == 0){
                        inRealmName  = true;
                    }
                    if(tag.compareTo("url") == 0){
                        inUrl = true;
                    }
                    break;
                }
                parserEvent = parser.next();
                count++;
            }while(parserEvent != XmlPullParser.END_DOCUMENT);
        }catch(Exception e){}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
