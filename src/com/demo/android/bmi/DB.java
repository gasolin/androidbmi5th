package com.demo.android.bmi;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private Context mContext = null;
	private DatabaseHelper dbHelper ;
	private SQLiteDatabase db;

	/** Constructor */
	public DB(Context context) {
	    this.mContext = context;
	}

	public DB open () throws SQLException {
	    dbHelper = new DatabaseHelper(mContext);
	    db = dbHelper.getWritableDatabase();
	    return this;
	}

	public void close() {
	    dbHelper.close();
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String DATABASE_NAME = "history.db";
	    private static final int DATABASE_VERSION = 1;
	    
	    private static final String DATABASE_TABLE = "history";
	    
	    private static final String DATABASE_CREATE =
	    	    "CREATE TABLE history("
	    	        +"_id INTEGER PRIMARY KEY,"
	    	        +"item TEXT NOT NULL,"
	    	        +"created TIMESTAMP"
	    	    +");";
	    
//		public DatabaseHelper(Context context, String name,
//				CursorFactory factory, int version) {
	    public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
		}

    }
	

	//CRUD
	public Cursor getAll() {
	    return db.rawQuery("SELECT * FROM history", null);
	}

}
