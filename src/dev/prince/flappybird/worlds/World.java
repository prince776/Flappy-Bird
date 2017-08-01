package dev.prince.flappybird.worlds;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import dev.prince.flappybird.Handler;
import dev.prince.flappybird.entities.EntityManager;
import dev.prince.flappybird.entities.Pipe;
import dev.prince.flappybird.entities.Player;
import dev.prince.flappybird.gfx.Assets;

public class World {
	
	private Handler handler;
	private Random random;

	public static int  groundWidth=180*2,groundHeight=50*2 ;
	private int x1=-10,x=450;
	private Pipe pipe;
	private int widthPipe=50;
	
	public int renderCount=0;
	
	public ArrayList<Integer> heights;
	

	
	//ENTITY MANAGER
	EntityManager entityManager;
	
	public World(Handler handler){
		this.handler=handler;
		entityManager=new EntityManager(handler);
		heights=new ArrayList<Integer>();
		heights.add(100);
		pipe=new Pipe(handler,x,0,50,100);
		entityManager.addEntity(pipe);		random = new Random(System.nanoTime());

	}
	
	public void tick(){
		if(!entityManager.getPlayer().hasCollided){
			moveGround();
		}
		if(!entityManager.getPlayer().hasCollided)
			x-=2;
		
		entityManager.tick();
	}
	
	
	
	public void render(Graphics g){
		g.drawImage(Assets.backgroundDay, -10, 0, handler.getWidth()+10,handler.getHeight(),null);
		g.drawImage(Assets.ground,	x1 , handler.getHeight()-groundHeight , groundWidth , groundHeight , null);
		for(int i=x;i<handler.getWidth();i+=widthPipe*3){
			//if(x>=-widthPipe*3){
			heights.add(random.nextInt(handler.getHeight()-groundHeight-24-24-24-Pipe.pipeGap)+24);
			pipe.render(g, i, 0, widthPipe, heights.get(renderCount));
			renderCount++;
			//}
		}
		entityManager.render(g);
		

		renderCount=0;
	}
	
	public void moveGround(){
		x1-=2;
		if(x1<=-(groundWidth-handler.getWidth())+Player.pWidth+2){
			x1=-5;
		}
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public ArrayList<Integer> getHeights() {
		return heights;
	}
	
	
	public void setX(int a){
		this.x=a;
	}

	public Pipe getPipe() {
		return pipe;
	}
	
	
	public void makeNewRandom(long a){
		random=new Random(a);
	}
	
	
}
