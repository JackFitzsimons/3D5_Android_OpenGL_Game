package com.example.andenginefirsttrial;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;

public class GameScene extends Scene implements IOnSceneTouchListener{
	public Square square;
	public Circle circle;
	
	Camera mCamera;
	
	public GameScene() {
	    setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
	    mCamera = MainActivity.getSharedInstance().mCamera;
	    square = Square.getSharedInstance();
	    circle = Circle.getSharedInstance();
	    attachChild(square.sprite);
	    attachChild(circle.sprite);
	    
	    MainActivity.getSharedInstance().setCurrentScene(this);

	    registerUpdateHandler(new GameLoopUpdateHandler());

	}

	
	public void moveSquare(){
		square.moveSquare();
	}
	
	public void moveCircle(){
		circle.moveCircle();
	}
	
	public void cleaner(){}


	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}
