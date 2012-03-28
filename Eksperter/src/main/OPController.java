package main;

/**
 * This is the overpass controller that controls what the trafficlights should do.
 * 
 * @author cato
 *
 */

public class OPController {
	private TrafficLight northWestLight;
	private TrafficLight northEastLight;
	
	private TrafficLight southWestLight;
	private TrafficLight southEastLight;
	
	private void changeSouthLights(Direction dir){
		southWestLight.changeColour(dir);
		southEastLight.changeColour(dir);
	}
	
	private void changeWestLights(Direction dir){
		
	}
 
}
