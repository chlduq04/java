package com.ver1.culturemap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Front extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_front, menu);
        return true;
    }

    public void startApp(View v){
        Intent intent = new Intent(Front.this,MainActivity.class);
        startActivity(intent);
    }
}
