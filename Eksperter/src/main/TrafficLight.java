package main;

/**
 * This class represents a single trafficlight in the overpass. Every light and placement is 
 * based on that the road towards Studentersamfunnet is "South". 
 * 
 * @author cato
 *
 */

public class TrafficLight extends Thread{
	private TLSensor sensor;
	private final int greenToRedSleepTime = 2500;	
	private final int redToGreenSleepTime = 6000;
	
	
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
		this.placement = placement;		
		
		if(placement == Placement.SOUTHEAST) pedLight1 = new PedestrianLight(Placement.SOUTH);
		else if(placement == Placement.SOUTHWEST){
			pedLight1 = new PedestrianLight(Placement.SOUTH);
			pedLight2 = new PedestrianLight(Placement.WEST);
		}
		else if(placement == Placement.NORTHWEST){
			pedLight1 = new PedestrianLight(Placement.NORTH);
			pedLight2 = new PedestrianLight(Placement.WEST);
		}
		else if(placement == Placement.NORTHEAST) pedLight1 = new PedestrianLight(Placement.NORTH);
		
		sensor = new TLSensor();
		
		//initializes all the lights to green so that the underlying logic 
		fromNorthToSouth = LightColour.GREEN;
		fromNorthToWest = LightColour.GREEN;
		
		fromSouthToNorth = LightColour.GREEN;
		fromSouthToWest = LightColour.GREEN;
		
		fromWestToNorth = LightColour.GREEN;
		fromWestToSouth = LightColour.GREEN;
	}
	
	public synchronized void changeColour(Direction dir){
		switch (dir) {
		case FROMSOUTHTONORTH:
			this.fromSouthToNorth = LightColour.YELLOW;
			if(this.fromSouthToNorth == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromSouthToNorth = LightColour.RED;
			} else if(this.fromSouthToNorth == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromSouthToNorth = LightColour.RED;
			}			
			break;

		case FROMSOUTHTOWEST:
			this.fromSouthToWest = LightColour.YELLOW;
			if(this.fromSouthToWest == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromSouthToWest = LightColour.RED;
			} else if(this.fromSouthToWest == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromSouthToWest = LightColour.RED;
			}			
			break;

		case FROMWESTTOSOUTH:
			this.fromWestToSouth = LightColour.YELLOW;
			if(this.fromWestToSouth == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromWestToSouth = LightColour.RED;
			} else if(this.fromWestToSouth == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromWestToSouth = LightColour.RED;
			}
			break;
			
		case FROMWESTTONORTH:
			this.fromWestToNorth = LightColour.YELLOW;
			if(this.fromWestToNorth == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromWestToNorth = LightColour.RED;
			} else if(this.fromWestToNorth == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromWestToNorth = LightColour.RED;
			}
			break;
			
		case FROMNORTHTOSOUTH:
			this.fromNorthToSouth= LightColour.YELLOW;
			if(this.fromNorthToSouth == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromNorthToSouth = LightColour.RED;
			} else if(this.fromNorthToSouth == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromNorthToSouth = LightColour.RED;
			}
			break;
			
		case FROMNORTHTOWEST:
			this.fromNorthToWest = LightColour.YELLOW;
			if(this.fromNorthToWest == LightColour.GREEN){
				trySleep(LightColour.GREEN);
				this.fromNorthToWest = LightColour.RED;
			} else if(this.fromNorthToWest == LightColour.RED){
				trySleep(LightColour.RED);
				this.fromNorthToWest = LightColour.RED;
			}
			break;
		}
	}
	
	public void changePedLight(Placement placement){
		if(this.placement == Placement.NORTHEAST && placement == Placement.NORTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && placement == Placement.NORTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.NORTHWEST && placement == Placement.WEST){
			this.pedLight2.changeColour();
		}else
		if(this.placement == Placement.SOUTHWEST && placement == Placement.WEST){
			this.pedLight2.changeColour();
		}else
		if(this.placement == Placement.SOUTHWEST && placement == Placement.SOUTH){
			this.pedLight1.changeColour();
		}else
		if(this.placement == Placement.SOUTHEAST && placement == Placement.SOUTH){
			this.pedLight1.changeColour();
		}
	}
	
	protected void trySleep(LightColour colour){
		if(colour == LightColour.GREEN){
			try{
				sleep(greenToRedSleepTime);
			}catch(Exception e){
				System.err.println("There was an error trying to sleep: " + e);
			}
		}else if(colour == LightColour.RED){
			try{
				sleep(redToGreenSleepTime);
			}catch(Exception e){
				System.err.println("There was an error trying to sleep: " + e);
			}
		}
	}
	
	public TLSensor getSensor() {
		return sensor;
	}

	public void setSensor(TLSensor sensor) {
		this.sensor = sensor;
	}	
}
