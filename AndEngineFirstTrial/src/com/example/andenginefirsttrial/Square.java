package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;

public class Square {
	
		float speedX=2;
		float speedY=2;
	
	    public Rectangle sprite;
	    public static Square instance;
	    Camera mCamera;
	    Random randomGenerator = new Random();
	    
	    public static Square getSharedInstance() {
	        if (instance == null)
	            instance = new Square();
	        return instance;
	    }

	    private Square() {
	        sprite = new Rectangle(0, 0, 50, 50, MainActivity.getSharedInstance()
	            .getVertexBufferObjectManager());

	        mCamera = MainActivity.getSharedInstance().mCamera;
	        
	        int XMax = (int) (mCamera.getWidth() - sprite.getWidth() / 2);
	        int YMax = (int) (mCamera.getHeight() - sprite.getHeight()/2);
	        int randomX = randomGenerator.nextInt(XMax);
	        int randomY= randomGenerator.nextInt(YMax);
	        sprite.setPosition(randomX,
	            randomY);
	    }
	
	    public void moveSquare(){

				int lL = 0;
				int rL = (int) (mCamera.getWidth() - (int) sprite.getWidth());
				
				int uL = 0;
				int dL = (int) (mCamera.getHeight() - (int) sprite.getHeight());

				float newX, newY;
				
				float randomMove;
				newX = sprite.getX();
				// Calculate New X,Y Coordinates within Limits
				if (newX > lL-speedX && newX <rL-speedX){
					newX = newX + speedX;
				}	
				else if (newX<=lL-speedX){
					newX=lL;
					if(speedX<0)
						speedX = speedX*(-1);
				}
				else {
					newX=rL;
					if(speedX>0)
						speedX = speedX*(-1);
				}
				
				
				
				newY = sprite.getY();
				// Calculate New X,Y Coordinates within Limits
				if (newY > uL-speedY && newY <dL-speedY){
					newY = newY + speedY;
				}	
				else if (newY<=uL-speedY){
					newY=uL;
					if(speedY<0)
						speedY = speedY*(-1);
				}
				else {
					newY=dL;
					if(speedY>0)
						speedY = speedY*(-1);
				}

				sprite.setPosition(newX, newY);
			
	    }
}
