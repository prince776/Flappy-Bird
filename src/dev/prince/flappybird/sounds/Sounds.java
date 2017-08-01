package dev.prince.flappybird.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sounds {
	//init() it in game class
	public static AudioClip wing,hit,die,point,swoosh;
	
	public static void init(){
		wing = loadSound("/sounds/wing.wav");
		hit = loadSound("/sounds/hit.wav");
		die = loadSound("/sounds/die.wav");
		point = loadSound("/sounds/point.wav");
		swoosh = loadSound("/sounds/swoosh.wav");
	}
	
	public static AudioClip loadSound(String path){
		return Applet.newAudioClip(Sounds.class.getResource(path));
	}
	
	public static void playSound(AudioClip c){
		Thread thread = new Thread();
		thread.start();
		c.play();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
