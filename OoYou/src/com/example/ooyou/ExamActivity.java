package com.example.ooyou;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ExamActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_exam, menu);
        return true;
    }
}
