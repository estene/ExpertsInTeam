package main;

public class TrafficLight extends Thread{
	private TLSensor sensor;
	private final int greenToRedSleepTime = 2500;	
	private final int redToGreenSleepTime = 6000;
	
	private LightColour forward;
	private LightColour backward;
	private LightColour left;
	private LightColour right;
	
	//private LightColour myColour;
		
	private final Placement placement;
	
	private PedestrianLight pedLight1 = null;
	private PedestrianLight pedLight2 = null;
		
		
	public TrafficLight(Placement dir){
		this.placement = dir;
	}
	
	public void changeColour(Direction dir){
		switch (dir) {
		
		case FORWARD:
			this.forward = LightColour.YELLOW;
			if(this.forward == LightColour.GREEN){
				this.forward = LightColour.YELLOW;
				trySleep(LightColour.GREEN);
				this.forward = LightColour.RED;
			} else if(this.forward == LightColour.RED){
				trySleep(LightColour.RED);
				this.forward = LightColour.RED;
			}			
			break;
		
		case BACKWARD:
			this.backward = LightColour.YELLOW;
			if(this.backward == LightColour.GREEN){
				this.backward = LightColour.YELLOW;
				trySleep(LightColour.GREEN);
				this.backward = LightColour.RED;
			} else if(this.backward == LightColour.RED){
				trySleep(LightColour.RED);
				this.backward = LightColour.RED;
			}			
			break;
		
		case LEFT:
			this.left = LightColour.YELLOW;
			if(this.left == LightColour.GREEN){
				this.left = LightColour.YELLOW;
				trySleep(LightColour.GREEN);
				this.left = LightColour.RED;
			} else if(this.left == LightColour.RED){
				trySleep(LightColour.RED);
				this.left = LightColour.RED;
			}			
			break;
		
		case RIGHT:
			this.right = LightColour.YELLOW;
			if(this.right == LightColour.GREEN){
				this.right = LightColour.YELLOW;
				trySleep(LightColour.GREEN);
				this.right = LightColour.RED;
			} else if(this.right == LightColour.RED){
				trySleep(LightColour.RED);
				this.right = LightColour.RED;
			}			
			break;
			
		default:
			break;
		}
	}
	
	private void trySleep(LightColour colour){
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

	

	/**
	public void changeLightColour(){
		if (myColour == LightColour.GREEN){
			myColour = LightColour.ORANGE;
			trySleep(LightColour.GREEN);
			myColour = LightColour.RED;
		}else if(myColour == LightColour.RED){
			myColour = LightColour.ORANGE;
			trySleep(LightColour.RED);
			myColour = LightColour.GREEN;
		}
	}
	
	public TrafficLight[] getOpposingTrafficLight() {
		return opposingTrafficLight;
	}
	
	public void addOpposingTrafficLight(TrafficLight opposingTrafficLight) {
		TrafficLight[] temp = new TrafficLight[this.opposingTrafficLight.length+1];
		
		System.arraycopy(this.opposingTrafficLight, 0, temp, 0, this.opposingTrafficLight.length);
		
		temp[this.opposingTrafficLight.length] = opposingTrafficLight;
		
		this.opposingTrafficLight = new TrafficLight[temp.length];
		
		System.arraycopy(temp, 0, this.opposingTrafficLight, 0, temp.length);
		
		//Istedenfor array av opposingtrafficlights
		if(opposingTrafficLight1 == null){
			this.opposingTrafficLight1 = opposingTrafficLight;
		} else if(opposingTrafficLight2 == null){
			this.opposingTrafficLight2 = opposingTrafficLight;
		}
		
	}
	
	public LightColour getMyColour() {
		return myColour;
	}

	public void setMyColour(LightColour myColour) {
		this.myColour = myColour;
	}
	**/
	
	
}
