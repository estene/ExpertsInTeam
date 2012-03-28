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
	
	public void changeLights(Direction dir){
		southEastLight.changeColour(dir);
		southWestLight.changeColour(dir);
		northEastLight.changeColour(dir);
		northWestLight.changeColour(dir);
	}
	
	/**
	 * This method changes the overall traffic flow in the overpass.
	 * 
	 * Must find a better name for this method :-)
	 * 
	 */
	
	public void mustFindAName(Direction dir){
		
	}
 
}
