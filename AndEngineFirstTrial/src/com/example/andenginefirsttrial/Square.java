package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.text.Text;

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
	    public Text sprite_score;
	    float new_X = 0;
	    float new_Y = 0;
	    int count = 0;
	    

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
	    	
	    	sprite_score = new Text(0f, 0f,activity.handWritingFontWHITE, "3", 3, activity.getVertexBufferObjectManager());
	    	sprite_score.setVisible(false);
	    	
	        mCamera = MainActivity.getSharedInstance().mCamera;
	        
	        h = sprite[0].getHeight();
	    	w = sprite[0].getWidth();
	    	

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
	    	changeSquareOn();
	    }
	    
	    void changeSquareOn(){
	    	int level = activity.GS.level;
	    	if(level==1 && (tON- activity.time.checkTime())>5){
	    		switchNewOn();
	    	}
	    	else if(level==2 && (tON- activity.time.checkTime())>3){
	    		switchNewOn();
	    	}
	    	else if((tON- activity.time.checkTime())>1){
	    		switchNewOn();
	    	}
	    }
	    
	    void switchNewOn(){
	    	if(ON!=-1)
	    		sprite[ON].changeTexture(0);
	    	ON = -1;
	    	setRandomSquareOn();
	    	
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
	    	if(sprite_score.isVisible()){
	    		float X_coord = sprite_score.getX();
	    		float Y_coord = sprite_score.getY();
	    		new_X = X_coord + ((activity.GS.t.getX() + (activity.GS.t.getWidth()/2) - X_coord)/10);
	    		new_Y = Y_coord - ((Y_coord)/10);
	    		sprite_score.setPosition(new_X, new_Y);
	    		
	    		if(count > 20){
	    			sprite_score.setVisible(false);
	    			count = 0;
	    		}
	    		count++;
	    	}
	    }
	    
	    void collisionDetection(){

	    	for(int i = 0; i < 8;i++){
		    		float olX = -1f;
	    			float olY = -1f;
		    		for(int j = i+1; j <8; j++){//checks if box A and B will hit and IF they do, what fraction of time will it be between frames
		    				olX = -1f;
		    				olY = -1f;
		    				
		    				Boolean X, Y;
		    				X=true;
		    				Y=true;
		    				
		    				float X1 = sprite[i].getX();
		    				float Y1 = sprite[i].getY();
		    				float X2 = sprite[j].getX();
		    				float Y2 = sprite[j].getY();
		    				
		    			
		    				if((X1 + w >= X2)&&(X1 <= X2)){
		    					olX = X1 + w - X2;
		    					X = true;
		    				}
		    				else if((X2 <= X1)&&(X2 + w >= X1)){
		    					olX = X2 + w - X1;
		    					X=false;
		    				}
		    			
		    				if(olX>=0){
		    					if((Y1 + h >= Y2)&&(Y1 <= Y2)){
		    						olY = Y1 + h - Y2;
		    						Y=true;
		    					}
		    				
		    					else if((Y2 <= Y1)&&(Y2 + h >= Y1)){
		    						olY = Y2 + h - Y1;
		    						Y=false;
		    					}
		    				}
		    			
		    				if(olX>=0 && olY>=0 ){
		    					if(olY>olX) {
		    					
		    						float t1, t2;
	    							t1 = speedX[j];
	    							t2 = speedX[i];
	    							if(X){
	    								if(t1<t2){
	    									speedX[j]= t2;
	    									speedX[i] = t1;
	    								}
	    								else{
	    									speedX[j]= t1;
	    									speedX[i] = t2;
	    								}
	    							}
	    							else{
	    								if(t1<t2){
	    									speedX[j]= t1;
	    									speedX[i] = t2;
	    								}
	    								else{
	    									speedX[j]= t2;
	    									speedX[i] = t1;
	    								}
	    							}
		    						
		    					}
		    					if(olY<olX) {
		    						
		    							float t1, t2;
		    							t1 = speedY[j];
		    							t2 = speedY[i];
		    							if(Y){
		    								if(t1<t2){
		    									speedY[j]= t2;
		    									speedY[i] = t1;
		    								}
		    								else{
		    									speedY[j]= t1;
		    									speedY[i] = t2;
		    								}
		    							}
		    							else{
		    								if(t1<t2){
		    									speedY[j]= t1;
		    									speedY[i] = t2;
		    								}
		    								else{
		    									speedY[j]= t2;
		    									speedY[i] = t1;
		    								}
		    							}
		    						
		    					}
		    					
		    					if(olX==olY) {
		    						
		    						float t1, t2;
	    							t1 = speedY[j];
	    							t2 = speedY[i];
	    							if(Y){
	    								if(t1<t2){
	    									speedY[j]= t2;
	    									speedY[i] = t1;
	    								}
	    								else{
	    									speedY[j]= t1;
	    									speedY[i] = t2;
	    								}
	    							}
	    							t1 = speedX[j];
	    							t2 = speedX[i];
	    							if(X){
	    								if(t1<t2){
	    									speedX[j]= t2;
	    									speedX[i] = t1;
	    								}
	    								else{
	    									speedX[j]= t1;
	    									speedX[i] = t2;
	    								}
	    							}
		    						
		    					}
		    				
	    				
	    			}
	    		}
	    		
	    	}
	    	
	    	
	    }
	    
	    void setRandomSquareOn(){
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
	    
	    
	    long tapSquare(float X, float Y){
	    	
	    	if(ON!=-1){
		    	if(((X>sprite[ON].getX())&&(X<(sprite[ON].getX()+w)))&&(((Y>sprite[ON].getY())&&(Y<(sprite[ON].getY()+h))))){	    			
		    		sprite[ON].changeTexture(2);
		    		ON=-1;
		    		if  (tON - activity.time.checkTime() < 5){	
		    			return (long)(5 -(tON - activity.time.checkTime()));
		    		}
		    		else 
		    			return 0;
		    	}
	    	}
	    	
	    	return -1;
	    }
	    
	    void score_sprite(float X, float Y, long extratime){
	    	sprite_score.setText("+" + String.valueOf(extratime));
	    	sprite_score.setPosition(X, Y);
	    	sprite_score.setVisible(true);
	    }
}