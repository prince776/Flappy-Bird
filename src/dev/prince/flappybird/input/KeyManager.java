package dev.prince.flappybird.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	public boolean[] keys;
	
	public boolean up =false, down=false , left=false , right=false,jump=false,jumped=false,enter=false;
	
	public KeyManager(){
		keys= new boolean[512];
	}
	
	public void tick(){
		up=keys[KeyEvent.VK_UP];
		down=keys[KeyEvent.VK_DOWN];
		left=keys[KeyEvent.VK_LEFT];
		right=keys[KeyEvent.VK_RIGHT];
		jump=keys[KeyEvent.VK_SPACE];
		enter=keys[KeyEvent.VK_ENTER];
	}
	
	public void keyTyped(KeyEvent e) {
		//if(e.getKeyCode()==KeyEvent.VK_A){
		//	jump=true;
		//}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
		//if(e.getKeyCode()==KeyEvent.VK_SPACE){
		//	jumped = true;
		//}
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			jumped = false;
		}
	}

}
