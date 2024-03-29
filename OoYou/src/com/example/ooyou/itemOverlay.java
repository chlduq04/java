package com.example.ooyou;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class itemOverlay extends ItemizedOverlay {

    
    private Context mContext;
    private ArrayList<OverlayItem> mOverlays 
    = new ArrayList<OverlayItem>();

    public itemOverlay(Drawable defaultMarker, Context context) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
    }
    @Override
    protected OverlayItem createItem(int i) {
        return mOverlays.get(i);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return mOverlays.size();
    }

    public void addOverlay(OverlayItem overlay)
    {
        mOverlays.add(overlay);
        populate();
    }

    @Override
    protected boolean onTap(int index) {
        OverlayItem item = mOverlays.get(index);
        Toast.makeText
        (mContext, item.getSnippet(), Toast.LENGTH_LONG).show();
        return true;
    }

}
