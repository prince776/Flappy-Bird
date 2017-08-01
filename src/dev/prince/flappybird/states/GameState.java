package dev.prince.flappybird.states;

import java.awt.Graphics;

import dev.prince.flappybird.Handler;
import dev.prince.flappybird.gfx.Assets;
import dev.prince.flappybird.worlds.World;

public class GameState extends State{
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world=new World(handler);
		handler.setWorld(world);

	}

	@Override
	public void tick() {
			world.tick();
			
	}

	@Override
	public void render(Graphics g) {
			world.render(g);
			int[] scores = breakInDigits(handler.getWorld().getEntityManager().getPlayer().score);
			for(int i=0;i<scores.length;i++){
					g.drawImage(Assets.numbers[scores[i]], handler.getWidth()/3+20+i*20, handler.getHeight()/6, 12*2, 18*2, null);	
			}
	}
	
	
	public int[] breakInDigits(int num){
		String temp = Integer.toString(num);
		String[] numS;
		int[] nums = new int[temp.length()];
		numS=temp.split("");
		
		for(int i=0;i<temp.length();i++){
			nums[i]=Integer.parseInt(numS[i]);
		}
		return nums;
	}
	

}
