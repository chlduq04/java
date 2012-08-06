package Android.Speech.OYCHOI;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AndroidSpeechProjectActivity extends Activity {
    private static final int request_code = 1234;
    private ListView mlist;
    private ListView mdebug;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button start_speak_bt = (Button)findViewById(R.id.btn_speak);
        mlist = (ListView)findViewById(R.id.list);
        
        //packagemanager은 어플리케이션의 정보를 알아낼 수 있다.
        PackageManager pm = getPackageManager();
        //queryintentactivities를 실행하여 인자값으로 원하는 프로그램을 찾는다
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        //ACTION_RECOGNIZE_SPEECH는 음성 인식을 위한 프로그램을 실행하는 것이다.
        if(activities.size()!=0)
            start_speak_bt.setOnClickListener(startBt);
        else
            start_speak_bt.setEnabled(false);
    }
    private OnClickListener startBt = new OnClickListener() {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v.getId()==R.id.btn_speak)
                startVoiceRecognization();
        }
    };
    private void startVoiceRecognization(){
        try{
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "free laguage model demo");
            startActivityForResult(intent, request_code);
        }
        catch(Exception e)
        {
            Toast.makeText(AndroidSpeechProjectActivity.this,"Not found", Toast.LENGTH_LONG).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_code && resultCode == RESULT_OK) {
         ArrayList<String> matches = data
           .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
         mlist.setAdapter(new ArrayAdapter<String>(this,
           android.R.layout.simple_list_item_1, matches));
        }
        super.onActivityResult(requestCode, resultCode, data);
       }
}