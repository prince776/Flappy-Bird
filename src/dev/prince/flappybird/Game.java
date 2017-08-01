 package dev.prince.flappybird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.prince.flappybird.display.Display;
import dev.prince.flappybird.gfx.Assets;
import dev.prince.flappybird.input.KeyManager;
import dev.prince.flappybird.sounds.Sounds;
import dev.prince.flappybird.states.GameState;
import dev.prince.flappybird.states.MenuState;
import dev.prince.flappybird.states.State;

public class Game implements Runnable{
	
	//BASIC VARIABLES
	private String title;
	private int width,height;
	
	//DISPLAY
	private Display display;
	
	//THREAD VARIABLES
	private Thread thread;
	private boolean running=false;
	
	//RENDER VARIABLES
	private BufferStrategy bs;
	private Graphics g;
	
	//HANDLER
	private Handler handler;
	
	//STATES
	private GameState gameState;
	private MenuState menuState;
	
	//INPUT
	private KeyManager keyManager;
	
	public Game(String title , int width , int height){
		this.title=title;
		this.width=width;
		this.height=height;
	}
	
	public void init(){
		display=new Display(title,width,height);
		handler=new Handler(this);
		Assets.init();
		Sounds.init();
		gameState=new GameState(handler);
		menuState=new MenuState(handler);
		keyManager=new KeyManager();
		State.setState(menuState);
		
		//SET LISTENERS
		display.getFrame().addKeyListener(keyManager);
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread=new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		
		//FPS
		int fps=60;
		double nsPerTick=1000000000/fps;
		long lastTime=System.nanoTime();
		long now=0;
		double delta=0;
		long timer=0;
		int frames=0;
		
		
		while(running){
			
			//FPS
			now=System.nanoTime();
			delta+=(now-lastTime)/nsPerTick;
			timer+=now-lastTime;
			lastTime=now;
			
			if(delta>=1){
			tick();
			
			delta--;
			}
			render();
			frames++;
			if(timer>=1000000000){
				timer=0;
				display.getFrame().setTitle(title+" with "+frames+" FPS");
				frames=0;
			}
		}
		stop();
	}
	
	public void tick(){
		
		keyManager.tick();
		
		if(State.getState()!=null){
			State.getState().tick();
		}
		
	}
	
	public void render(){
		bs=display.getCanvas().getBufferStrategy();
		if(bs==null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		
		//CLEAR RECT
		g.clearRect(0, 0, width, height);
		//RENDER START
		
		
		//offScreen.drawImage(Assets.backgroundDay, 0, 0, width, height, null);
		//offScreen.drawImage(Assets.ground,-10, 430, 168*2, 56*2, null);
		
		if(State.getState()!=null){
			State.getState().render(g);
		}
		g.setFont(new Font("Vernada",Font.PLAIN,15));
		g.drawString("Developed By - Prince Gupta", 20, 20);

		
		//RENDER END
		
		bs.show();
		g.dispose();
		
	}
	
	public synchronized void stop()  {
		if(!running)
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//GETTERS
	
	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameState getGameState() {
		return gameState;
	}

	public MenuState getMenuState() {
		return menuState;
	} 
	
	
	
}
