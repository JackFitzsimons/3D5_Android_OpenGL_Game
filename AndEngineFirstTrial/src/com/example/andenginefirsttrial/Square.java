package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;

import android.util.FloatMath;
import android.util.Log;


//defines the class square an instance of which contains most of the variables for game play functionality 
public class Square {
	
		MainActivity activity;
		
		float speedX[] = new float[10];
		float speedY[] = new float[10];
		
		float timestep = 0.1f;
		
		float h;
    	float w;

    	int ON= -1;
    	float tON;
		
		float mag;
	
	    public Present sprite[] = new Present[8];

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
	  	    	
	    	mag=3f;
	    	
	    	for(int i = 0; i<8; i++)
	    		sprite[i] = new Present(0f, 0f, activity.mSquareType1, activity.mSquareType2, activity.mSquareType3, activity.getVertexBufferObjectManager());
	    	
	    	
	        mCamera = MainActivity.getSharedInstance().mCamera;
	        
	        h = sprite[0].getHeight();
	    	w = sprite[0].getWidth();
	    	
	    	Log.w("Outside","SetRandomOn");

			this.setRandomSquareOn();
	        
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
	    	for(int i = 0; i<8; i++)
	    		sprite[i].downFlash();
	   		moveEachSquare();
	    	collisionDetection();
	    }
	    
	    void moveEachSquare(){ //moves the square to the next position
	    	int lL = 0;
			int rL = (int) (mCamera.getWidth() - (int) sprite[0].getWidth());
			
			int uL = 60;
			int dL = (int) (mCamera.getHeight() - (int) sprite[0].getHeight());

	    	for(int i = 0; i<8; i++){
	    		
	    		
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

	    	for(int i = 0; i < 8;i++){
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
	    
	    void setRandomSquareOn(){
	    	Log.w("Inside","SetRandomOn");
	    	Random rand = new Random();
	    	ON = rand.nextInt(8);
	    	while(sprite[ON].getColorType()==2) ON = rand.nextInt(8);
	    	sprite[ON].changeTexture(1);
	    	tON = activity.time.checkTime(); 
	    	
	    	
}
	    
	    void changeSpeed(float factor){

	    	for(int i = 0; i<8; i++){
	    		speedX[i]*=factor;
	    		speedY[i]*=factor;
	    	}
	    }
	    
	    
	    int tapSquare(float X, float Y){
	    	
	    	if(((X>sprite[ON].getX())&&(X<(sprite[ON].getX()+w)))&&(((Y>sprite[ON].getY())&&(Y<(sprite[ON].getY()+h))))){	    			
	    		sprite[ON].changeTexture(2);
	    		ON=-1;
    			this.setRandomSquareOn();
	    		if  (tON - activity.time.checkTime() < 5){	
	    			return (int)(5 -(tON - activity.time.checkTime()));
	    		}
	    		else 
	    			return 0;
	    	}
	    	
	    	return 0;
	    }
}