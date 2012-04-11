package main;

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
				
		//initializes all the lights to green so that the underlying logic asdfadsgsdfb
		fromNorthToSouth = LightColour.RED;
		fromNorthToWest = LightColour.RED;
		
		fromSouthToNorth = LightColour.RED;
		fromSouthToWest = LightColour.RED;
		
		fromWestToNorth = LightColour.RED;
		fromWestToSouth = LightColour.RED;
	}
	
	public synchronized void changeColour(Direction dir, LightColour lC){
		switch (dir) {
		case FROMSOUTHTONORTH:									
			this.fromSouthToNorth = LightColour.YELLOW;
//			this.fromNorthToSouth = LightColour.YELLOW;
//			this.fromNorthToWest = LightColour.YELLOW;
			if(this.fromSouthToNorth != lC){
				if(lC == LightColour.GREEN){
//					trySleep(lC);
					this.fromSouthToNorth = LightColour.RED;
//					this.fromNorthToSouth = LightColour.RED;
//					this.fromNorthToWest = LightColour.RED;
				}
				else if(lC == LightColour.RED){
//					trySleep(lC);
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
/**	
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
**/
	
	public PedestrianLight getPedLight1(){
		return this.pedLight1;
	}
	
	public PedestrianLight getPedLight2(){
		return this.pedLight2;
	}
	
}
