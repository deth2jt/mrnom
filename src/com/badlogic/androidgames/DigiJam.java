package com.badlogic.androidgames;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DigiJam extends ListActivity 
{
	private EditText edittext, notEditText;
	private Button button;
	TextView textView;
	CalDataSource datasource;
	int num;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_list_item_1);
		textView = new TextView(this);
		
		edittext = (EditText) findViewById(R.id.editText);
		notEditText = (EditText) findViewById(R.id.notEditText);
		
		
	 
		button = (Button) findViewById(R.id.button_send);
	 
		
		datasource = new CalDataSource(this);
	    datasource.open();

	    List<Cal> values = datasource.getAllComments();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Cal> adapter = new ArrayAdapter<Cal>(this, android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	    
		//addKeyListener();
	}
	
	/*
	public void addKeyListener()
	{
		// get edittext component
		
     button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
             // Perform action on click
        	 String str = edittext.getText().toString();
        	 num = str.equals("") ? 0 :  Integer.parseInt(str);
        	 notEditText.setText("val is: " + (num * 3));
        	 textView.setText("val is: " + (num * 3));
         }
     });
	 
	}
	
	*/
	
	public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Cal> adapter = (ArrayAdapter<Cal>) getListAdapter();
	    Cal comment = null;
	    
	    switch (view.getId()) {
	    case R.id.button_send:
	    	String str = edittext.getText().toString();
       	 	num = str.equals("") ? 0 :  Integer.parseInt(str);
       	 	num = num * 3;
       	 	notEditText.setText("val is: " + num);
       	 	//textView.setText("val is: " + (num * 3));
       	 	break;
       	 	
       	 	
	    case R.id.add:
	      //String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
	      //int nextInt = new Random().nextInt(3);
	      // save the new comment to the database
	      //comment = datasource.createComment(comments[nextInt]);
	    	
	    	comment = datasource.createComment(num);	
	      adapter.add(comment);
	      break;
	      
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) { 
	        comment = (Cal) getListAdapter().getItem(0);
	        datasource.deleteComment(comment);
	        adapter.remove(comment);
	      }
	      
	      
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }


}
