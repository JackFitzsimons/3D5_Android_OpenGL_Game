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
	
	public int level = 1;
	
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
		this.levelUpdate();
		String temp= String.valueOf(activity.time.checkTime());
	    
	    if(temp.length()<=4)
	    	t.setText("Time: " + temp);
	    //Log.w("level", String.valueOf(level));
		square.moveSquare();
				}
		else {
			activity.scoreString = s.getText();
			activity.GOS.SceneSetUp();

		}
	}
	
	public void levelUpdate(){
		int NewLevel = level;
		if(score<10){
			NewLevel = 1;
		}
		else if(score < 110){
			NewLevel = 2;
		}
		else {
			NewLevel=3;
		}
		if(level!=NewLevel){
			level = NewLevel;
		}
	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			float X = pSceneTouchEvent.getX();
			float Y = pSceneTouchEvent.getY();
			long T = square.tapSquare(X, Y);
			
			if (T >= 0){
				if(level==1){
					score+=1;
					activity.time.AddTime(T);
					square.score_sprite(X, Y, T);
				}
				else if(level==2){
					score+=5;
					activity.time.AddTime(T/2);
					square.score_sprite(X, Y, (long)(T/2));
				}
				else{
					score+=10;
					activity.time.AddTime(T/3);
					square.score_sprite(X, Y, (long)(T/3));
				}
				s.setText(scoreText + score);
			}
		if(T!=-1)
			square.setRandomSquareOn();

		
		return true;
	}
	
	

		return false;
	
}
}

