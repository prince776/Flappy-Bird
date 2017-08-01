package dev.prince.flappybird.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.prince.flappybird.Handler;

public abstract class Entity {
	
	public int x,y,width,height;
	protected Handler handler;
	protected Rectangle bounds;
	
	public Entity(Handler handler){
		this.handler=handler;
		
		bounds=new Rectangle(x,y,width,height);
	}
	
	//////ENTITY COLLISION///////////////
	
	public boolean checkEntityCollision(float xOffset , float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().entities){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds2(0f, 0f,e.height+24+24).intersects(getCollisionBounds(xOffset,yOffset))){// || e.getCollisionBounds2(0f,height+24+Pipe.pipeGap,handler.getHeight()-y-height-24-Pipe.pipeGap-24-World.groundHeight).intersects(getCollisionBounds(xOffset,yOffset))){
				return true;
			}
			if(e.getCollisionBounds2(0f, e.height+24+Pipe.pipeGap,handler.getHeight()-e.y-e.height-24-Pipe.pipeGap).intersects(getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset,float yOffset){
		return new Rectangle((int)(x+xOffset),(int)(y+yOffset),width,height);
	}
	
	public Rectangle getCollisionBounds2(float xOffset,float yOffset,int heightN){
		return new Rectangle((int)(x+xOffset),(int)(y+yOffset),width,heightN);
	}
	
	//ABSTRACT METHODS
	public abstract void tick();
	public abstract void render(Graphics g);

}
