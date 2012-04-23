package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import main.TrafficLight.RedToGreen;


/**
 * Class for the Pedestrian lights. 
 * @author even, cato
 *
 */
public class PedestrianLight {
	
	private LightColour myColour;
	private Placement placement;
	private TLSensor pedSensor;
	private boolean isGreen;
	private ClassLoader classLoader;
	private Image image;
	private int x,y;
	Timer timer;
	private int greenTimer;	
	
	/**
	 * Constructor 
	 * @param placement - what crossing is it located at
	 * @param x - x position to place light
	 * @param y - y position to place light
	 */
	// Takes x and y coordinates from traffic light to ease placement
	public PedestrianLight(Placement placement, int x , int y){
		greenTimer = 0;
		this.myColour = LightColour.RED;
		this.isGreen = false;
		this.placement = placement;
		classLoader = Thread.currentThread().getContextClassLoader();
		pedSensor = new TLSensor();
		this.x = x;
		this.y = y;
		timer = new Timer();
		image = getImage("redman.png");
	}
	
	public boolean isGreen(){
		return isGreen;
	}
	
	public TLSensor getTLS(){
		return pedSensor;
	}

	public int getPeopleAmount(){
		return pedSensor.getPersonQueueAmount();
	}
	
	public Person getPeople(){
		return pedSensor.getPeople();
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
		greenTimer++;
		if(greenTimer % 100 == 0){
			System.out.println(pedSensor.getPeople());
			//pedSensor.getPeople().increaseWaitTime(1);
		}
	}
	
	class RedToGreen extends TimerTask {
		public void run() {
			image = getImage("greenman.png");
			myColour = LightColour.GREEN;
			isGreen = true;
		}
	}
	class GreenToRed extends TimerTask {
		public void run() {
			image = getImage("redman.png");
			myColour = LightColour.RED;
			isGreen = false;
		}
	}
	
	
	public void changeColour(){
		if(myColour == LightColour.RED){
			timer.schedule ( new RedToGreen() , 1000 ) ;
		} else {
			timer.schedule ( new GreenToRed() , 1000 ) ;
		}
	}
	
	
	
	// Retrieve image from resource
	public Image getImage(String imgName) {
		InputStream input = classLoader.getResourceAsStream("" + imgName);
		Image inputImage = null;
		try {
			inputImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputImage;
	}
	
}
