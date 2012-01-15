package dozedoff.Sandbox;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class AndroidSQLiteActivity extends FragmentActivity {
	SQLiteDatabase db;
	EditText txtInput;
	SimpleCursorAdapter simpleCursorAd;

	final String TABLE_NAME = "test";
	final String[] COLUMN_TO_READ = {"simplestring"};
	final String[] COLUMNS_FOR_ADAPTER = {"_id","simplestring"};
	final String RAW_QUERY = "SELECT oid as _id, simplestring FROM "+TABLE_NAME;
	final int[] to = {R.id.rowid,R.id.simplestring};

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SimpleDbHelper dbHelper = new SimpleDbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        
        txtInput = (EditText)this.findViewById(R.id.txtInput);
		
		loadFromDb(db);
		
		txtInput.setOnEditorActionListener(new OnEditorActionListener() {

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
					if(db.insert(TABLE_NAME, "", addText(txtInput.getText().toString())) == -1){
						Toast toast = Toast.makeText(getApplicationContext(), "Database write failed", Toast.LENGTH_SHORT);
						toast.show();
					}
					refreshList(db);
					txtInput.setText("");
					return true;
				}
				return false;
			}
		});
    }
    
    @Override
    protected void onDestroy() {
    	if(db != null){
    		db.close();
    	}
    	super.onDestroy();
    }
    
    private ContentValues addText(String text){
    	ContentValues content = new ContentValues();
    	content.put("simplestring", text);
    	return content;
    }
    
    private void loadFromDb(SQLiteDatabase db){
    	Cursor cursor = db.rawQuery(RAW_QUERY, null);
    	    	
    	simpleCursorAd = new SimpleCursorAdapter(getApplicationContext(), R.layout.listlayout, cursor, COLUMNS_FOR_ADAPTER, to, SimpleCursorAdapter.NO_SELECTION);
    	FragmentManager fm = getSupportFragmentManager();
    	ListFragment frag = (ListFragment)fm.findFragmentById(R.id.list);
    	frag.setListAdapter(simpleCursorAd);
    }
    
    private void refreshList(SQLiteDatabase db){
    	Cursor cursor = db.rawQuery(RAW_QUERY, null);
    	simpleCursorAd.changeCursor(cursor);
    }
}