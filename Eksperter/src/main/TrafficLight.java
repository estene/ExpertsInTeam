package main;

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
	private PedestrianLight pedLight2 = null;
		
	private final String placement;
	
	public TrafficLight(String placement){
		this.placement = placement;
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
