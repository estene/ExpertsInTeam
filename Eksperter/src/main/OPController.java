package main;

import java.util.ArrayList;

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
	private ArrayList<Bus> busQueue;
	
	public OPController(){
		northWestLight = new TrafficLight(Placement.NORTHWEST);
		northEastLight = new TrafficLight(Placement.NORTHEAST);
		southWestLight = new TrafficLight(Placement.SOUTHWEST);
		southEastLight = new TrafficLight(Placement.SOUTHEAST);
	
		busQueue = new ArrayList<Bus>();
	}
	
	
	//Må se om dette er nødvendig
	public void changeLights(Direction dir){
			
		southEastLight.changeColour(dir);
		southWestLight.changeColour(dir);
		northEastLight.changeColour(dir);
		northWestLight.changeColour(dir);
	}
	
	public void addBusToQueue(Bus bus){
		this.busQueue.add(bus);
	}
	
	public void removeBusFromQueue(Bus bus){
		for(Bus b : busQueue){
			if (b == bus) busQueue.remove(b);
		}
	}
	
	/**
	 * This method changes the overall traffic flow in the overpass.
	 * 
	 * Must find a better name for this method :-)
	 * 
	 */
	
	public double whatToDoUtility(Direction d){
		double utilityValue = 0.0;

		for(Bus b : busQueue){
			if(b.getHeadingDirection().equals(d) && b.isWaitingAtOverpass()){
				utilityValue += b.getMinutesLate() + b.getPeopleAmount();
			}
		}
		return utilityValue;
	}

}
