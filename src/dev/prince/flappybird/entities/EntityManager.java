package dev.prince.flappybird.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.prince.flappybird.Handler;

public class EntityManager {
	
	private Handler handler;
	
	public ArrayList<Entity> entities;
	private Player player;
	
	
	
	public EntityManager(Handler handler){
		this.handler=handler;
		entities=new ArrayList<Entity>();         
		player=new Player(handler,handler.getWidth()/4,handler.getHeight()/2-30,17*2,12*2);
	}
	
	
	public void tick(){
		
		player.tick();

		if(!player.hasCollided){
			
			for(Entity e:entities){
				
				if( !(e.x < -e.width)){
					e.tick();
				}
			
			}
		}
   
	}
	
	

	public void render(Graphics g){
		    
		for(Entity e:entities){
			
			
			
			if( !(e.x < -e.width)){
				e.render(g);
			}
		}
		player.render(g);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	
}
