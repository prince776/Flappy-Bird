package dev.prince.flappybird.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.prince.flappybird.Handler;
import dev.prince.flappybird.gfx.Animation;
import dev.prince.flappybird.gfx.Assets;
import dev.prince.flappybird.sounds.Sounds;
import dev.prince.flappybird.states.State;
import dev.prince.flappybird.worlds.World;

public class Player extends Entity{

	private float yMove=0,speed=10  ,gravityConstant=2f;
	private float accn=0;
	
	public Animation animation;
	
	public boolean hasCollided=false,wing=false,hit=true;
	
	public static int pWidth=0; 
	
	public static Rectangle boundsN;
	
	public int score=0;
	public boolean increase=true;
	
	
	Graphics2D g2D;
	
	public Player(Handler handler, int x, int y, int width, int height) {
		super(handler);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		animation=new Animation(Assets.bird,100);
		bounds = new Rectangle(x,y,width,height);
		boundsN=bounds;
		pWidth=width;
	}

	

	@Override
	public void tick() {
		
		///////////////SOUNDS/////////////////////
		
		///////flap sound//////
		if(!handler.getKeyManager().jumped){
			wing=true;
		}
		
		if(handler.getKeyManager().jumped){
			if(wing){
				Sounds.playSound(Sounds.wing);
			}
			wing=false;
		}
		//////hit sound///////
		if(hasCollided || y>=handler.getHeight()-World.groundHeight-24){
			if(hit){
				Sounds.playSound(Sounds.die);
				Sounds.playSound(Sounds.hit);
				}
			hit=false;
		}
		
		
		////////////////////////////////////////
		
		if(!hasCollided)
			animation.tick();  
		if(checkEntityCollision(0f,yMove+25)||y>=handler.getHeight()-World.groundHeight-24){
			hasCollided=true;
			
		}
		if(!hasCollided){
			move();
			if(increase){
				if(this.x>handler.getWorld().getPipe().x){
					score++;
					Sounds.playSound(Sounds.point);
					increase=false;
				}
			}
			if(this.x> handler.getWorld().getPipe().x+handler.getWorld().getPipe().width+20){
				increase=true;
			}	
		}
		if(hasCollided){
			y+=gravityConstant+accn;
			accn+=0.2f;
			if(y>=handler.getHeight()-World.groundHeight-24){
				y=handler.getHeight()-World.groundHeight-24;
				if(handler.getKeyManager().enter){
					handler.getKeyManager().enter=false;
					resetStuff();
				}
			}
		}
	}

	
	@Override 
	public void render(Graphics g) {
		//if(yMove>0)
		g2D = (Graphics2D)g;
		/*if(yMove<0){
			g2D.rotate(Math.toRadians(-25), x, y);
		}
		else{
			g2D.rotate(Math.toRadians(70), x, y);
		}
		*/
		g2D.drawImage(getCurrentAnimationFrame(),x,y,width,height,null);
		
	}
	public void render(Graphics g,int x1,int y1) {
		g.drawImage(getCurrentAnimationFrame(), x1, y1, width, height, null);
	}
	
	
	public void move(){
		//detect the event in 0.5s and change yMove to 0 in later 0.5s
		if(!handler.getKeyManager().jumped){
			getInput();
		}
		y+=yMove;
		yMove+=0.8f;
		if(yMove>=0){
			yMove=0;
		}
		accn+=0.2f;
		if(yMove!=0){
			accn=0;
		}
		y+=gravityConstant+accn;
		
		
		if(y>=handler.getHeight()-World.groundHeight-20){
			y=handler.getHeight()-World.groundHeight-20;
		}
		if(y<-30){
			y=-30;
		}
		
	}
	
	
	public void getInput(){
		
		
		if(handler.getKeyManager().jump){
			yMove=-speed;
			handler.getKeyManager().jumped=true;
		}
		
	}
	
	public BufferedImage getCurrentAnimationFrame(){
			
		return animation.getCurrentFrame();
		
	}

	
	public void setY(int a){
		this.y=a;
	}
	
	public void resetStuff(){
		setY(handler.getHeight()/2-30);
		handler.getWorld().setX(450);
		handler.getWorld().getPipe().setX(450);
		handler.getWorld().getPipe().setCount(1);
		handler.getWorld().heights.clear();
		handler.getWorld().heights.add(100);
		handler.getWorld().getPipe().setHeight(100);

		handler.getWorld().renderCount=0;
		score=0;
		increase=true;
		hasCollided=false;
		hit=true;
		State.setState(handler.getGame().getMenuState());
		handler.getKeyManager().jump=false;

	}
	
	
}
