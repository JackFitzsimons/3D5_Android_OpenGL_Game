package com.example.andenginefirsttrial;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Present extends Sprite {
	
	ITextureRegion t[] = new TextureRegion[3];
	int color=1;
	int flashOn=-1;

	public Present(float pX, float pY, ITextureRegion pTextureRegion1, ITextureRegion pTextureRegion2, ITextureRegion pTextureRegion3, VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pTextureRegion1, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
		t[0]=pTextureRegion1;
		t[1]=pTextureRegion2;
		t[2]=pTextureRegion3;

	}

	public void changeTexture(int i){
		color = i;
		this.mTextureRegion = t[i];
		this.setBlendingEnabled(true);
		this.initBlendFunction(mTextureRegion);
		
		this.onUpdateVertices();
		this.onUpdateColor();
		this.onUpdateTextureCoordinates();
		if(i==2)
			flashOn=5;
	}
	
	public int getColorType(){
		return color;
	}
	
	public void downFlash(){
		if(flashOn>=0){
			flashOn--;
			if(flashOn ==0){
				this.changeTexture(0);
			}
		}
	}

}
