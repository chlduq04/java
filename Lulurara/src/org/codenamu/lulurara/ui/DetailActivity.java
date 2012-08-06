package org.codenamu.lulurara.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.codenamu.lulurara.R;
import org.codenamu.lulurara.data.ImageDownloader;
import org.codenamu.lulurara.data.d_parser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	Context context;
	Intent intent;
	
	TextView tvTitle;		// 공연 제목
	TextView tvSDate;		// 공연 시작일
	TextView tvEDate;		// 공연 종료일
	TextView tvPlace;		// 공연 장소
	TextView tvAddr;		// 공연장 주소
	TextView tvTelNum;		// 공연장 전번
	TextView tvContent;		// 공연 내용
	ImageView tvThumbnail;		// 이미지
	
	d_parser detail;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		
		context = this;
		intent  = getIntent();

		initLayout();
	}
	
	public void initLayout(){
		tvTitle   = (TextView)findViewById(R.id.tv_title);
		tvSDate   = (TextView)findViewById(R.id.tv_sdate);
		tvEDate   = (TextView)findViewById(R.id.tv_edate);
		tvPlace   = (TextView)findViewById(R.id.tv_place);
		tvAddr    = (TextView)findViewById(R.id.tv_addr);
		tvTelNum  = (TextView)findViewById(R.id.tv_tel);
		tvContent = (TextView)findViewById(R.id.tv_content);
		tvThumbnail = (ImageView)findViewById(R.id.thumnbnail);
		
		detail = new d_parser("http://www.culture.go.kr/openapi/rest/publicperformancedisplays/d/?seq=" + intent.getStringExtra("SEQ") + "&serviceKey=D1yyEu8AyO6FzCZ%2BaxokkVM8tpT6im7WFNB84Aewz9jXN5YkIA9txn1HRGx%2B3pZCidW3%2BNnRrbT1vc5QWFImxA%3D%3D");
		ArrayList<HashMap<String, String>> list = detail.getListBoard();

		Log.e(this.getClass().getSimpleName(), "[list data] =======> " + list);
		ImageDownloader.download(list.get(0).get("imgUrl"), tvThumbnail);
		tvTitle.setText(list.get(0).get("title"));
		tvSDate.setText(list.get(0).get("startDate"));
		tvEDate.setText(list.get(0).get("endDate"));
		tvPlace.setText(list.get(0).get("place"));
		tvAddr.setText(list.get(0).get("placeAddr"));
		tvTelNum.setText(list.get(0).get("phone"));
		tvContent.setText(Html.fromHtml(list.get(0).get("contents1")));
	}
}