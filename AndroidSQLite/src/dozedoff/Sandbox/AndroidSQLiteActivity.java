package dozedoff.Sandbox;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.*;
import android.widget.TextView.OnEditorActionListener;

public class AndroidSQLiteActivity extends Activity {
	SQLiteDatabase db;
	EditText txtInput;
	TableLayout table;
	final String TABLE_NAME = "test";
	final String[] TABLE_TO_READ = {"simplestring"};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SimpleDbHelper dbHelper = new SimpleDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        
        txtInput = (EditText)this.findViewById(R.id.txtInput);
		table = (TableLayout)this.findViewById(R.id.table);
		
		loadFromDb(db);
		
		txtInput.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
					TextView newtext = new TextView(getApplicationContext());
					newtext.setText(txtInput.getText());
					table.addView(newtext);
					
					if(db.insert(TABLE_NAME, "", addText(txtInput.getText().toString())) == -1){
						Toast toast = Toast.makeText(getApplicationContext(), "Database write failed", Toast.LENGTH_SHORT);
						toast.show();
					}
					
					txtInput.setText("");
					return true;
				}
				return false;
			}
		});
    }
    
    private ContentValues addText(String text){
    	ContentValues content = new ContentValues();
    	content.put("simplestring", text);
    	return content;
    }
    
    private void loadFromDb(SQLiteDatabase db){
    	Cursor cursor = db.query(TABLE_NAME, TABLE_TO_READ, null, null, null, null, null);
    	
    	while(cursor.moveToNext()){
    		TextView newtext = new TextView(getApplicationContext());
			newtext.setText(cursor.getString(0));
			table.addView(newtext);
    	}
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}