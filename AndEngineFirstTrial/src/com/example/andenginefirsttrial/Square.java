package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;

import android.util.FloatMath;
import android.util.Log;

//defines the class square an instance of which contains most of the variables for game play functionality 
public class Square {
	
		float speedX[] = new float[8];
		float speedY[] = new float[8];
		
		float timestep = 0.1f;
		
		float h;
    	float w;
		
		float mag;
		
		int yellowSq[] = new int[8];
		Boolean boxOn[] = new Boolean[8];
	
	    public Rectangle sprite[] = new Rectangle[8];
	    public static Square instance;
	    Camera mCamera;
	    Random randomGenerator = new Random();
	    
	    public static Square getSharedInstance() {
	        if (instance == null)
	            instance = new Square();
	        return instance;
	    }

	    // the constructor for class square
	    private Square() {
	    	for(int i = 0;i<8; i++)
	    		boxOn[i]=false;
	  
	    	mag=3f;
	    	
	    	for(int i = 0; i<8; i++)
	    		sprite[i] = new Rectangle(0, 0, 50, 50, MainActivity.getSharedInstance()
	    				.getVertexBufferObjectManager());

	        mCamera = MainActivity.getSharedInstance().mCamera;
	        
	        h = sprite[0].getHeight();
	    	w = sprite[0].getWidth();
	        
	        Random rand = new Random();
	        
	        for(int i = 0; i<8; i++){
	        	speedX[i] = (rand.nextFloat());
	        
	        	int j = rand.nextInt(2);
	        	
	        	if(j==1)
	        		speedX[i] *= (-1);
	        	
	        	j = rand.nextInt(2);
	        
	        	if(j==1)
	        		speedY[i] =  FloatMath.sqrt(1 - (float) Math.pow(speedX[i], 2f));
	        	else
	        		speedY[i] =  FloatMath.sqrt(1 - (float) Math.pow(speedX[i], 2f))*(-1);
	        
	        	speedX[i]-=0.5;
	        	speedY[i]-=0.5;
	        	
	        	speedX[i]*=mag;
	        	speedY[i]*=mag;
	        }
	        double XMax = (double) (mCamera.getWidth() - sprite[0].getWidth() );
	        double YMax = (double) (mCamera.getHeight() - sprite[0].getHeight());
	        
	        for(int i = 0; i<8; i++){
	        	int initX = (int) ((XMax/4) + (i%4)*(XMax/6));
	        	int initY = (int) ((YMax/3) + YMax*Math.ceil(i/4)/3);
	        
	        	sprite[i].setPosition(initX,
	        			initY);
	        }
	        
	    }
	
	    // moves each square, is called in upDate screen in GameScene
	    public void moveSquare(){
	   		moveSquares();
	    	collisionDetection();
	    }
	    
	    public Boolean checkIfAllOff(){
	    	for(int i = 0; i<8;i++)
	    		if(boxOn[i]) {
	    			//Log.w("On", String.valueOf(i));
	    			return false;
	    		}
	    	
	    	return true;
	    }
	    
	    void moveSquares(){ //moves the square to the next position
	    	int lL = 0;
			int rL = (int) (mCamera.getWidth() - (int) sprite[0].getWidth());
			
			int uL = 0;
			int dL = (int) (mCamera.getHeight() - (int) sprite[0].getHeight());

	    	for(int i = 0; i<8; i++){
	    		if(yellowSq[i]> 0){
	    			yellowSq[i]--;
	    			if(yellowSq[i]==0){
	    				if(boxOn[i]) setColor(3, i);
	    				else setColor(2, i);
	    			}
	    			
	    		}
	    		
	    		//If hit wall turn around
				
				float newX, newY;
				
				newX = sprite[i].getX();
				// Calculate New X,Y Coordinates within Limits
				if (newX > lL-speedX[i] && newX <rL-speedX[i]){
					newX = newX + speedX[i];
				}	
				else if (newX<=lL-speedX[i]){
					newX=lL;
					if(speedX[i]<0)
						speedX[i] = speedX[i]*(-1);
				}
				else {
					newX=rL;
					if(speedX[i]>0)
						speedX[i] = speedX[i]*(-1);
				}
				
				
				newY = sprite[i].getY();
				// Calculate New X,Y Coordinates within Limits
				if (newY > uL-speedY[i] && newY <dL-speedY[i]){
					newY = newY + speedY[i];
				}	
				else if (newY<=uL-speedY[i]){
					newY=uL;
					if(speedY[i]<0)
						speedY[i] = speedY[i]*(-1);
				}
				else {
					newY=dL;
					if(speedY[i]>0)
						speedY[i] = speedY[i]*(-1);
				}
								
				sprite[i].setPosition(newX, newY);
	    	}
	    }
	    
	    void collisionDetection(){

	    	for(int i = 0; i < 8; i++){
	    		float olX = -1f;
    			float olY = -1f;
	    		for(int j = i+1; j <8; j++){//checks if box A and B will hit and IF they do, what fraction of time will it be between frames
	    			
	    				olX = -1f;
	    				olY = -1f;
	    				
	    				float X1 = sprite[i].getX();
	    				float Y1 = sprite[i].getY();
	    				float X2 = sprite[j].getX();
	    				float Y2 = sprite[j].getY();
	    				
	    			
	    				if((X1 + w >= X2)&&(X1 <= X2)){
	    					olX = X1 + w - X2;
	    				}
	    				else if((X2 <= X1)&&(X2 + w >= X1)){
	    					olX = X2 + w - X1;
	    				}
	    			
	    				if(olX>=0){
	    					if((Y1 + h >= Y2)&&(Y1 <= Y2)){
	    						olY = Y1 + h - Y2;
	    					}
	    				
	    					else if((Y2 <= Y1)&&(Y2 + h >= Y1)){
	    						olY = Y2 + h - Y1;
	    					}
	    				}
	    			
	    				if(olX>=0 && olY>=0 ){
	    					if(olY>olX) {
	    						
	    							float t;
	    							t = speedX[j];
	    							speedX[j] = speedX[i];
	    							speedX[i] = t;
	    						
	    					}
	    					if(olY<olX) {
	    						
	    							float t;
	    							t = speedY[j];
	    							speedY[j] = speedY[i];
	    							speedY[i] = t;
	    						
	    					}
	    					
	    					if(olX==olY) {
	    						
	    							float t;
	    							t = speedY[j];
	    							speedY[j] = speedY[i];
	    							speedY[i] = t;
	    							t = speedX[j];
	    							speedX[j] = speedX[i];
	    							speedX[i] = t;
	    						
	    					}
	    				
	    				
	    			}
	    		}
	    		
	    	}
	    	
	    	
	    }
	    
	    void turnAllSquaresOff(){
	    	for(int i = 0; i < 8; i++){
	    		setColor(2,i);
	    	}
	    }
	    
	    void setRandomSquareOn(){
	    	Random rand = new Random();
	    	 int k = rand.nextInt(8);
	    	setColor(3,k);
	    	boxOn[k] = true;
	    }
	    
	    void setColor(int i, int k){
	    	if(i==1)
	    		sprite[k].setColor(new Color(1f, 4f, 0));
	    	else if(i==2)
	    		sprite[k].setColor(new Color(1f,1f, 1f));
	    	else
	    		sprite[k].setColor(new Color(0.66f, 1f, 0.6f));
	    }
	    
	    void changeSpeed(float factor){
	    	for(int i = 0; i<8; i++){
	    		speedX[i]*=factor;
	    		speedY[i]*=factor;
	    	}
	    }
	    
	    long tapSquare(float X, float Y){
	    	for(int i = 0; i <8; i++){
	    		if(boxOn[i]){
	    			if(((X>sprite[i].getX())&&(X<(sprite[i].getX()+w)))&&(((Y>sprite[i].getY())&&(Y<(sprite[i].getY()+h))))){
	    				setColor(1, i);
	    				yellowSq[i]+=3;
	    				boxOn[i] = false;
	    				return 5;
	    			}
	    		}
	    	}
	    	return 0;
	    }
}