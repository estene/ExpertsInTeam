package main;

public class TrafficLight extends Thread{
	private TLSensor sensor;
	private TrafficLight[] opposingTrafficLight;
	private LightColour myColour;
	private final int greenToRedSleepTime = 2500;
	private final int redToGreenSleepTime = 6000;
	
	
	public enum LightColour{
		GREEN,
		ORANGE,
		RED;
	}
		
	public TrafficLight(){
		opposingTrafficLight = new TrafficLight[0];
	}
	
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

	public TrafficLight[] getOpposingTrafficLight() {
		return opposingTrafficLight;
	}

	public void addOpposingTrafficLight(TrafficLight opposingTrafficLight) {
		TrafficLight[] temp = new TrafficLight[this.opposingTrafficLight.length+1];
		
		System.arraycopy(this.opposingTrafficLight, 0, temp, 0, this.opposingTrafficLight.length);
		
		temp[this.opposingTrafficLight.length] = opposingTrafficLight;
		
		this.opposingTrafficLight = new TrafficLight[temp.length];
		
		System.arraycopy(temp, 0, this.opposingTrafficLight, 0, temp.length);
	}

	public LightColour getMyColour() {
		return myColour;
	}

	public void setMyColour(LightColour myColour) {
		this.myColour = myColour;
	}
	
	
	
}
