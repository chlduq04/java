package com.first.google_m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import android.R.array;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import com.first.google_m.R.drawable;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Overlay;
import com.google.gson.*;
import com.google.gson.internal.StringMap;
public class MainActivity extends MapActivity {
    private itemOverlay itemOverlay;
    private MapView mapView;
    private List<Overlay> mapOverlays;
    private Drawable drawMarker;
    private String name;
    private String address;
    private String desc; 
    private ArrayList<Inner> lst;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ConnectDB con = new ConnectDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);  //줌컨트롤을 활성

        MapController mc = mapView.getController();
        mc.animateTo(new GeoPoint(37517180,127041268));
        mc.setZoom(15);

        lst = con.ConnectDB("http://192.168.0.60:8000/mobile/requestloc");

       
        mapOverlays= mapView.getOverlays();
        drawMarker = this.getResources().getDrawable(R.drawable.base);
        itemOverlay = new itemOverlay(drawMarker, this);
        int a = (int)((Double.parseDouble(lst.get(1).getField().get("Addr_X")))*1e6);
        for (int i=0;i<lst.size();i++){
           OverlayItem item1 = new OverlayItem(new GeoPoint(lst.get(i).pickLocation("Addr_X"),lst.get(i).pickLocation("Addr_Y")),lst.get(i).pickString("Content"),lst.get(i).pickString("Title"));  
            itemOverlay.addOverlay(item1);
            mapOverlays.add(itemOverlay);                    
        }
        /*
             OverlayItem item1 = new OverlayItem(new GeoPoint(37580716,127043176),"What args?", "Title");      
             itemOverlay.addOverlay(item1);
             mapOverlays.add(itemOverlay);                    
         */
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
