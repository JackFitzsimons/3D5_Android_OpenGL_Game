package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;


public class Circle {
	
	float speedX;
	float speedY;

    public Rectangle sprite;
    public static Circle instance;
    Camera mCamera;
    Random randomGenerator = new Random();
    
    public static Circle getSharedInstance() {
        if (instance == null)
            instance = new Circle();
        return instance;
    }

    private Circle() {
        sprite = new Rectangle(0, 0, 50, 50, MainActivity.getSharedInstance()
            .getVertexBufferObjectManager());
        
        sprite.setColor(new Color(1f, 4f, 0));
        
        Random rand = new Random();
        
        speedX = (rand.nextFloat()*5);
        
        speedY = (float) Math.sqrt(25 - Math.pow(speedX, 2));
        
        speedX-=2.5;
        speedY-=2.5;
        
        mCamera = MainActivity.getSharedInstance().mCamera;
        
        int XMax = (int) (mCamera.getWidth() - sprite.getWidth() );
        int YMax = (int) (mCamera.getHeight() - sprite.getHeight());
        int randomX = randomGenerator.nextInt(XMax);
        int randomY= randomGenerator.nextInt(YMax);
        sprite.setPosition(randomX,
            randomY);
    }

    public void moveCircle(){

			int lL = 0;
			int rL = (int) (mCamera.getWidth() - (int) sprite.getWidth());
			
			int uL = 0;
			int dL = (int) (mCamera.getHeight() - (int) sprite.getHeight());

			float newX, newY;
			
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
