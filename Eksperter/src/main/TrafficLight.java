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
	private Image image;
	private ClassLoader classLoader;
	private int x,y;
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
		image = getImage("reddown.png");
		
		if(placement == Placement.SOUTHEAST) {
			pedLight1 = new PedestrianLight(Placement.SOUTH);
			image = getImage("redleft.png");
			x = 487;
			y = 48;
		}
		else if(placement == Placement.SOUTHWEST){
			pedLight1 = new PedestrianLight(Placement.SOUTH);
			pedLight2 = new PedestrianLight(Placement.WEST);
			image = getImage("reddown.png");
			x = 290;
			y = 32;
		}
		else if(placement == Placement.NORTHWEST){
			pedLight1 = new PedestrianLight(Placement.NORTH);
			pedLight2 = new PedestrianLight(Placement.WEST);
			image = getImage("redright.png");
			x = 210;
			y = 265;
		}
		else if(placement == Placement.NORTHEAST)  {
			pedLight1 = new PedestrianLight(Placement.NORTH);
			image = getImage("redup.png");
			x = 475;
			y = 315;
		}
				
		//initializes all the lights to green so that the underlying logic asdfadsgsdfb
		fromNorthToSouth = LightColour.RED;
		fromNorthToWest = LightColour.RED;
		
		fromSouthToNorth = LightColour.RED;
		fromSouthToWest = LightColour.RED;
		
		fromWestToNorth = LightColour.RED;
		fromWestToSouth = LightColour.RED;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null); 
	}
	
    class GreenToRed extends TimerTask  {  
        public void run (  )   {  
            System.out.println ( "Change lights" ) ; 
            image = getImage("red24.png");
            timer.cancel (  ) ; //Terminate the timer thread 
         }  
     }  
    
    class RedToGreen extends TimerTask  {  
        public void run (  )   {  
            System.out.println ( "Change lights" ) ; 
            image = getImage("green32.png");
            timer.cancel (  ) ; //Terminate the timer thread 
         }  
     }  
    
	
	public synchronized void changeColour(Direction dir, LightColour lC){
		switch (dir) {
		case FROMSOUTHTONORTH:
			this.fromSouthToNorth = LightColour.YELLOW;
			image = getImage("yellow24.png");
//			this.fromNorthToSouth = LightColour.YELLOW;
//			this.fromNorthToWest = LightColour.YELLOW;
			if(this.fromSouthToNorth != lC){
				if(lC == LightColour.GREEN){
					timer.schedule ( new RedToGreen() , 5000 ) ;
					this.fromSouthToNorth = LightColour.RED;
//					this.fromNorthToSouth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}
				else if(lC == LightColour.RED){
					timer.schedule ( new GreenToRed() , 5000 ) ;
					this.fromSouthToNorth = LightColour.GREEN;
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
//					trySleep(LightColour.GREEN);
					this.fromSouthToWest = LightColour.RED;
//					this.fromWestToSouth = LightColour.RED;
//					this.fromSouthToNorth = LightColour.RED;
				}
				else if(this.fromSouthToWest == LightColour.RED){
//					trySleep(LightColour.RED);
					this.fromSouthToWest = LightColour.GREEN;
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
//					trySleep(LightColour.GREEN);
					this.fromWestToSouth = LightColour.RED;
//					this.fromSouthToWest = LightColour.RED;
//					this.fromSouthToNorth = LightColour.RED;
				}
				else if(this.fromWestToSouth == LightColour.RED){
//					trySleep(LightColour.RED);
					this.fromWestToSouth = LightColour.GREEN;
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
//					trySleep(LightColour.GREEN);
					this.fromWestToNorth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}
				else if(this.fromWestToNorth == LightColour.RED){
//					trySleep(LightColour.RED);
					this.fromWestToNorth = LightColour.GREEN;
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
//					trySleep(LightColour.GREEN);
					this.fromNorthToSouth = LightColour.RED;
//					this.fromSouthToNorth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}				
				else if(this.fromNorthToSouth == LightColour.RED){
//					trySleep(LightColour.RED);
					this.fromNorthToSouth = LightColour.GREEN;
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
//					trySleep(LightColour.GREEN);
					this.fromNorthToWest = LightColour.RED;
//					this.fromWestToNorth = LightColour.RED;
				}				
				else if(this.fromNorthToWest == LightColour.RED){
//					trySleep(LightColour.RED);
					this.fromNorthToWest = LightColour.GREEN;
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
