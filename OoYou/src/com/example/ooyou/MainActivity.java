package com.example.ooyou;

import java.util.ArrayList;
import java.util.List;

import com.example.ooyou.ConnectDB;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import com.google.android.maps.MyLocationOverlay;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;

public class MainActivity extends MapActivity {
    private itemOverlay itemOverlay;
    private MapView mapView;
    private List<Overlay> mapOverlays;
    private Drawable drawMarker;
    private MyLocationOverlay me = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public void loadMarker(View v){
        
        
        ConnectDB con = new ConnectDB();      
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);  //줌컨트롤을 활성

        MapController mc = mapView.getController();
        mc.animateTo(new GeoPoint(37517180,127041268));
        mc.setZoom(15);
        ArrayList<Inner> lst = con.Connect_DB("http://192.168.0.60:8000/mobile/requestloc");
                
        mapOverlays= mapView.getOverlays();
        drawMarker = this.getResources().getDrawable(R.drawable.base);
        itemOverlay = new itemOverlay(drawMarker, this);
        for (int i=0;i<lst.size();i++){
           OverlayItem item1 = new OverlayItem(new GeoPoint(lst.get(i).pickLocation("Addr_X"),lst.get(i).pickLocation("Addr_Y")),lst.get(i).pickString("Comments"),lst.get(i).pickString("Title"));  
            itemOverlay.addOverlay(item1);
            mapOverlays.add(itemOverlay);                    
        }
        
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
}
