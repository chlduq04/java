package com.example.ooyou;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FrontActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }
    public void startApp(View v){
        Intent intent = new Intent(FrontActivity.this,ManuActivity.class);
        startActivity(intent);
    }
    public void stopApp(View v){
        ActivityManager m = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        m.restartPackage(getPackageName());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_front, menu);
        return true;
    }
}
