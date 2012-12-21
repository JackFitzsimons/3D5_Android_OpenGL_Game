package com.example.andenginefirsttrial;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

public class TopScoreScene extends Scene implements IOnSceneTouchListener{
	
	Text W1, W2, W3, tapSceneToExit;
	float w, h;
	MainActivity activity;

	//no constructor needed
	TopScoreScene(){}
	
	//gets the top scores and presents them on screen
	void SceneSetUp(){
		activity = MainActivity.getSharedInstance();
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		attachChildren(activity.mSnowSprite);
		
		registerUpdateHandler(new SnowFlakeUpdateHandler()); // keep snow falling
		
		w = activity.mCamera.getWidth();
		h = activity.mCamera.getHeight();

		int i =activity.getScores(1);
		if(i==-1) i = 0;
		W1 = new Text(0, 0, activity.mFont, "First Place :   " + String.valueOf(i), activity.getVertexBufferObjectManager());
		
		i =activity.getScores(2);
		if(i==-1) i = 0;
		W2 = new Text(0, 0, activity.mFont, "Second Place :   " + String.valueOf(i), activity.getVertexBufferObjectManager());
		
		i =activity.getScores(3);
		if(i==-1) i = 0;
		W3 = new Text(0, 0, activity.mFont, "Third Place :   " + String.valueOf(i), activity.getVertexBufferObjectManager());

		//put top 3 scores on screen
		W1.setPosition(activity.mCamera.getWidth() / 2 - W1.getWidth() / 2, activity.mCamera.getHeight() / 2 - W1.getHeight() / 2);
		W2.setPosition(activity.mCamera.getWidth() / 2 - W2.getWidth() / 2, W1.getY() + 50 - W2.getHeight() / 2);
		W3.setPosition(activity.mCamera.getWidth() / 2 - W3.getWidth() / 2, W2.getY() + 50 - W3.getHeight() / 2);
		
		attachChild(W1);
		attachChild(W2);
		attachChild(W3);
		
		//put instructions on screen
		tapSceneToExit = new Text(0, 0, activity.mFont, "Tap the screen to return to Main Menu", activity.getVertexBufferObjectManager());
		tapSceneToExit.setPosition(activity.mCamera.getWidth() / 2 - tapSceneToExit.getWidth() / 2, activity.mCamera.getHeight()/4);
		attachChild(tapSceneToExit);
		
		MainActivity.getSharedInstance().setCurrentScene(this);
		setOnSceneTouchListener(this);
		
	}

	//if touched returns to the main screen
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		this.detachChildren();
		activity.MMS.SceneSetUp();
		return true;
	}

}
