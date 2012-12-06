package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;

import android.util.FloatMath;


//defines the class square an instance of which contains most of the variables for game play functionality 
public class Square {
	
		MainActivity activity;
	
		int ON=-1;
		int flash= -1;
		
		float speedX[] = new float[10];
		float speedY[] = new float[10];
		
		float timestep = 0.1f;
		
		float h;
    	float w;
		
		float mag;
		
		int yellowSq[] = new int[8];
		Boolean boxOn[] = new Boolean[8];
	
	    public Sprite sprite[] = new Sprite[10];

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
	    	activity = MainActivity.instance;
	    	for(int i = 0;i<8; i++)
	    		boxOn[i]=false;
	  
	    	ON = -1;
	    	
	    	mag=3f;
	    	
	    	for(int i = 0; i<8; i++)
	    		sprite[i] = new Sprite(0f, 0f, activity.mSquareType1, activity.getVertexBufferObjectManager());

	    	sprite[8] = new Sprite(0f, 0f, activity.mSquareType2, activity.getVertexBufferObjectManager());
	    	
	    	sprite[9] = new Sprite(0f, 0f, activity.mSquareType3, activity.getVertexBufferObjectManager());
	    	
	    	sprite[8].setVisible(false);
	    	sprite[9].setVisible(false);
	    	
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
	    		if(ON!=-1) {
	    			return false;
	    		}
	    	
	    	return true;
	    }
	    
	    void moveSquares(){ //moves the square to the next position
	    	int lL = 0;
			int rL = (int) (mCamera.getWidth() - (int) sprite[0].getWidth());
			
			int uL = 60;
			int dL = (int) (mCamera.getHeight() - (int) sprite[0].getHeight());

	    	for(int i = 0; i<10; i++){
	    		if(i<8){
	    			if(yellowSq[i]> 0){
	    				yellowSq[i]--;
	    				if(yellowSq[i]==0){
	    					setColor(2, i);
	    				}
	    			
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

	    	for(int i = 0; i < 10;i++){
	    		if(sprite[i].isVisible()){
		    		float olX = -1f;
	    			float olY = -1f;
		    		for(int j = i+1; j <10; j++){//checks if box A and B will hit and IF they do, what fraction of time will it be between frames
		    			if(sprite[j].isVisible()){
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
	    	if(k==flash) setColor(2,k);
	    	setColor(1,k);
	    	ON = k;
}
	    
	    void setColor(int i, int k){
	    	if(i==1){
	    		sprite[8].setVisible(true);
	    		sprite[8].setPosition(sprite[k].getX(), sprite[k].getY());
	    		speedX[8]= speedX[k];
	    		speedY[8]= speedY[k];	    		
	    		sprite[k].setVisible(false);
	    	}
	    	else if(i==2){
	    		if(k==flash){
	    			sprite[flash].setVisible(true);
	    			sprite[flash].setPosition(sprite[9].getX(), sprite[9].getY());
	    			speedX[flash]= speedX[9];
	    			speedY[flash]= speedY[9];	    		
	    			sprite[9].setVisible(false);
	    			flash=-1;
	    		}
	    		else if(k == ON){
	    			sprite[ON].setVisible(true);
	    			sprite[ON].setPosition(sprite[8].getX(), sprite[8].getY());
	    			speedX[ON]= speedX[8];
	    			speedY[ON]= speedY[8];	    		
	    			sprite[8].setVisible(false);
	    		}
	    		
	    	}
	    	else{
	    		flash = k;
	    		sprite[9].setVisible(true);
    			sprite[9].setPosition(sprite[flash].getX(), sprite[flash].getY());
    			speedX[9]= speedX[flash];
    			speedY[9]= speedY[flash];	    		
    			sprite[flash].setVisible(false);
	    	}
	    }
	    
	    void changeSpeed(float factor){
	    	for(int i = 0; i<8; i++){
	    		speedX[i]*=factor;
	    		speedY[i]*=factor;
	    	}
	    }
	    
	    long tapSquare(float X, float Y){
	    	
	    	if(((X>sprite[8].getX())&&(X<(sprite[8].getX()+w)))&&(((Y>sprite[8].getY())&&(Y<(sprite[8].getY()+h))))){
	    			setColor(2, ON);
	    			yellowSq[ON]+=3;
	    			ON=-1;
	    			return 5;
	    	}
	    	
	    	return 0;
	    }
}