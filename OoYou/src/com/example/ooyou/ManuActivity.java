package com.example.ooyou;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ManuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manu);
    }
    public void goMap(View v){
        Intent intent = new Intent(ManuActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void goSearch(View v){
        Intent intent = new Intent(ManuActivity.this,SearchActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manu, menu);
        return true;
    }
}
