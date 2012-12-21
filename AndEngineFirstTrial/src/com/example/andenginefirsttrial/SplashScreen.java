package com.example.andenginefirsttrial;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.view.MotionEvent;

// defining the splash screen class
public class SplashScreen extends Scene implements IOnSceneTouchListener{

	// ===========================================================
	// Global variables
	// ===========================================================
	
	MainActivity activity;
	Sprite SantaSleigh;
	Text Intro1;
	Text Intro2;
	

	// ===========================================================
	// Constructor
	// ===========================================================
	
	//not used for consistancy purposes
	public SplashScreen(){
		//default constructor
			}
	
	//method for setting or resetting Splashscreen and setting up the snow flakes
	public void SceneSetUp(){
	    MainActivity.getSharedInstance().setCurrentScene(this);

		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		activity = MainActivity.getSharedInstance();
		
		activity.mSnowSprite[0] = new SnowFlake(0f, 0f, activity.mSnowFlake1, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[1] = new SnowFlake(0f, 0f, activity.mSnowFlake2, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[2] = new SnowFlake(0f, 0f, activity.mSnowFlake3, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[3] = new SnowFlake(0f, 0f, activity.mSnowFlake4, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[4] = new SnowFlake(0f, 0f, activity.mSnowFlake5, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[5] = new SnowFlake(0f, 0f, activity.mSnowFlake6, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[6] = new SnowFlake(0f, 0f, activity.mSnowFlake7, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[7] = new SnowFlake(0f, 0f, activity.mSnowFlake1, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[8] = new SnowFlake(0f, 0f, activity.mSnowFlake2, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[9] = new SnowFlake(0f, 0f, activity.mSnowFlake3, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[10] = new SnowFlake(0f, 0f, activity.mSnowFlake4, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[11] = new SnowFlake(0f, 0f, activity.mSnowFlake5, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[12] = new SnowFlake(0f, 0f, activity.mSnowFlake6, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());
		activity.mSnowSprite[13] = new SnowFlake(0f, 0f, activity.mSnowFlake7, activity.getVertexBufferObjectManager(), activity.mCamera.getHeight(), activity.mCamera.getWidth());

		SantaSleigh = new Sprite(activity.mCamera.getWidth()/10, activity.mCamera.getHeight()/3,activity.mSantasSleigh, activity.getVertexBufferObjectManager());

		SantaSleigh.setScale(0.8f);
		
		Intro1 = new Text(activity.mCamera.getWidth()*2/3, activity.mCamera.getHeight()/10, activity.handWritingFontRED, "Help Santa", 50, activity.getVertexBufferObjectManager());
		Intro2 = new Text(activity.mCamera.getWidth()/2, activity.mCamera.getHeight()/10 + Intro1.getHeight() + 5, activity.handWritingFontWHITE, "protect the presents in danger", 50, activity.getVertexBufferObjectManager());
		
		for(int i=0; i<14;i++){
			activity.mSnowSprite[i].setScale();
			activity.mSnowSprite[i].setStartPosition();
		}
				
		this.attachChildren(activity.mSnowSprite);
		
		this.attachChild(SantaSleigh);
		
		this.attachChild(Intro1);
		this.attachChild(Intro2);

		setOnSceneTouchListener(this);
		
		registerUpdateHandler(new SnowFlakeUpdateHandler());
		

	}

	//when screen is touched
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			activity.MMS.SceneSetUp(); // go to main menu
		}
		return true;
	}
}
