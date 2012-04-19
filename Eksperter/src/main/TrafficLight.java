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
			pedLight1 = new PedestrianLight(Placement.SOUTH, 260,275);
			pedLight2 = new PedestrianLight(Placement.WEST, 280, 288);
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
        	// TODO : Finish these to check for what image should be changed to what
        	if (fromSouthToWest.equals(LightColour.RED)) {
        		image2 = getImage("redcurvedleft.png");
        	}
        	if (fromSouthToNorth.equals(LightColour.RED)) {
        		image = getImage("redup.png");
        	}
         }  
     }  
    
    class RedToGreen extends TimerTask  {  
        public void run ( )   {  
        	System.out.println(fromSouthToNorth);
        	System.out.println(fromSouthToWest);
        	if (fromSouthToWest.equals(LightColour.GREEN)) {
        		image2 = getImage("greencurvedleft.png");
        	}
        	if (fromSouthToNorth.equals(LightColour.GREEN)) {
        		image = getImage("greenup.png");
        	}
         }  
     }  
    public void toYellow() {
    	// TODO Same if's as above to check for what image to use
    	//image = getImage("yellowup.png");
    }
    
	public synchronized void changeColour(Direction dir, LightColour lC){
		switch (dir) {
		case FROMSOUTHTONORTH:

			this.fromSouthToNorth = LightColour.YELLOW;
//			this.fromNorthToSouth = LightColour.YELLOW;
//			this.fromNorthToWest = LightColour.YELLOW;
			if(this.fromSouthToNorth != lC){
				if(lC == LightColour.GREEN){
					this.fromSouthToNorth = LightColour.GREEN;
					if (this.placement.equals(Placement.SOUTHEAST)) {
						toYellow();
						timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					this.fromNorthToSouth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}
				else {
					this.fromSouthToNorth = LightColour.RED;
					if (this.placement.equals(Placement.SOUTHEAST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromNorthToSouth = LightColour.GREEN;
//					this.fromNorthToWest = LightColour.GREEN;
				}
			}
			break;

		case FROMSOUTHTOWEST:
//			this.fromSouthToNorth = LightColour.YELLOW;
			this.fromSouthToWest = LightColour.YELLOW;
//			this.fromWestToSouth = LightColour.YELLOW;
			if(this.fromSouthToWest != lC){
				if(lC == LightColour.GREEN){
					this.fromSouthToWest = LightColour.GREEN;
					if (this.placement.equals(Placement.SOUTHEAST)) {
						toYellow();
						//timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					trySleep(LightColour.GREEN);
//					this.fromWestToSouth = LightColour.RED;
//					this.fromSouthToNorth = LightColour.RED;
				}
				else {
					this.fromSouthToWest = LightColour.RED;
					if (this.placement.equals(Placement.SOUTHEAST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromWestToSouth = LightColour.GREEN;
//					this.fromSouthToNorth = LightColour.GREEN;
				}			
			}
			break;

		case FROMWESTTOSOUTH:
//			this.fromSouthToNorth = LightColour.YELLOW;
//			this.fromSouthToWest = LightColour.YELLOW;
			this.fromWestToSouth = LightColour.YELLOW;
			if(this.fromWestToSouth != lC){
				if(lC == LightColour.GREEN){
					this.fromWestToSouth = LightColour.GREEN;
					if (this.placement.equals(Placement.SOUTHWEST)) {
						toYellow();
						//timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					this.fromSouthToWest = LightColour.RED;
//					this.fromSouthToNorth = LightColour.RED;
				}
				else{
					this.fromWestToSouth = LightColour.RED;
					if (this.placement.equals(Placement.SOUTHWEST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromSouthToWest = LightColour.GREEN;
//					this.fromSouthToNorth = LightColour.GREEN;
				}
			}
			break;
			
		case FROMWESTTONORTH:
//			this.fromNorthToWest = LightColour.YELLOW;
			this.fromWestToNorth = LightColour.YELLOW;
			if(this.fromWestToNorth != lC){
				if(lC == LightColour.GREEN){
					this.fromWestToNorth = LightColour.GREEN;
					if (this.placement.equals(Placement.SOUTHWEST)) {
						toYellow();
						//timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					this.fromNorthToWest = LightColour.RED;
				}
				else{
					this.fromWestToNorth = LightColour.RED;
					if (this.placement.equals(Placement.SOUTHWEST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromNorthToWest = LightColour.GREEN;
				}
			}
			break;
			
		case FROMNORTHTOSOUTH:
//			this.fromNorthToWest = LightColour.YELLOW;
//			this.fromSouthToNorth = LightColour.YELLOW;
			this.fromNorthToSouth= LightColour.YELLOW;
			if(this.fromNorthToSouth != lC){
				if(lC == LightColour.GREEN){
					this.fromNorthToSouth = LightColour.GREEN;
					if (this.placement.equals(Placement.NORTHWEST)) {
						toYellow();
						//timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					this.fromSouthToNorth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}				
				else{
					this.fromNorthToSouth = LightColour.RED;
					if (this.placement.equals(Placement.NORTHWEST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromSouthToNorth = LightColour.GREEN;
//					this.fromNorthToWest = LightColour.GREEN;
				}
			}
			break;

		case FROMNORTHTOWEST:
//			this.fromWestToNorth = LightColour.YELLOW;
			this.fromNorthToWest = LightColour.YELLOW;
			if(this.fromNorthToWest != lC){
				if(lC == LightColour.GREEN){
					this.fromNorthToWest = LightColour.GREEN;
					if (this.placement.equals(Placement.NORTHWEST)) {
						toYellow();
						//timer.schedule ( new RedToGreen() , 1000 ) ;
					}
//					this.fromWestToNorth = LightColour.RED;
				}				
				else{
					this.fromNorthToWest = LightColour.RED;
					if (this.placement.equals(Placement.NORTHWEST)) {
						toYellow();
						timer.schedule ( new GreenToRed() , 1000 ) ;
					}
//					this.fromWestToNorth = LightColour.GREEN;
				}
			}
			break;
		}
	}
	
	public void changePedLight(Direction dir){
		if(this.placement == Placement.NORTHEAST && dir == Direction.FROMNORTHTOSOUTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && dir == Direction.FROMNORTHTOSOUTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && dir == Direction.FROMWESTTONORTH){
			this.pedLight2.changeColour();
		}else
		if(this.placement == Placement.SOUTHWEST && dir == Direction.FROMWESTTONORTH){
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
