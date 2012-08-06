package com.and.m;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Overlay;

import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;

public class MainActivity extends MapActivity {
    private itemOverlay itemOverlay;
    private MapView mapView;
    private List<Overlay> mapOverlays;
    private Drawable drawMarker;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);  //줌컨트롤을 활성

        MapController mc = mapView.getController();
        mc.animateTo(new GeoPoint(37517180,127041268));
        mc.setZoom(15);
        mapOverlays = mapView.getOverlays();
        drawMarker = this.getResources().getDrawable(R.drawable.androidmarker);
        itemOverlay = new itemOverlay(drawMarker, this);


        OverlayItem item1 = new OverlayItem(new GeoPoint(37580716,127043176),"What args?", "Title");      
        itemOverlay.addOverlay(item1);
        mapOverlays.add(itemOverlay);                    

    }
    public void startApp(View v){
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
}
