package dev.prince.flappybird;

import dev.prince.flappybird.input.KeyManager;
import dev.prince.flappybird.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game=game;
	}
	
	public void setWorld(World world){
		this.world=world;
	}
	
	public World getWorld(){
		return this.world;
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public Game getGame(){
		return this.game;
	}
	
}
