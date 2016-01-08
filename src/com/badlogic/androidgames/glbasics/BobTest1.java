package com.badlogic.androidgames.glbasics;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.gl.FPSCounter;
import com.badlogic.androidgames.framework.gl.Texture;
import com.badlogic.androidgames.framework.gl.Vertices1;
import com.badlogic.androidgames.framework.impl.GLGame;

import com.badlogic.androidgames.framework.impl.GLGraphics;

public class BobTest1 extends GLGame 
{
	@Override
	public Screen getStartScreen() 
	{
		return new BobScreen(this);
	}

	class BobScreen extends Screen 
	{
		static final int NUM_BOBS = 100;
		GLGraphics glGraphics;
		Texture bobTexture;
		Vertices1 fbobModel;
		Bob[] bobs;
		FPSCounter fpsCounter;
		int interval;
		
		public BobScreen(Game game) 
		{
			super(game);
			
			fpsCounter = new FPSCounter();
			interval = 0;
			
			
			glGraphics = ((GLGame)game).getGLGraphics();
			
			bobTexture = new Texture((GLGame)game, "bobrgb888.png");
			fbobModel = new Vertices1(glGraphics, 4, 12, false, true);
			
			fbobModel.setVertices(new float[] { -16, -16, 0, 1,
								16, -16, 1, 1,
								16, 16, 1, 0,
								-16, 16, 0, 0, }, 0, 16);
			fbobModel.setIndices(new short[] {0, 1, 2, 2, 3, 0}, 0, 6);
			bobs = new Bob[100];

			for(int i = 0; i < 100; i++) 
			{
				bobs[i] = new Bob();
			}
		}


		@Override
		public void update(float deltaTime) 
		{
			game.getInput().getTouchEvents();
			game.getInput().getKeyEvents();
			
			for(int i = 0; i < NUM_BOBS; i++) 
			{
				bobs[i].update(deltaTime);
			}
		}

		
		@Override
		public void present(float deltaTime) 
		{
			interval += deltaTime;
			
			GL10 gl = glGraphics.getGL();
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

			gl.glMatrixMode(GL10.GL_MODELVIEW);
			
			fbobModel.bind();
			for(int i = 0; i < NUM_BOBS; i++) 
			{
				gl.glLoadIdentity();
				gl.glTranslatef(bobs[i].x, bobs[i].y, 0);
				fbobModel.draw(GL10.GL_TRIANGLES, 0, 6);
			}
			
			fbobModel.unbind();
			if(interval - (30 * (6 * 10 ^ 14)) < 0  )
			{
				//fpsCounter.logFrame();
				interval = 0;
			}	

		}

		@Override
		public void pause() 
		{
		}

		@Override
		public void resume() 
		{
		}

		@Override
		public void dispose() 
		{
		}
	}
}	