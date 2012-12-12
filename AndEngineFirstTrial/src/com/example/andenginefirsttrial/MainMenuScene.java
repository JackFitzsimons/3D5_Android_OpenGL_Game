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
	
	public MainMenuScene(){
		super(MainActivity.getSharedInstance().mCamera);
		
	}
	
	public void SceneSetUp(){
		
		Log.w("Scene","Main Menu");

		activity = MainActivity.getSharedInstance();
		
		activity.SS.detachChildren();
		
		this.detachChildren();
		
		attachChildren(activity.mSnowSprite);
		
		registerUpdateHandler(new SnowFlakeUpdateHandler());
		
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        
        Text HowToPlay1 = new Text(0, 0, activity.handWritingFontBLACK, activity.getString(R.string.howtoplay1), activity.getVertexBufferObjectManager());
        
        float X = (activity.mCamera.getWidth() - HowToPlay1.getWidth())/2;
        float Y =  30;
        
        HowToPlay1.setPosition(X,Y);
        
        attachChild(HowToPlay1);
        
        Text HowToPlay2 = new Text(0, 0, activity.handWritingFontBLACK, activity.getString(R.string.howtoplay2), activity.getVertexBufferObjectManager());
        
        X = (activity.mCamera.getWidth() - HowToPlay2.getWidth())/2;
        Y = 45 + HowToPlay1.getHeight();
        
        HowToPlay2.setPosition(X,Y);
        
        attachChild(HowToPlay2);
        
		IMenuItem startButton = new TextMenuItem(MENU_START, activity.mFont, activity.getString(R.string.startButton), activity.getVertexBufferObjectManager());
		IMenuItem scoreButton = new TextMenuItem(MENU_SCORE, activity.mFont, activity.getString(R.string.scoreButton), activity.getVertexBufferObjectManager());
		IMenuItem quitButton = new TextMenuItem(MENU_QUIT, activity.mFont, activity.getString(R.string.quitButton), activity.getVertexBufferObjectManager());
		startButton.setPosition(activity.mCamera.getWidth() / 2 - startButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - startButton.getHeight() / 2);
		scoreButton.setPosition(activity.mCamera.getWidth() / 2 - scoreButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - scoreButton.getHeight() / 2 + 50);
		quitButton.setPosition(activity.mCamera.getWidth() / 2 - quitButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - quitButton.getHeight() / 2 + 100);
		addMenuItem(startButton);
		addMenuItem(scoreButton);
		addMenuItem(quitButton);
	    MainActivity.getSharedInstance().setCurrentScene(this);

		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
	    switch (arg1.getID()) {
	    case MENU_START:
        	activity.GS.SceneSetUp();

            return true;
	    case MENU_SCORE:
        	activity.GS.SceneSetUp();

            return true;
	    case MENU_QUIT:
        	activity.GS.SceneSetUp();

            return true;
        
	    default:
	        break;
	    }
	    return false;
	}


}
