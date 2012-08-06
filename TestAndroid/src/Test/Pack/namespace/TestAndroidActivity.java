package Test.Pack.namespace;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TestAndroidActivity extends Activity{
    /** Called when the activity is first created. */
    ArrayAdapter<String> adapter ;
 
    
    private Runnable run_code = new Runnable() {
        public void run() {
           
        }
    };

    private OnItemClickListener item_listener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {
            TextView select_item = (TextView)view;
            TextView tv = (TextView)findViewById(R.id.editTextrss);
            tv.setText(select_item.getText());

        }
    };
    private OnClickListener button_click = new OnClickListener() {
        public void onClick(View v) {
            int list_count = adapter.getCount();
            EditText edit = (EditText)findViewById(R.id.editTextrss);
            adapter.insert(edit.getText().toString(), list_count);
            ListView list = (ListView)findViewById(R.id.parsinglist);
            list.smoothScrollToPosition(list_count);
            edit.setText("");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<String> list_string = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.listdetail,list_string); 

        ListView list = (ListView)findViewById(R.id.parsinglist);
        Button btn = (Button)findViewById(R.id.RSSparsingbutton);

        list.setAdapter(adapter);
        list.setOnItemClickListener(item_listener);
        btn.setOnClickListener(button_click);
 
    }
}

