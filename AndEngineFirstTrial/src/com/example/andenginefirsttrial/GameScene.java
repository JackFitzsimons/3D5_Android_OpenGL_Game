package com.example.andenginefirsttrial;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;
import android.view.MotionEvent;



//===========================================================
// Controlles the main game activity calling the presents to move, scores to change time to be incremented etc
//===========================================================
public class GameScene extends Scene implements IOnSceneTouchListener{
	
	// ===========================================================
	// Global variables
	// ===========================================================
	
	public Square square;	//controlled the presents on screen
	public int score;	//incriments on tapping presents
	public double timeRemaining;	
	private Sprite header;	//the white snowy banner at the top of the screen
	
	public int level = 1;	//which of the 3 levels are you on
	
	Text s, t;	//score and time respectively
	
	Camera mCamera;	//view of the device
	Text score_text;	
	
	public String scoreText = "Score: ";	//String version of the score_text Text object
	
	MainActivity activity;
			
	// ===========================================================
	// Constructor - things declared here that can be kept in local memory through game play and can be re-initialised each time game is played
	// ===========================================================
			
	public GameScene() {
		// TODO Auto-generated 
		activity = MainActivity.getSharedInstance();	
		
		 setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		 mCamera = MainActivity.getSharedInstance().mCamera;
				 
		//put the snowy header on the screen
		header = new Sprite(0,0, activity.mGamePlayHeader, activity.getVertexBufferObjectManager());
		header.setScale(mCamera.getWidth()/header.getWidth());
		attachChild(header);
		
		
		s = new Text(0, 0, activity.mFont, "Score:0123456789", 20, activity.getVertexBufferObjectManager());
		t = new Text(0, 0, activity.mFont, "Time:15", 10, activity.getVertexBufferObjectManager());
	   
	    score=0;
	    square = Square.getSharedInstance();
	    
	    attachChildren(square.sprite);
	    
	    attachChildren(square.sprite_score);
	    
	    
	    s.setText(scoreText + 0);
	    s.setPosition(20f, 20f);
	    attachChildren(s);
	    
	    float X = mCamera.getWidth() - 60 - t.getWidth();
	    
	    String temp= String.valueOf(activity.time.checkTime());
	    
	    if(temp.length()<=4)
	    	t.setText("Time: " + temp);
	    
	    t.setPosition(X, 20f);
	    attachChildren(t);

	}
			
	// ===========================================================
	// Methods
	// ===========================================================
	
	//called when new game is played and instead of redeclaring everything we simply initialise what is already there
	public void SceneSetUp(){
		setOnSceneTouchListener(this);
		activity = MainActivity.getSharedInstance();
		
		 activity.time.initial(15);
		
	    score=0;
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

	//Check if game is still being played (time!=0) and updates screen / goes to game over
	public void upDateScreen(){
		//if still playing
		if (activity.time.checkTime()>0)
		{
			this.levelUpdate(); //update level if necessary
			
			String temp= String.valueOf(activity.time.checkTime());
		    
			//put time on screen (provided it fits)
		    if(temp.length()<=4)
		    	t.setText("Time: " + temp);

		    //change presents to next position for next frame
		    square.moveSquare();
		}
		
		//if game over
		else {
			activity.score=score;
			activity.scoreString = s.getText();
			activity.GOS.SceneSetUp();
		}
	}

	//check if score if high enough to move up a level
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

	//called when screen is touched
	@Override
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		
		//only if touched downward to avoid multiple touches
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
			float X = pSceneTouchEvent.getX(); //get coordinates of the touch
			float Y = pSceneTouchEvent.getY();
			long T = square.tapSquare(X, Y); //check if you touch a square
			
			if (T >= 0){	//if so add score + time
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

