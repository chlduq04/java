package org.codenamu.lulurara.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class d_parser {
	private ArrayList<HashMap<String, String>> list_board = new ArrayList<HashMap<String, String>>();
	private HashMap<String, String> map;

	public d_parser(String url)
	{
		list_board.clear();
		try{
			URL text = new URL(url);
			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			parser.setInput(text.openStream(), null);

			int parserEvent = parser.getEventType();
			String tag = "";

			boolean inItem = false;			//아이템변경 판단
			boolean inTitle = false;		//제목여부판단
			boolean inPlace= false;			//작성자 판단
			boolean inRealmName = false;	//작성일
			boolean inContent = false;		//내용
			boolean inUrl = false;			//내용
			boolean inSdate= false;			//내용
			boolean inEdate = false;		//내용
			boolean inPhone = false;		//내용
			boolean inAddr = false;			//내용
			boolean inImgURL = false;		//img


			do{
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
					if(inItem && inSdate){
						map.put("startDate", parser.getText());
					}
					if(inItem && inEdate){
						map.put("endDate", parser.getText());
					}
					if(inItem && inContent){
						map.put("contents1", parser.getText());
					}
					if(inItem && inUrl){
						map.put("url", parser.getText());
					}
					if(inItem && inPhone){
						map.put("phone", parser.getText());
					}
					if(inItem && inAddr){
						map.put("placeAddr", parser.getText());
					}
					if(inItem && inImgURL) {
						map.put("imgUrl", parser.getText());
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
					if(tag.compareTo("startDate") == 0){
						inSdate= false;
					}
					if(tag.compareTo("endDate") == 0){
						inEdate = false;
					}
					if(tag.compareTo("contents1") == 0){
						inContent = false;
					}
					if(tag.compareTo("url") == 0){
						inUrl = false;
                    }
					if(tag.compareTo("phone") == 0){
						inPhone = false;
                    }
					if(tag.compareTo("placeAddr") == 0){
						inAddr = false;
                    }
					if(tag.compareTo("imgUrl") == 0) {
						inImgURL = false;
					}
                    break;

                case XmlPullParser.START_TAG:
                    tag = parser.getName();
                    //만약 item으로 시작하는 태그라면
                    if(tag.compareTo("perforInfo") == 0){
                        inItem = true;
                        map = new HashMap<String, String>();
                    }
                    //만약 title 로 시작하는 태그라면
                    if(tag.compareTo("title") == 0){
                        inTitle = true;
                    }
                    if(tag.compareTo("startDate") == 0){
                        inSdate = true;
                    }
                    if(tag.compareTo("endDate") == 0){
                        inEdate = true;
                    }
                    //만약 pubDate 태그라면
                    if(tag.compareTo("place") == 0){
                        inPlace = true;
                    }
                    //만약 description 태그라면
                    if(tag.compareTo("realmName") == 0){
                        inRealmName = true;
                    }
                    if(tag.compareTo("contents1") == 0){
                        inContent  = true;
                    }
                    if(tag.compareTo("url") == 0){
                        inUrl = true;
                    }
                    if(tag.compareTo("phone") == 0){
                        inPhone = true;
                    }
                    if(tag.compareTo("placeAddr") == 0){
                    	inAddr = true;
                    }
                    if(tag.compareTo("imgUrl") == 0) {
                    	inImgURL = true;
                    }
                    break;
                }
                parserEvent = parser.next();
            }while(parserEvent != XmlPullParser.END_DOCUMENT);
        }catch(Exception e){}
    }
	
	public ArrayList<HashMap<String, String>> getListBoard(){
		ArrayList<HashMap<String, String>> a = list_board;
		return list_board;
	}
}