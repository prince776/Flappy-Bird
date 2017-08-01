package dev.prince.flappybird.states;

import java.awt.Graphics;

import dev.prince.flappybird.Handler;
import dev.prince.flappybird.entities.Player;
import dev.prince.flappybird.gfx.Assets;
import dev.prince.flappybird.worlds.World;

public class MenuState extends State {
	
	private int x=-10;
	
	public MenuState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().jump){
			State.setState(handler.getGame().getGameState());
		}
		handler.getWorld().getEntityManager().getPlayer().animation.tick();
		
		x-=2;
		if(x<=-(World.groundWidth-handler.getWidth())+Player.pWidth+2){
			x=-5;
		}	
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.backgroundDay, 0, 0, handler.getWidth(),handler.getHeight(),null);
		g.drawImage(Assets.ground,	x , handler.getHeight()-World.groundHeight , World.groundWidth ,World.groundHeight  , null);
		g.drawImage(Assets.homeImage,handler.getWidth()/2-55,handler.getHeight()/2-49,55*2,49*2,null);
		g.drawImage(Assets.getReady, handler.getWidth()/2-70, handler.getHeight()/2-98, 150, 49,null);
		g.drawImage(Assets.logo, handler.getWidth()/2-100, handler.getHeight()/2-170, 200, 60, null);
		handler.getWorld().getEntityManager().getPlayer().render(g,handler.getWidth()/2-handler.getWorld().getEntityManager().getPlayer().width/2,handler.getHeight()/2-handler.getWorld().getEntityManager().getPlayer().height/2);
	}

}
