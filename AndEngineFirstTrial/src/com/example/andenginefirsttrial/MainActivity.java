package com.example.andenginefirsttrial;

//importing required libraries
import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;



/* main controlling class*/
public class MainActivity extends SimpleBaseGameActivity {
		
	// ===========================================================
	// Global Variables we wish to access from different areas of the game
	// ===========================================================
	
	static final int CAMERA_WIDTH = 800;
	
	static final int CAMERA_HEIGHT = 480;

	static final String filename = "savedScores"; //for saying the file name to save our sharedPreferences
	
	private SharedPreferences prefs;
	 	
	public TimerActivity time;
	long t=0;
	
	public int highScores[] = new int[3];

	public Font mFont;
	
	public Camera mCamera;
	public Music music;
	    
	public Scene mCurrentScene;
// create an instance of main activity, call it instance	
	public static MainActivity instance;
	
	//declare a char sequence to be used in 
	public CharSequence scoreString;
	public int T;
	
	public int score;
	
	Boolean AlreadyRunning= false;
	
	//creates an instance of each scene in the game allowing each to be accessed at any time
	public GameOverScene GOS;
	public GameScene GS;
	public SplashScreen SS;
	public MainMenuScene MMS;
	public TopScoreScene TS;
	
	public Font handWritingFontWHITE;
	public Font handWritingFontRED;
	public Font handWritingFontBLACK;
	public ITexture fontTexture1;
	public ITexture fontTexture2;
	public ITexture fontTexture3;
	
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
	
	public TextureRegion mScoreSprite;
	
	public BitmapTextureAtlas mSantaS;
	public TextureRegion mSantasSleigh;
	
	public BitmapTextureAtlas mheader;
	public TextureRegion mGamePlayHeader;
	
	public SnowFlake mSnowSprite[] = new SnowFlake[14];
	
	// ===========================================================
	// Methods
	// ===========================================================
  
	//set up the AndEngine Options
	@Override
	public EngineOptions onCreateEngineOptions() {
	    instance = this;
	    mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    
	    EngineOptions engine_options = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
	    engine_options.getAudioOptions().setNeedsMusic(true);
	    return engine_options;
	}

	//creates all the textures and music ect from assets and starts music playing
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

	    mScoreSprite = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "snow_flakes7.png", 128, 192);
	    		
	    mBitmapTextureAtlas.load();
	    mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256,Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL), 32);
	    mFont.load();
	    
	    mSantaS = new BitmapTextureAtlas(this.getTextureManager(), 512, 256);
	    mSantasSleigh = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mSantaS, this, "santas_sleigh.png", 0, 0);

	    mSantaS.load();
	    
	    mheader = new BitmapTextureAtlas(this.getTextureManager(), 800, 70);
	    mGamePlayHeader = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mheader, this, "game_play_header.png", 0, 0);

	    mheader.load();
	    
	    fontTexture1 = new BitmapTextureAtlas(this.getTextureManager(),1024,1024);
	    fontTexture2 = new BitmapTextureAtlas(this.getTextureManager(),1024,1024);
	    fontTexture3 = new BitmapTextureAtlas(this.getTextureManager(),1024,1024);

	    handWritingFontWHITE = FontFactory.createFromAsset(this.getFontManager(),fontTexture1,this.getAssets(),"strato-unlinked.ttf",20f,true,Color.WHITE);
	    handWritingFontRED = FontFactory.createFromAsset(this.getFontManager(),fontTexture2,this.getAssets(),"strato-unlinked.ttf",28f,true,Color.RED);
	    handWritingFontBLACK = FontFactory.createFromAsset(this.getFontManager(),fontTexture3,this.getAssets(),"strato-unlinked.ttf",28f,true,Color.BLACK);
	    
	    getEngine().getTextureManager().loadTexture(fontTexture1);
	    getEngine().getTextureManager().loadTexture(fontTexture2);
	    getEngine().getTextureManager().loadTexture(fontTexture3);
	    
	    getEngine().getFontManager().loadFont(handWritingFontWHITE);
	    getEngine().getFontManager().loadFont(handWritingFontRED);
	    getEngine().getFontManager().loadFont(handWritingFontBLACK);

	  //setting preferences
	   
	    prefs = this.getSharedPreferences(filename, 0);
	    
	    try{prefs.getInt("MUTE", 3);}
	    catch(Exception e){
	    	prefs.edit().putInt("MUTE", 0).commit();
	    }
	    
	    try{
	    	
	    	this.music = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "It's Beginning to Look a Lot Like Christmas.ogg");
	    	this.music.setLooping(true);
	    	
	    	
	    }
	    catch(final IOException e){
	    	Debug.e("Error", e);
	    }
	    
	    if(checkMUTE() == 2){
    		music.pause();
    	}
	    
	}
	
	//checks if music is pause playing or not yet initialised
	int checkMUTE(){	// i = 1 2 or 3 for each place of interest
		return prefs.getInt("MUTE", 1);
	}
	
	//play to pause, pause to play
	void changeMUTE(){
		if(	prefs.getInt("MUTE", 3) == 1){
			prefs.edit().putInt("MUTE", 2).commit();
    		//music.pause();
		}
		else if(prefs.getInt("MUTE", 3) == 2){
			prefs.edit().putInt("MUTE", 1).commit();
			//music.play();
		}
		
		
	if(music.isPlaying()) {
			music.pause();
    } else {
    	music.play();
    }

	}
	
	//returns the top 3 scores from the shared preferences
	int getScores(int i){	// i = 1 2 or 3 for each place of interest
		return prefs.getInt("Place"+String.valueOf(i), -1);
	}

	//sets the top three scores
	void setScores(int i, int score){
		 Editor editor = prefs.edit();
		 editor.putInt("Place"+String.valueOf(i), score);
		 Log.w("Place"+String.valueOf(i), String.valueOf(score));
		 editor.commit();
	}

	//Initialize each screen, when called returns the splash screen
	protected Scene onCreateScene() {
		AlreadyRunning = true;
		time = new TimerActivity();

		mEngine.registerUpdateHandler(new FPSLogger());
		GOS= new GameOverScene() ;
		GS= new GameScene();
		SS= new SplashScreen();
		MMS= new MainMenuScene();
		TS = new TopScoreScene();

				
		mCurrentScene = SS;
		music.play();

		SS.SceneSetUp();
		//return mCurrentScene;
		return mCurrentScene;
	}

	//allows other scenes to get the shared MainActivity
	public static MainActivity getSharedInstance() {
	    return instance;
	}

	// to change the current main scene
	public void setCurrentScene(Scene scene) {
	    mCurrentScene = scene;
	    getEngine().setScene(mCurrentScene);
	}
	
	
	
	}

