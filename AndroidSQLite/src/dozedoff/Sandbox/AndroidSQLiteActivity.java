package dozedoff.Sandbox;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.*;
import android.widget.TextView.OnEditorActionListener;

public class AndroidSQLiteActivity extends Activity {
	SQLiteDatabase db;
	EditText txtInput;
	TableLayout table;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SimpleDbHelper dbHelper = new SimpleDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        
        txtInput = (EditText)this.findViewById(R.id.txtInput);
		table = (TableLayout)this.findViewById(R.id.table);
		
		txtInput.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
					
					return true;
				}
				return false;
			}
		});
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}