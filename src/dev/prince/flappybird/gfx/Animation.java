package dev.prince.flappybird.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private long lastTime,now,timer;
	private int speed=0,frames=0;
	private  BufferedImage[] image;
	
	public Animation(BufferedImage image[],int speed){
		this.image=image;
		lastTime=System.currentTimeMillis();
		this.speed=speed;
	}
	
	public void tick(){
		now=System.currentTimeMillis();
		timer+=now-lastTime;
		lastTime=now;
		
		if(timer>=speed){
			timer=0;
			frames++;
		}
		
		if (frames>=image.length){
			frames=0;
		}
		
	}
	
	public BufferedImage getCurrentFrame(){
		return image[frames];
	}
	
}
