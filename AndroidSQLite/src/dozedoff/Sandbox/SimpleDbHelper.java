package dozedoff.Sandbox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "test";
    private static final String TEST_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( simplestring TEXT);";


	public SimpleDbHelper(Context context){
		super(context, TABLE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TEST_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
