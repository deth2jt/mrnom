 package com.badlogic.androidgames;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.os.Bundle;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener 
{

	StringBuilder builder = new StringBuilder();
	TextView textView;
	float[] x = new float[10];
	float[] y = new float[10];
	boolean[] touched = new boolean[10];
	int[] id = new int[10];

	private void updateTextView() 
	{
		builder.setLength(0);
		for (int i = 0; i < 10; i++) 
		{
			builder.append(touched[i]);
			builder.append(", ");
			builder.append(id[i]);
			builder.append(", ");
			builder.append(x[i]);
			builder.append(", ");
			builder.append(y[i]);
			builder.append("\n");
		}
		
		textView.setText(builder.toString());
	}

	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("Touch and drag (multiple fingers supported)!");
		textView.setOnTouchListener(this);
		setContentView(textView);
		
		for (int i = 0; i < 10; i++) 
		{
			id[i] = -1;
		}
		updateTextView();
	}

		
	//@SuppressWarnings("deprecation")
	@Override
	public boolean onTouch(View view, MotionEvent event) 
	{
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		//int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int pointerCount = event.getPointerCount();
		
		/*
		try 
		{
			textView.setText("pointerIndex: " + pointerIndex);
		}
		
		catch (Exception e) 
		{
			textView.setText("ERROR");
		}
		*/
		
		
		for (int i = 0; i < 10; i++) 
		{
			
			//textView.setText("event.getPointerId(i): ") ;
			
			if (i >= pointerCount) 
			{
				touched[i] = false;
				id[i] = -1;
				continue;
			}
			
			if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) 
			{
	
				continue;
			}
		
			int pointerId = event.getPointerId(i);
			
			switch (action) 
			{
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
					touched[i] = true;
					id[i] = pointerId;
					x[i] = (int) event.getX(i);
					y[i] = (int) event.getY(i);
					break;
				
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				case MotionEvent.ACTION_OUTSIDE:
				case MotionEvent.ACTION_CANCEL:
					touched[i] = false;
					id[i] = -1;
					x[i] = (int) event.getX(i);
					y[i] = (int) event.getY(i);
					break;
				case MotionEvent.ACTION_MOVE:
					touched[i] = true;
					id[i] = pointerId;
					x[i] = (int) event.getX(i);
					y[i] = (int) event.getY(i);
					break;
			}
		}
		
		updateTextView();
		
		addToScreen(pointerIndex, pointerCount);
		return true;
	}

	

	public void addToScreen(int x, int y) 
	{
		builder.append("pointerIndex: " + x);
		builder.append("\n");
		builder.append("pointerCount: " + y);
		//builder.append("\n");
		textView.setText(builder.toString());
	}

}


