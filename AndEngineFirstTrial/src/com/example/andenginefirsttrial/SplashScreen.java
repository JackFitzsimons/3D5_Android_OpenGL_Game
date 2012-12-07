package com.example.andenginefirsttrial;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import android.view.MotionEvent;

// defining the splash screen class
	public class SplashScreen extends Scene implements IOnSceneTouchListener{
	MainActivity activity;
	
	public SplashScreen(){
		//default constructor
		

		loadResources();
	}
	
	//method for setting or resetting Splashscreen
	public void SceneSetUp(){
	    MainActivity.getSharedInstance().setCurrentScene(this);

		/*setBackground(new Background(0.09804f, 0.6274f, 0.8784f));*/
		activity = MainActivity.getSharedInstance();
		/*Text title1 = new Text(0, 0, activity.mFont, activity.getString(R.string.title_1), activity.getVertexBufferObjectManager());
		Text title2 = new Text(0, 0, activity.mFont, activity.getString(R.string.title_2), activity.getVertexBufferObjectManager());
		
		//title1.setColor(Color.WHITE);
		//title2.setColor(Color.WHITE);
		
		title1.setPosition(-title1.getWidth(), activity.mCamera.getHeight() / 2);
		title2.setPosition(activity.mCamera.getWidth(), (activity.mCamera.getHeight() / 2));

		attachChild(title1);
		attachChild(title2);

		title1.registerEntityModifier(new MoveXModifier(2, title1.getX(), activity.mCamera.getWidth() / 2 - title1.getWidth()));
		title2.registerEntityModifier(new MoveXModifier(2, title2.getX(), activity.mCamera.getWidth() / 2));
		*/
	    
	    SpriteBackground bg = new SpriteBackground(new Sprite(0f, 0f, activity.mSplashBgTexture, activity.getVertexBufferObjectManager()));
        setBackground(bg);
		
		setOnSceneTouchListener(this);
		
    	//activity.MMS.SceneSetUp();

	}
	
	public void loadResources(){
				DelayModifier dMod = new DelayModifier(2, new IEntityModifierListener() {
		    @Override
		    public void onModifierStarted(IModifier arg0, IEntity arg1) {
		    }
		    public void onModifierFinished(IModifier arg0, IEntity arg1) {

		    }
		});
		registerEntityModifier(dMod);

	}


	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		if(pSceneTouchEvent.getAction() == MotionEvent.ACTION_DOWN){
		activity.MMS.SceneSetUp();
		}
		return true;
	}
}
