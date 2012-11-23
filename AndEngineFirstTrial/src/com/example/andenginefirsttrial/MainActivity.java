package com.example.andenginefirsttrial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;



public class MainActivity extends SimpleBaseGameActivity {

	
	// ===========================================================
    // Constants
    // ===========================================================
	static final int CAMERA_WIDTH = 800;
	
	static final int CAMERA_HEIGHT = 480;

		
	    
	 // ===========================================================
     // Fields
     // ===========================================================
	 
	public Font mFont;
	
	public Camera mCamera;
	    
	public Scene mCurrentScene;
	
	public static MainActivity instance;
    
	 // ===========================================================
     // Constructors
     // ===========================================================

     // ===========================================================
     // Getter & Setter
     // ===========================================================

     // ===========================================================
     // Methods for/from SuperClass/Interfaces
     // ===========================================================
	
	@Override
	public EngineOptions onCreateEngineOptions() {
	    instance = this;
	    mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

	    return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	}

	@Override
	protected void onCreateResources() {
	    mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL), 32);
	    mFont.load();
	}

	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mCurrentScene = new SplashScreen();
		return mCurrentScene;

	}

        
        
     // ===========================================================
     // Methods
     // ===========================================================

	public static MainActivity getSharedInstance() {
	    return instance;
	}

	// to change the current main scene
	public void setCurrentScene(Scene scene) {
	    mCurrentScene = scene;
	    getEngine().setScene(mCurrentScene);
	}

	    
	 // ===========================================================
     // Inner and Anonymous Classes
     // ===========================================================
	}

