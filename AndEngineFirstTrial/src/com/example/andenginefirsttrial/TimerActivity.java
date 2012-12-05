package com.example.andenginefirsttrial;

import java.util.Date;

import android.util.Log;


public class TimerActivity {
    long timeLeft;
    Date cal;
    long seconds1;
    long seconds2;
	
      public void onCreate() {   
        
      }  
      
      public long checkTime(){
    	  cal = new Date();
    	  seconds2 = cal.getTime()/1000;
    	  if(seconds1==0){
    		  timeLeft = 30;
    		  seconds1 = seconds2 + timeLeft;
    	  }
    	  
  		//Log.w("Now", String.valueOf(seconds2));
  		//Log.w("Original", String.valueOf(seconds1));

    	  return seconds1 - seconds2;
      }
      
      public void AddTime(long t){
    	  seconds1+=t;
      }
}
