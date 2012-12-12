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
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.graphics.Color;
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
	 
	
	public TimerActivity time;

	public Font mFont;
	
	public Camera mCamera;
	    
	public Scene mCurrentScene;
// create an instance of main activity, call it instance	
	public static MainActivity instance;
	
	//declare a char sequence to be used in 
	public CharSequence scoreString;
	public int T;
	
	//creates an instance of each scene in the game allowing each to be accessed at any time
	public GameOverScene GOS;
	public GameScene GS;
	public SplashScreen SS;
	public MainMenuScene MMS;
	
	public Font handWritingFontWHITE;
	public Font handWritingFontRED;
	public Font handWritingFontBLACK;
	public ITexture fontTexture;
	
	public BitmapTextureAtlas mBitmapTextureAtlas;
	public TextureRegion mSquareType1;
	public TextureRegion mSquareType2;
	public TextureRegion mSquareType3;
	
	public TextureRegion mSnowFlake1;
	public TextureRegion mSnowFlake2;
	public TextureRegion mSnowFlake3;
	public TextureRegion mSnowFlake4;
	public TextureRegion mSnowFlake5;
	public TextureRegion mSnowFlake6;
	public TextureRegion mSnowFlake7;
	
	public BitmapTextureAtlas mSantaS;
	public TextureRegion mSantasSleigh;
	
	public BitmapTextureAtlas mheader;
	public TextureRegion mGamePlayHeader;
	
	public SnowFlake mSnowSprite[] = new SnowFlake[14];
    
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
		mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 256, 256);
	    mSquareType1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box1.png", 0, 0);
	    mSquareType2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box2.png", 0, 64);
	    mSquareType3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "christmas_box3.png", 0, 128);
	    
	    mSnowFlake1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes1.png", 64, 0);
	    mSnowFlake2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes2.png", 64, 64);
	    mSnowFlake3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes3.png", 64, 128);
	    mSnowFlake4 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes4.png", 64, 192);
	    mSnowFlake5 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes5.png", 128, 0);
	    mSnowFlake6 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes6.png", 128, 64);
	    mSnowFlake7 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes7.png", 128, 128);

	    mBitmapTextureAtlas.load();
	    mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL), 32);
	    mFont.load();
	    
	    mSantaS = new BitmapTextureAtlas(this.getTextureManager(), 512, 256);
	    mSantasSleigh = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mSantaS, this, "santas_sleigh.png", 0, 0);

	    mSantaS.load();
	    
	    mheader = new BitmapTextureAtlas(this.getTextureManager(), 800, 70);
	    mGamePlayHeader = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mheader, this, "game_play_header.png", 0, 0);

	    mSantaS.load();
	    
	    fontTexture = new BitmapTextureAtlas(this.getTextureManager(),1024,1024);

	    handWritingFontWHITE = FontFactory.createFromAsset(this.getFontManager(),fontTexture,this.getAssets(),"strato-unlinked.ttf",20f,true,Color.WHITE);
	    handWritingFontRED = FontFactory.createFromAsset(this.getFontManager(),fontTexture,this.getAssets(),"strato-unlinked.ttf",28f,true,Color.RED);
	    handWritingFontBLACK = FontFactory.createFromAsset(this.getFontManager(),fontTexture,this.getAssets(),"strato-unlinked.ttf",28f,true,Color.BLACK);
	    
	    getEngine().getTextureManager().loadTexture(fontTexture);
	    
	    getEngine().getFontManager().loadFont(handWritingFontWHITE);
	    getEngine().getFontManager().loadFont(handWritingFontRED);
	    getEngine().getFontManager().loadFont(handWritingFontBLACK);

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

