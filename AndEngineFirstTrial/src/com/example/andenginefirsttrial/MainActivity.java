package com.example.andenginefirsttrial;

//importing required libraries
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Typeface;



/* main controlling class*/
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
// create an instance of main activity, call it instance	
	public static MainActivity instance;
	
	//declare a char sequence to be used in 
	public CharSequence scoreString;
	
	//creates an instance of each scene in the game allowing each to be accessed at any time
	public GameOverScene GOS;
	public GameScene GS;
	public SplashScreen SS;
	public MainMenuScene MMS;
	
	public BitmapTextureAtlas mBitmapTextureAtlas;
	public TextureRegion mSquareType1;
	public TextureRegion mSquareType2;
	public TextureRegion mSquareType3;
    
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
		mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 51, 153);
	    mSquareType1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box1.png", 0, 0);
	    mSquareType2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box2.png", 0, 51);
	    mSquareType3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box3.png", 0, 102);
	    mBitmapTextureAtlas.load();
	    mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL), 32);
	    mFont.load();
	}

	//Initialize each screen, when called returns the splash screen
	protected Scene onCreateScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		GOS= new GameOverScene() ;
		GS= new GameScene();
		SS= new SplashScreen();
		MMS= new MainMenuScene();
				
		mCurrentScene = SS;
		
		SS.SceneSetUp();
		//return mCurrentScene;
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

