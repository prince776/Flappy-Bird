package dev.prince.flappybird.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
	
	//HELPER FUNCTIONS
	
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static BufferedImage crop(BufferedImage sheet,int x,int y,int w,int h){
		return sheet.getSubimage(x, y, w, h);
	}
	
	//VARIABLES
	public static BufferedImage sheet,birdSheet,pipes,number;
	public static BufferedImage backgroundDay,backgroundNight,ground,pipeTail,pipeHead,homeImage,getReady,logo;
	
	public static BufferedImage[] bird;
	public static BufferedImage[] numbers;
	
	public static void init(){
		//INITIALIZATIONS
		sheet=loadImage("/textures/sheet.png");
		birdSheet=loadImage("/textures/bird.png");
		pipes = loadImage("/textures/pipes.png");
		number = loadImage("/textures/numbers.png");
		
		//images
		backgroundDay=crop(sheet,0,0,144,256);
		backgroundNight=crop(sheet,144,0,144,256);
		ground=crop(sheet,144,256,168*2,56);
		pipeTail=crop(pipes,1,0,24,148);
		pipeHead=crop(pipes,0,148,26,12);
		
		//menu images
		homeImage = crop(sheet,292,90,57,49);
		getReady = crop(sheet,295,58,92,25);
		logo=crop(sheet,350,89,90,25);
		
		//bird
		bird = new BufferedImage[4];
		
		bird = getArray(bird,birdSheet,0,1,4,17,12);
		
		//numbers
		numbers = new BufferedImage[10];
		
		numbers = getArray(numbers,number,0,0,10,12,18);
		
	}
	
	
	public static BufferedImage[] getArray(BufferedImage[] array,BufferedImage sheet,int x,int y,int length,int width,int height){
		for(int i=x;i<x+length;i++){
			array[i-x]=crop(sheet,i*width,y*height,width,height);
		}
		return array;
	}
	
}
