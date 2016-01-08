package com.badlogic.androidgames.framework.gl;

import com.badlogic.androidgames.framework.Game;

import android.util.Log;

public class FPSCounter 
{
	long startTime;
	int frames;
	
	public FPSCounter() 
	{
		startTime = System.nanoTime();
		frames = 0;
	}
	
	public void logFrame() 
	{
		frames++;
		
		if(System.nanoTime() - startTime >= 1000000000) 
		{
			Log.d("FPSCounter", "fps: " + frames + " System.nanoTime(): " + System.nanoTime());
			frames = 0;
			startTime = System.nanoTime();
		}
	}

}
