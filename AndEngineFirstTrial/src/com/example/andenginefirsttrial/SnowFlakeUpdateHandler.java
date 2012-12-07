package com.example.andenginefirsttrial;

import org.andengine.engine.handler.IUpdateHandler;

public class SnowFlakeUpdateHandler implements IUpdateHandler{
	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(MainActivity.getSharedInstance().mCurrentScene.getClass().equals(SplashScreen.class) || MainActivity.getSharedInstance().mCurrentScene.getClass().equals(MainMenuScene.class))
			for(int i=0;i<14;i++)
				MainActivity.getSharedInstance().mSnowSprite[i].move();
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
