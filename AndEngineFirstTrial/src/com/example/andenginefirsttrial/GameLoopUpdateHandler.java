package com.example.andenginefirsttrial;

import org.andengine.engine.handler.IUpdateHandler;


// ===========================================================
// Updates the GameScene Class each frame
// ===========================================================
public class GameLoopUpdateHandler implements IUpdateHandler{

	@Override
	public void onUpdate(float pSecondsElapsed) {
		if(MainActivity.getSharedInstance().mCurrentScene.getClass().equals(GameScene.class))
			((GameScene)MainActivity.getSharedInstance().mCurrentScene).upDateScreen();
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}