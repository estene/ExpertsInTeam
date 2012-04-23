package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;


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
	
	/**
	 * Constructor 
	 * @param placement - what crossing is it located at
	 * @param x - x position to place light
	 * @param y - y position to place light
	 */
	// Takes x and y coordinates from traffic light to ease placement
	public PedestrianLight(Placement placement, int x , int y){
		this.myColour = LightColour.RED;
		this.isGreen = false;
		this.placement = placement;
		classLoader = Thread.currentThread().getContextClassLoader();
		pedSensor = new TLSensor();
		this.x = x;
		this.y = y;
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
	
	public void buttonPushed(){
		if(myColour == LightColour.RED){
			// Request colour change from OPController
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null); 
	}
	
	public void changeColour(){
		if(myColour == LightColour.RED && this.placement.equals(Placement.SOUTH)){
			this.image = getImage("greenman.png");
			this.myColour = LightColour.GREEN;
			this.isGreen = true;
		} else {
			this.image = getImage("redman.png");
			this.myColour = LightColour.RED;
			this.isGreen = false;
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
