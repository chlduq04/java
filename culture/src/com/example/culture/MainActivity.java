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
         //이까지 기본
        status = (TextView)findViewById(R.id.textView1);
        lv = (ListView)findViewById(R.id.listView1);

        //뷰와 리스트 id 검색후 할당
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

            boolean inItem = false;     //아이템변경 판단

            boolean inTitle = false;    //제목여부판단
            boolean inPlace= false;   //작성자 판단
            boolean inRealmName = false;     //작성일
            boolean inContent = false;  //내용
            boolean inUrl = false;  //내용
          
            int count = 0;
            do{
                Log.i("test",""+count);
                switch(parserEvent){
                case XmlPullParser.TEXT:
                    tag = parser.getName();
                    //제목
                    if(inItem && inTitle){
                        map.put("title", parser.getText());
                    }
                    //작성일
                    if(inItem && inPlace){
                        map.put("place", parser.getText());
                    }
                    //내용
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
                    //태그가 끝나면
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
                    //만약 item으로 시작하는 태그라면
                    if(tag.compareTo("perforList") == 0){
                        inItem = true;
                        map = new HashMap<String, String>();
                    }
                    //만약 title 로 시작하는 태그라면
                    if(tag.compareTo("title") == 0){
                        inTitle = true;
                    }
                    //만약 pubDate 태그라면
                    if(tag.compareTo("place") == 0){
                        inPlace = true;
                    }
                    //만약 description 태그라면
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
