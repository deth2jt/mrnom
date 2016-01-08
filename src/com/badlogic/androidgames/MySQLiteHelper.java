package com.badlogic.androidgames;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_CAL = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CAL = "comment";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;
	  
	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_CAL + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_CAL
	      + " text not null);";

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAL);
	    onCreate(db);
	  }

}
