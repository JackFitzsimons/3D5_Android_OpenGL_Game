package com.example.andenginefirsttrial;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Present extends Sprite {
	

	// ===========================================================
	// Global variables
	// ===========================================================
	ITextureRegion t[] = new TextureRegion[3]; //different present images
	int color=1;
	int flashOn=-1;


	// ===========================================================
	// Constructor
	// ===========================================================
	public Present(float pX, float pY, ITextureRegion pTextureRegion1, ITextureRegion pTextureRegion2, ITextureRegion pTextureRegion3, VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pTextureRegion1, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		t[0]=pTextureRegion1;	//the three textures to change between
		t[1]=pTextureRegion2;
		t[2]=pTextureRegion3;

	}


	// ===========================================================
	// Methods
	// ===========================================================
	
	//switch between the different textures
	public void changeTexture(int i){
		color = i;
		this.mTextureRegion = t[i];
		
		//this next part is needed due to how sprites are managed in AndEngine
		
		this.setBlendingEnabled(true);
		this.initBlendFunction(mTextureRegion);
		
		this.onUpdateVertices();
		this.onUpdateColor();
		this.onUpdateTextureCoordinates();
		if(i==2)
			flashOn=5;
	}

	//check what colour is visible
	public int getColorType(){
		return color;
	}
	
	//after touched we want the present to flash yellow and then back to white, this makes sure it gets back to white
	public void downFlash(){
		if(flashOn>=0){
			flashOn--;
			if(flashOn ==0){
				this.changeTexture(0);
			}
		}
	}

}
