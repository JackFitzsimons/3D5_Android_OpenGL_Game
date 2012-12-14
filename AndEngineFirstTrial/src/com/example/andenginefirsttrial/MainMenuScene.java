package com.example.andenginefirsttrial;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.text.Text;

import android.util.Log;

public class MainMenuScene extends MenuScene implements IOnMenuItemClickListener{

	MainActivity activity;
	
	final int MENU_START = 0;
	final int MENU_SCORE = 1;
	final int MENU_QUIT = 2;
	final int MENU_MUTE = 3;
	
	IMenuItem startButton;
	IMenuItem scoreButton;
	IMenuItem quitButton;
	IMenuItem mute1;
	IMenuItem mute2;
	
	public MainMenuScene(){
		super(MainActivity.getSharedInstance().mCamera);
		
	}
	
	public void SceneSetUp(){
		
		//Log.w("Scene","Main Menu");

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
		mute1 = new TextMenuItem(MENU_MUTE, activity.mFont, "Mute: Off", activity.getVertexBufferObjectManager());
		mute2 = new TextMenuItem(MENU_MUTE, activity.mFont, "Mute: On", activity.getVertexBufferObjectManager());
		startButton.setPosition(activity.mCamera.getWidth() / 2 - startButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - startButton.getHeight() / 2);
		scoreButton.setPosition(activity.mCamera.getWidth() / 2 - scoreButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - scoreButton.getHeight() / 2 + 50);
		quitButton.setPosition(activity.mCamera.getWidth() / 2 - quitButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - quitButton.getHeight() / 2 + 100);
		mute1.setPosition(20, activity.mCamera.getHeight() - 40);
		mute2.setPosition(20, activity.mCamera.getHeight() - 40);
		addMenuItem(startButton);
		addMenuItem(scoreButton);
		addMenuItem(quitButton);
		addMenuItem(mute1);
		addMenuItem(mute2);
		
		if(activity.checkMute()=="MUTE"){
			mute1.setVisible(true);
			mute2.setVisible(false);
		}
		else{
			mute1.setVisible(false);
			mute2.setVisible(true);
		}
	    MainActivity.getSharedInstance().setCurrentScene(this);

		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
	    switch (arg1.getID()) {
	    case MENU_START:
			this.detachChildren();
        	activity.GS.SceneSetUp();

            return true;
	    case MENU_SCORE:
			this.detachChildren();
        	activity.TS.SceneSetUp();

            return true;
	    case MENU_QUIT:
			this.detachChildren();
        	//activity.GS.SceneSetUp();
			android.os.Process.killProcess(android.os.Process.myPid());
            return true;
            
	    case MENU_MUTE:
			activity.changeMute();
			if(activity.checkMute()=="SILENT"){
				mute1.setVisible(false);
				mute2.setVisible(true);
			}
			else{
				mute1.setVisible(true);
				mute2.setVisible(false);
			}
            return true;
        
	    default:
	        break;
	    }
	    return false;
	}


}
