package com.rss;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RSS_TestActivity extends Activity {
    
    /*
     * @author 프리돔
     * @website http://onedays.co.kr 
     * 일단 안드로이드 메니페스트 파일에서 퍼미션을 추가해주세요.( 본프로젝트는 추가되어져 있습니다!)
     * 
     */
    private ArrayList<HashMap<String, String>> list_board = new ArrayList<HashMap<String, String>>();
    private HashMap<String, String> map;
    private SimpleAdapter sa;
    private ArrayList<String> contents;
    
    static final int PROGRESS_DIALOG = 0;
    
    private ListView lv;
    private TextView status;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        status = (TextView)findViewById(R.id.textView1);
        lv = (ListView)findViewById(R.id.listView1);

        sa = new SimpleAdapter(this, list_board, R.layout.row_board, new String[]{"subject", "date", "writer"},new int[]{R.id.board_subject, R.id.board_date, R.id.board_writer});
        lv.setAdapter(sa);
        
        contents = new ArrayList<String>();
        
        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                rssParse(((EditText)findViewById(R.id.editText1)).getText().toString());
            }
        });       
    }
    
  //데이터가지고 오기
    private void rssParse(String url)
    {
        list_board.clear();
        
        try{
            URL text = new URL(url);
            
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            
            parser.setInput(text.openStream(), null);
            
            status.setText("파싱중...");
            
            int parserEvent = parser.getEventType();
            String tag = "";
            
            boolean inTitle = false;    //제목여부판단
            boolean inItem = false;     //아이템변경 판단
            boolean inWriter = false;   //작성자 판단
            boolean inDate = false;     //작성일
            boolean inContent = false;  //내용
            
            //XML 날짜 형식 변환하기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
            Date pubdate = null;

            int count = 0;
            
            contents.clear();      //가지고 있던 콘텐츠를 모두 삭제함
            
            do{
                Log.i("test",""+count);
                switch(parserEvent)
                {
                    case XmlPullParser.TEXT:
                        tag = parser.getName();
                        //제목
                        if(inItem && inTitle)
                        {
                            map.put("subject", parser.getText());
                            
                        }
                        //작성일
                        if(inItem && inDate)
                        {
                            pubdate = new Date(Date.parse(parser.getText()));
                            map.put("date", sdf.format(pubdate));
                        }
                        //작성자
                        if(inItem && inWriter)
                        {
                            map.put("writer", parser.getText());
                        }
                        //내용
                        if(inItem && inContent)
                        {
                            //map.put("content", parser.getText());
                            contents.add( parser.getText() );
                        }
                        break;
                        
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        //태그가 끝나면
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
                        //만약 item으로 시작하는 태그라면
                        if(tag.compareTo("item") == 0)
                        {
                            inItem = true;
                            map = new HashMap<String, String>();
                        }
                        //만약 title 로 시작하는 태그라면
                        if(tag.compareTo("title") == 0)
                        {
                            inTitle = true;
                        }
                        //만약 dc:creator 이거나 author 태그라면
                        if(tag.compareTo("dc:creator") == 0 || tag.compareTo("author") == 0)
                        {
                            inWriter = true;
                        }
                        //만약 pubDate 태그라면
                        if(tag.compareTo("pubDate") == 0)
                        {
                            inDate = true;
                        }
                        //만약 description 태그라면
                        if(tag.compareTo("description") == 0)
                        {
                            inContent = true;
                        }
                        break;
                }
                
                status.setText("파싱중: " + String.valueOf(count));
                parserEvent = parser.next();
                count++;
                
            }while(parserEvent != XmlPullParser.END_DOCUMENT);
            
            status.setText("파싱 끝");
            sa.notifyDataSetChanged();
            
            
        }catch(Exception e){}
    }
}