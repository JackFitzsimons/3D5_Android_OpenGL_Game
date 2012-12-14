package com.example.andenginefirsttrial;

import java.util.Random;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

public class SnowFlake extends Sprite{
	public float scale=1;
	private float SH, SW;
	private Random r= new Random();
	int rot;
	
	public SnowFlake(float a, float b, ITextureRegion tr, VertexBufferObjectManager v, float ScreenHeight, float ScreenWidth){
		super(a,b,tr,v);
		SH=ScreenHeight;
		SW = ScreenWidth;
		this.setPosition(SW*r.nextFloat(), SH*r.nextFloat());
		rot = r.nextInt(2);
		scale=(r.nextFloat() + 0.5f)/2;
		this.setScaleX(scale);
		this.setScaleY(scale);
	}
	
	
	void setNewPosition(){
		this.setPosition(SW*(r.nextFloat()), -this.getHeight());
	}
	void setStartPosition(){
		this.setPosition(SW*(r.nextFloat()), SH*(r.nextFloat()));
	}
	public void setScale(){
		scale=(r.nextFloat() + 0.5f)/2;
		this.setScaleX(scale);
		this.setScaleY(scale);
		this.setPosition(SW*(r.nextFloat()), (SH*r.nextFloat()));
	}
	
	public void move(){
		if(rot==1)
			this.setRotation(this.getRotation() + scale);
		else
			this.setRotation(this.getRotation() - scale);
		
		float y = this.getY();
		if(y>=SH) {
			this.setScale();
			this.setNewPosition();
			y = this.getY();
		}
		this.setPosition(this.getX(), y+(scale*2));
	}

	
	
}
