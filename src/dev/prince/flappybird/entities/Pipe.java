package dev.prince.flappybird.entities;

import java.awt.Graphics;

import dev.prince.flappybird.Handler;
import dev.prince.flappybird.gfx.Assets;
import dev.prince.flappybird.worlds.World;

public class Pipe extends Entity {
	
	public static int pipeGap=130;
	
	
	public int newHeight=0;
	
	int count=1;
	
	
	
	public Pipe(Handler handler,int x,int y,int width,int height) {
		super(handler);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=100;
	}

	@Override
	public void tick() {
		x-=2;
		if(this.x<0){
			x+=150;
			this.height=handler.getWorld().getHeights().get(count);
			count++;
		}
	}

	@Override
	public void render(Graphics g) {
		
	}
	
	public void render(Graphics g , int x ,int y ,int width , int height){
		
		
		
		if(!(x<-width)){
		//Draw Top Pipe 
		g.drawImage(Assets.pipeTail, x,y,width,height,null);//Draw Tail
		g.drawImage(Assets.pipeHead, x-width/10,y+height,width+width/5,24,null);//Draw Head
		
		//Draw Bottom Pipe
		g.drawImage(Assets.pipeHead, x-width/10,y+height+pipeGap+24,width+width/5,24,null);//Draw Head
		g.drawImage(Assets.pipeTail, x,y+height+24+pipeGap+24,width,handler.getHeight()-y-height-24-pipeGap-24-World.groundHeight,null);//Draw Tail
		}
	}
	
	public void setHeight(int h){
		this.height=h;
	}
	
	public  void setX(int a){
		x=a;
	}
	
	public void setCount(int a){
		count=a;
	}
	
}
