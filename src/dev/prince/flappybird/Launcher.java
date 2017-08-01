package dev.prince.flappybird;

public class Launcher {
	public static void main(String[] args) throws InterruptedException{
		Game game=new Game("Flappy Bird",288,512);
		game.start();		
	}
}
