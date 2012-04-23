package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

/**
 * This class represents a single trafficlight in the overpass. Every light and placement is 
 * based on that the road towards Studentersamfunnet is "South". 
 * 
 * @author cato
 *
 */

public class TrafficLight{
	private final int greenToRedSleepTime = 1000;	
	private final int redToGreenSleepTime = 4000;	
	private Image image, image2;
	private ClassLoader classLoader;
	private int x,y,x2,y2;
	Timer timer;
	
	protected LightColour fromSouthToNorth;
	protected LightColour fromSouthToWest;
	
	protected LightColour fromNorthToSouth;
	protected LightColour fromNorthToWest;			

	protected LightColour fromWestToNorth;
	protected LightColour fromWestToSouth;
	
	private PedestrianLight pedLight1 = null;
	private PedestrianLight pedLight2 = null; // pedLight2 will always be the crossing on the west road.
			
	private final Placement placement;
	
	public TrafficLight(Placement placement){
		timer = new Timer();
		this.placement = placement;		
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("");
		image2 = getImage("");
		
		
		
		if(placement == Placement.SOUTHEAST) {
			image = getImage("redup.png");
			image2 = getImage("redcurvedleft.png");
			x = 465;
			y = 320;
			x2 = 493;
			y2 = 320;
			pedLight1 = new PedestrianLight(Placement.SOUTH, 477,280);
			
		}
		else if(placement == Placement.SOUTHWEST){
			image = getImage("reddownleft.png");
			image2 = getImage("reddownright.png");
			x = 235;
			y = 268;
			x2 = 235;
			y2 = 300;
			pedLight1 = new PedestrianLight(Placement.SOUTH, 280, 288);
			pedLight2 = new PedestrianLight(Placement.WEST, 260,275);
		}
		else if(placement == Placement.NORTHWEST){

			image = getImage("reddown.png");
			image2 = getImage("redleftdown.png");
			x = 290;
			x2 = 265;
			y = 32;
			y2 = 32;
			pedLight1 = new PedestrianLight(Placement.NORTH, 285, 60);
			pedLight2 = new PedestrianLight(Placement.WEST, 265, 77);

		}
		else if(placement == Placement.NORTHEAST)  {
			pedLight1 = new PedestrianLight(Placement.NORTH, 450, 55);
		}
				
		//initializes all the lights to red so that the underlying logic asdfadsgsdfb
		fromNorthToSouth = LightColour.RED;
		fromNorthToWest = LightColour.RED;
		
		fromSouthToNorth = LightColour.RED;
		fromSouthToWest = LightColour.RED;
		
		fromWestToNorth = LightColour.RED;
		fromWestToSouth = LightColour.RED;
	}
	
	public void draw(Graphics g) {
		if (pedLight1 != null) {
			pedLight1.draw(g);
		}
		if (pedLight2 != null) {
			pedLight2.draw(g);
		}
		
		
		g.drawImage(image, x, y, null); 
		g.drawImage(image2,x2,y2,null);
	}
	
    class GreenToRed extends TimerTask  {  
        public void run ( )   {  
        	if (fromSouthToWest.equals(LightColour.RED) && placement.equals(Placement.SOUTHEAST)) {
        		image2 = getImage("redcurvedleft.png");
        	}
        	if (fromSouthToNorth.equals(LightColour.RED) && placement.equals(Placement.SOUTHEAST)) {
        		image = getImage("redup.png");
        	}
        	if (fromWestToNorth.equals(LightColour.RED) && placement.equals(Placement.SOUTHWEST)) {
        		image = getImage("reddownleft.png");
        	}
        	if (fromWestToSouth.equals(LightColour.RED) && placement.equals(Placement.SOUTHWEST)) {
        		image2 = getImage("reddownright.png");
        	}
        	if (fromNorthToSouth.equals(LightColour.RED) && placement.equals(Placement.NORTHWEST)) {
        		image = getImage("reddown.png");
        	}
        	if (fromNorthToWest.equals(LightColour.RED) && placement.equals(Placement.NORTHWEST)) {
        		image2 = getImage("redleftdown.png");
        	}
         }  
     }  
    
    class RedToGreen extends TimerTask  {  
        public void run ( )   {  
        	if (fromSouthToWest.equals(LightColour.GREEN) && placement.equals(Placement.SOUTHEAST)) {
        			image2 = getImage("greencurvedleft.png");
        	}
        	if (fromSouthToNorth.equals(LightColour.GREEN) && placement.equals(Placement.SOUTHEAST)) {
        		image = getImage("greenup.png");
        	}
        	if (fromWestToNorth.equals(LightColour.GREEN) && placement.equals(Placement.SOUTHWEST)) {
        		image = getImage("greendownleft.png");
        	}
        	if (fromWestToSouth.equals(LightColour.GREEN) && placement.equals(Placement.SOUTHWEST)) {
        			image2 = getImage("greendownright.png");
        	}
        	if (fromNorthToSouth.equals(LightColour.GREEN) && placement.equals(Placement.NORTHWEST)) {
        		image = getImage("greendown.png");
        	}
        	if (fromNorthToWest.equals(LightColour.GREEN) && placement.equals(Placement.NORTHWEST)) {
        		image2 = getImage("greenleftdown.png");
        	}
         }  
     }  
    
    /**
     * To Yellow method - Is called whenever a light changes to yellow
     */
    public void toYellow() {
    	if (fromSouthToWest.equals(LightColour.YELLOW) && placement.equals(Placement.SOUTHEAST)) {
			image2 = getImage("yellowcurvedleft.png");
		}
		if (fromSouthToNorth.equals(LightColour.YELLOW)  && placement.equals(Placement.SOUTHEAST)) {
			image = getImage("yellowup.png");
		}
		if (fromWestToNorth.equals(LightColour.YELLOW)  && placement.equals(Placement.SOUTHWEST)) {
			image = getImage("yellowdownleft.png");
		}
		if (fromWestToSouth.equals(LightColour.YELLOW)  && placement.equals(Placement.SOUTHWEST)) {
			image2 = getImage("yellowdownright.png");
		}
		if (fromNorthToSouth.equals(LightColour.YELLOW)  && placement.equals(Placement.NORTHWEST)) {
			image = getImage("yellowdown.png");
		}
		if (fromNorthToWest.equals(LightColour.YELLOW)  && placement.equals(Placement.NORTHWEST)) {
			image2 = getImage("yellowleftdown.png");
	    }
    }
    /**
     * Method that changes colour of the lights according to:
     * @param dir - Direction of the bus
     * @param lC - What lightcolour the light should change to
     */
    
	public synchronized void changeColour(Direction dir, LightColour lC){
		switch (dir) {
		case FROMSOUTHTONORTH:
			if(this.fromSouthToNorth != lC){
				this.fromSouthToNorth = LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromSouthToNorth = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;
				}
				else {
					this.fromSouthToNorth = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}
			}
			break;

		case FROMSOUTHTOWEST:
			if(this.fromSouthToWest != lC){
				this.fromSouthToWest = LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromSouthToWest = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;

				}
				else {
					this.fromSouthToWest = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}			
			}
			break;

		case FROMWESTTOSOUTH:
			if(this.fromWestToSouth != lC){
				this.fromWestToSouth = LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromWestToSouth = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;
				}
				else{
					this.fromWestToSouth = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}
			}
			break;
			
		case FROMWESTTONORTH:
			if(this.fromWestToNorth != lC){
				this.fromWestToNorth = LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromWestToNorth = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;
				}
				else{
					this.fromWestToNorth = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}
			}
			break;
			
		case FROMNORTHTOSOUTH:
			if(this.fromNorthToSouth != lC){
				this.fromNorthToSouth= LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromNorthToSouth = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;
				}				
				else{
					this.fromNorthToSouth = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}
			}
			break;

		case FROMNORTHTOWEST:
			if(this.fromNorthToWest != lC){
				this.fromNorthToWest = LightColour.YELLOW;
				toYellow();
				if(lC == LightColour.GREEN){
					this.fromNorthToWest = LightColour.GREEN;
					timer.schedule ( new RedToGreen() , 1000 ) ;
				}				
				else{
					this.fromNorthToWest = LightColour.RED;
					timer.schedule ( new GreenToRed() , 1000 ) ;
				}
			}
			break;
		}
	}
	/**
	 * Something weird here!
	 * @param dir
	 */
	
	public void changePedLight(Direction dir){
		System.out.println(dir);
		if(this.placement == Placement.NORTHEAST && dir == Direction.FROMNORTHTOSOUTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && dir == Direction.FROMNORTHTOSOUTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && dir == Direction.FROMSOUTHTONORTHWEST){
			this.pedLight2.changeColour();
		}else
		if(this.placement == Placement.SOUTHWEST && dir == Direction.FROMSOUTHTONORTHWEST){
			this.pedLight2.changeColour();
		}else
		if(this.placement == Placement.SOUTHWEST && dir == Direction.FROMSOUTHTONORTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.SOUTHEAST && dir == Direction.FROMSOUTHTONORTH){
			this.pedLight1.changeColour();
		}
	}
	
	public PedestrianLight getPedLight1(){
		return this.pedLight1;
	}
	
	public PedestrianLight getPedLight2(){
		return this.pedLight2;
	}
	
	public TLSensor getPedL1S(){
		return this.pedLight1.getTLS();
	}
	
	public TLSensor getPedL2S(){
		return this.pedLight2.getTLS();
	}
	
	public Person getPeopleFromPedL1S(){
		return this.pedLight1.getPeople();
	}
	
	public Person getPeopleFromPedL2S(){
		return this.pedLight2.getPeople();
	}
	
	/**
	 * 
	 * @param imgName
	 * @return image 
	 */
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
