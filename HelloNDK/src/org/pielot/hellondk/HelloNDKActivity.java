package org.pielot.hellondk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloNDKActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        System.load("hellondk");
        int result = sayHello();
        Log.i("HelloNDK","" + result);
    }
    private native int sayHello();
}