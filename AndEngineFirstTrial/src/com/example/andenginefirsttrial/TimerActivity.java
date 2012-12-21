package com.example.andenginefirsttrial;

import java.util.Date;

public class TimerActivity {
	// ===========================================================
	// Globals
	// ===========================================================

    long timeLeft;
    Date cal;	//needed to get times
    long seconds1;	//end time and now to be compared to see how long is left
    long seconds2;
    
    // ===========================================================
    // Methods
    // ===========================================================
    
    //initialises times
      public TimerActivity() {   
    	  seconds2 = cal.getTime()/1000;
    	  seconds1 = cal.getTime()/1000 + 15;
      }  
      
      //set how long is left
      public void setTimeleft(long a){
    	  cal = new Date();
    	  seconds2 = cal.getTime()/1000;
    	  seconds1 = seconds2 + a;
      }
      
      //returns time left
      public long checkTime(){
    	  cal = new Date();
    	  seconds2 = cal.getTime()/1000;
    	  if(seconds1<=0){
    		  timeLeft = 15;
    		  seconds1 = seconds2 + timeLeft;
    	  }
    	  

    	  return seconds1 - seconds2;
      }
      
      //Add on time
      public void AddTime(long t){
    	  seconds1+=t;
      }
      
      
      public void initial(long t){
    	  seconds2 = cal.getTime()/1000;
    	  seconds1 = cal.getTime()/1000 + t;
      }
}
