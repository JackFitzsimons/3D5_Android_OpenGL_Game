package com.example.andenginefirsttrial;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

public class MainMenuScene extends MenuScene implements IOnMenuItemClickListener{

	MainActivity activity;
	
	final int MENU_START = 0;
	
	public MainMenuScene(){
		super(MainActivity.getSharedInstance().mCamera);
		
	}
	
	public void SceneSetUp(){

		activity = MainActivity.getSharedInstance();

		//setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		SpriteBackground bg = new SpriteBackground(new Sprite(0f, 0f, activity.mMenuBgTexture, activity.getVertexBufferObjectManager()));
        setBackground(bg);
        
        Text HowToPlay1 = new Text(0, 0, activity.mFont, activity.getString(R.string.howtoplay1), activity.getVertexBufferObjectManager());
        
        float X = (activity.mCamera.getWidth() - HowToPlay1.getWidth())/2;
        float Y =  30;
        
        HowToPlay1.setPosition(X,Y);
        
        attachChild(HowToPlay1);
        
        Text HowToPlay2 = new Text(0, 0, activity.mFont, activity.getString(R.string.howtoplay2), activity.getVertexBufferObjectManager());
        
        X = (activity.mCamera.getWidth() - HowToPlay2.getWidth())/2;
        Y =  60 + HowToPlay1.getHeight();
        
        HowToPlay2.setPosition(X,Y);
        
        attachChild(HowToPlay2);
        
		IMenuItem startButton = new TextMenuItem(MENU_START, activity.mFont, activity.getString(R.string.start), activity.getVertexBufferObjectManager());
		startButton.setPosition(activity.mCamera.getWidth() / 2 - startButton.getWidth() / 2, activity.mCamera.getHeight() / 2 - startButton.getHeight() / 2);
		addMenuItem(startButton);
	    MainActivity.getSharedInstance().setCurrentScene(this);

		setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1, float arg2, float arg3) {
	    switch (arg1.getID()) {
	        case MENU_START:
	        	activity.GS.SceneSetUp();

	            return true;

	        default:
	            break;
	    }
	    return false;
	}


}
