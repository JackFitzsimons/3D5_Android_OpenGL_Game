package com.example.andenginefirsttrial;

import org.andengine.entity.modifier.MoveXModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;
import android.view.MotionEvent;


public class GameOverScene extends Scene implements IOnSceneTouchListener{
	MainActivity activity;
	Text title1;
	CharSequence title2= "asdfghjkl";
	
	float startTime, endTime;
	
	
	public GameOverScene() {
		activity = MainActivity.getSharedInstance();
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		title1 = new Text(0, 0, activity.mFont, "Hello", 30, activity.getVertexBufferObjectManager());
		attachChild(title1);

	}
	
	public void SceneSetUp() {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated method stub
		CharSequence title2 = activity.scoreString;
		title1.setText(title2);
				
		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);


		title1.registerEntityModifier(new MoveXModifier(2, title1.getX(), activity.mCamera.getWidth() / 2 - title1.getWidth()));
		
		activity.setCurrentScene(this);
		
		startTime = activity.time.checkTime();
		
		UpdateTopScores(activity.score);
		
		setOnSceneTouchListener(this);

	}
	
	void UpdateTopScores(int score){
		int a= activity.getScores(1);
		int b= activity.getScores(2);
		int c= activity.getScores(3);
		
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

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			endTime = activity.time.checkTime();
			if(startTime - endTime > 3)
				activity.MMS.SceneSetUp();
		}
		return true;
	}
}
