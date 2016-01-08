package com.badlogic.androidgames;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CalDataSource 
{
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_CAL };

	  public CalDataSource(Context context) 
	  {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Cal createComment(int cal) {
	    ContentValues values = new ContentValues();
	    
	    values.put(MySQLiteHelper.COLUMN_CAL, cal);
	    long insertId = database.insert(MySQLiteHelper.TABLE_CAL, null, values);
	    
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_CAL, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
	    
	    cursor.moveToFirst();
	    //Comment newComment = cursorToComment(cursor);
	    Cal val_cal = cursorToComment(cursor);
	    
	    cursor.close();
	    return val_cal;
	  }

	  public void deleteComment(Cal val) {
	    long id = val.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_CAL, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Cal> getAllComments() {
	    List<Cal> comments = new ArrayList<Cal>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_CAL,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Cal comment = cursorToComment(cursor);
	      comments.add(comment);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return comments;
	  }

	  private Cal cursorToComment(Cursor cursor) {
	    Cal val = new Cal();
	    val.setId(cursor.getLong(0));
	    val.setVal(Integer.parseInt(cursor.getString(1)));
	    return val;
	  }

}
