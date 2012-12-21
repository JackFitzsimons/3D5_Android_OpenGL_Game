package com.example.andenginefirsttrial;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.text.Text;


public class MainMenuScene extends MenuScene implements IOnMenuItemClickListener{

	// ===========================================================
	// Global variables
	// ===========================================================
	
	MainActivity activity;
	
	float startTime, endTime; //to prevent accidental double click from previous scene
	
	final int MENU_START = 0;
	final int MENU_SCORE = 1;
	final int MENU_QUIT = 2;
	final int MENU_MUTE = 3;
	
	IMenuItem startButton;
	IMenuItem scoreButton;
	IMenuItem quitButton;
	IMenuItem MUTE1;
	IMenuItem MUTE2;
	

	// ===========================================================
	// Constructor
	// ===========================================================
	
	public MainMenuScene(){
		super(MainActivity.getSharedInstance().mCamera);
		
	}
	

	// ===========================================================
	// Methods
	// ===========================================================
	
	//initialises the menu
	public void SceneSetUp(){
		
		activity = MainActivity.getSharedInstance();
		
		activity.SS.detachChildren();
		
		this.detachChildren();
		
		attachChildren(activity.mSnowSprite);
		
		registerUpdateHandler(new SnowFlakeUpdateHandler());
		
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        
        Text HowToPlay1 = new Text(0, 0, activity.handWritingFontRED, activity.getString(R.string.howtoplay1), activity.getVertexBufferObjectManager());
        
        float X = (activity.mCamera.getWidth() - HowToPlay1.getWidth())/2;
        float Y =  30;
        
        HowToPlay1.setPosition(X,Y);
        
        attachChild(HowToPlay1);
        
        Text HowToPlay2 = new Text(0, 0, activity.handWritingFontRED, activity.getString(R.string.howtoplay2), activity.getVertexBufferObjectManager());
        
        X = (activity.mCamera.getWidth() - HowToPlay2.getWidth())/2;
        Y = 45 + HowToPlay1.getHeight();
        
        HowToPlay2.setPosition(X,Y);
        
        attachChild(HowToPlay2);
        
		startButton = new TextMenuItem(MENU_START, activity.mFont, activity.getString(R.string.startButton), activity.getVertexBufferObjectManager());
		scoreButton = new TextMenuItem(MENU_SCORE, activity.mFont, activity.getString(R.string.scoreButton), activity.getVertexBufferObjectManager());
		quitButton = new TextMenuItem(MENU_QUIT, activity.mFont, activity.getString(R.string.quitButton), activity.getVertexBufferObjectManager());
		MUTE1 = new TextMenuItem(MENU_MUTE, activity.mFont, "MUTE", activity.getVertexBufferObjectManager());
		MUTE2 = new TextMenuItem(MENU_MUTE, activity.mFont, "MUTE", activity.getVertexBufferObjectManager());
		startButton.setPosition(activity.mCamera.getWidth() / 2 - startButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - startButton.getHeight() / 2);
		scoreButton.setPosition(activity.mCamera.getWidth() / 2 - scoreButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - scoreButton.getHeight() / 2 + 50);
		quitButton.setPosition(activity.mCamera.getWidth() / 2 - quitButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - quitButton.getHeight() / 2 + 100);
		MUTE1.setPosition(20, activity.mCamera.getHeight() - 40);
		MUTE2.setPosition(20, activity.mCamera.getHeight() - 40);
		addMenuItem(startButton);
		addMenuItem(scoreButton);
		addMenuItem(quitButton);
		addMenuItem(MUTE1);
		addMenuItem(MUTE2);
		
		if(activity.checkMUTE()==1){
			MUTE1.setVisible(true);
			MUTE2.setVisible(false);
		}
		else{
			MUTE1.setVisible(false);
			MUTE2.setVisible(true);
		}
	    MainActivity.getSharedInstance().setCurrentScene(this);
	    startTime = activity.time.checkTime();
		setOnMenuItemClickListener(this);
	}

	//What happens when the menu items are touched
	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
		endTime = activity.time.checkTime();
		if(startTime - endTime > 1){
		    switch (arg1.getID()) {
		    case MENU_START:
				this.detachChildren();
	        	activity.GS.SceneSetUp();
	
	            return true;
		    case MENU_SCORE:
				this.detachChildren();
	        	activity.TS.SceneSetUp();
	
	            return true;
		    case MENU_QUIT:	//kill everything and quit the app
				this.detachChildren();
	        	//activity.GS.SceneSetUp();
				activity.music.stop();
				activity.getMusicManager().releaseAll();
				android.os.Process.killProcess(android.os.Process.myPid());
	            return true;
	            
		    case MENU_MUTE:
				activity.changeMUTE();

				if(activity.checkMUTE()==2){
					MUTE1.setVisible(false);
					MUTE2.setVisible(true);
				}
				else{
					MUTE1.setVisible(true);
					MUTE2.setVisible(false);
				}
	            return true;
	        
		    default:
		        break;
		    }
		}
	    return false;
	}


}
