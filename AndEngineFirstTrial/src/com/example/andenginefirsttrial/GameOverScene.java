package com.example.andenginefirsttrial;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.view.MotionEvent;

// ===========================================================
// Gives users their final score and returns to to main menu on touch
// ===========================================================

public class GameOverScene extends Scene implements IOnSceneTouchListener{
	

	// ===========================================================
    // Global Variables
    // ===========================================================
	
	MainActivity activity;
	Text title1;
	CharSequence title2= "asdfghjkl";
	
	float startTime, endTime;
	

	// ===========================================================
    // Constructor
    // ===========================================================
	
	public GameOverScene() {
		activity = MainActivity.getSharedInstance();
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		title1 = new Text(0, 0, activity.mFont, "Hello", 30, activity.getVertexBufferObjectManager()); //initialise a random text element to be over written
		attachChild(title1);

	}
	
	// ===========================================================
    // Methods
    // ===========================================================
	
	// Called when we refresh the scene ie when ever we want to use it
	public void SceneSetUp() {
		CharSequence title2 = activity.scoreString;	//end score from the game just played
		title1.setText(title2);	
				
		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);


		title1.registerEntityModifier(new MoveXModifier(2, title1.getX(), activity.mCamera.getWidth() / 2 - title1.getWidth()));
		
		activity.setCurrentScene(this);	//make this scene the visible scene
		
		startTime = activity.time.checkTime();	//time scene appears on screen
		
		UpdateTopScores(activity.score);	
		
		setOnSceneTouchListener(this);

	}
	
	//if score is in the top 3 then it updates the top 3 in saved memory
	void UpdateTopScores(int score){
		
		int a= activity.getScores(1);	//top score
		int b= activity.getScores(2);	//second place
		int c= activity.getScores(3);	//third
		
		if(score>a){
			activity.setScores(1, score);
			activity.setScores(2, a);
			activity.setScores(3, b);
		}
		else if(score>b){
			activity.setScores(2, score);
			activity.setScores(3, b);
		}
		else if(score>c){
			activity.setScores(3, score);
		}
		
	}

	//call when the screen is touched, if touch after 3 seconds returns to main menu
	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			
			endTime = activity.time.checkTime(); //now
			
			if(startTime - endTime > 3)	//if screen has been visible for three seconds
				activity.MMS.SceneSetUp(); //go to the main menu scene
		}
		return true;
	}
}
