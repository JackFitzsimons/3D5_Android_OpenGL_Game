package com.example.andenginefirsttrial;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;
import android.view.MotionEvent;



public class GameScene extends Scene implements IOnSceneTouchListener{
	public Square square;
	public int score;
	public double timeRemaining;
	
	Text s, t;
	
	Camera mCamera;
	Text score_text;
	

	
	public String scoreText = "Score: ";
	
	MainActivity activity;
			
	public GameScene() {
		// TODO Auto-generated 
		activity = MainActivity.getSharedInstance();	
		
		s = new Text(0, 0, activity.mFont, "Score:0123456789", 20, activity.getVertexBufferObjectManager());
		t = new Text(0, 0, activity.mFont, "Time:00", 10, activity.getVertexBufferObjectManager());
	    setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
	    mCamera = MainActivity.getSharedInstance().mCamera;
	    score=0;
	    activity.time = new TimerActivity();
	    
	    square = Square.getSharedInstance();
	    
	    attachChildren(square.sprite);
	    
	    attachChildren(square.sprite_score);
	    
	    
	    s.setText(scoreText + 0);
	    s.setPosition(20f, 20f);
	    attachChildren(s);
	    
	    float X = mCamera.getWidth() - 50 - t.getWidth();
	    
	    String temp= String.valueOf(activity.time.checkTime());
	    
	    if(temp.length()<=4)
	    	t.setText("Time: " + temp);
	    
	    t.setPosition(X, 20f);
	    attachChildren(t);

	}
	
	public void SceneSetUp(){
		setOnSceneTouchListener(this);
		activity = MainActivity.getSharedInstance();
	    score=0;
	    activity.time = new TimerActivity();
	    
	    square = Square.getSharedInstance();
	    
		Log.w("start","gamescene");
	    
	    s.setText(scoreText + 0);
	    s.setPosition(20f, 20f);
	    
	    float X = mCamera.getWidth() - 20 - t.getWidth();
	    
	    String temp= String.valueOf(activity.time.checkTime());
	    
	    if(temp.length()<=4)
	    	t.setText("Time: " + temp);
	    
	    t.setPosition(X, 20f);
	    	    
	    MainActivity.getSharedInstance().setCurrentScene(this);

	    registerUpdateHandler(new GameLoopUpdateHandler());
		
	}


	public void upDateScreen(){
		if (activity.time.checkTime()>0)
		{
		
		String temp= String.valueOf(activity.time.checkTime());
	    
	    if(temp.length()<=4)
	    	t.setText("Time: " + temp);
	    
		square.moveSquare();
				}
		else {
			activity.scoreString = s.getText();
			activity.GOS.SceneSetUp();

		}
	}
	

	@Override
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			float X = pSceneTouchEvent.getX();
			float Y = pSceneTouchEvent.getY();
			long T = square.tapSquare(X, Y);
			
			if (T != 0){
				square.score_sprite(X, Y, T);
				score+=10;
				s.setText(scoreText + score);
				activity.time.AddTime(T);

			}
		if(T!=-1)
			square.setRandomSquareOn();

		
		return true;
	}
	
	

		return false;
	
}
}

