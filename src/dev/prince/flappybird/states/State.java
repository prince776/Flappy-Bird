package dev.prince.flappybird.states;

import java.awt.Graphics;

import dev.prince.flappybird.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler=handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	/////STATE MANAGER CODE////////////////
	private static State currentState;
	
	public static State getState(){
		return currentState;
	}
	
	public static void setState(State s){
		currentState=s;
	}
	
}
