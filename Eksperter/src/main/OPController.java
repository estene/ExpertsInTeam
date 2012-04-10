package main;

import java.util.ArrayList;

import javax.swing.text.ChangedCharSetException;

/**
 * This is the overpass controller that controls what the trafficlights should do.
 * 
 * @author cato
 *
 */

public class OPController extends Thread{
	private TrafficLight northWestLight;
	private TrafficLight northEastLight;
	
	private TrafficLight southWestLight;
	private TrafficLight southEastLight;
	
	private ArrayList<Bus> busQueue;
	private ArrayList<Direction> availableDirections;
	private Direction currentGreenDirection = null;
	
	public OPController(){
		northWestLight = new TrafficLight(Placement.NORTHWEST);
		northEastLight = new TrafficLight(Placement.NORTHEAST);
		southWestLight = new TrafficLight(Placement.SOUTHWEST);
		southEastLight = new TrafficLight(Placement.SOUTHEAST);
	
		busQueue = new ArrayList<Bus>();
		availableDirections = new ArrayList<Direction>();
		availableDirections.add(Direction.FROMNORTHTOSOUTH);
		availableDirections.add(Direction.FROMNORTHTOWEST);
		
		availableDirections.add(Direction.FROMSOUTHTONORTH);
		availableDirections.add(Direction.FROMSOUTHTOWEST);
		
		availableDirections.add(Direction.FROMWESTTONORTH);
		availableDirections.add(Direction.FROMWESTTOSOUTH);
	}
	
	
	//Må se om dette er nødvendig
	private void changeLights(Direction dir, LightColour lC){			
		southEastLight.changeColour(dir, lC);
		southWestLight.changeColour(dir, lC);
		northEastLight.changeColour(dir, lC);
		northWestLight.changeColour(dir, lC);
	}
	
	public void addBusToQueue(Bus bus){
		busQueue.add(bus);
	}
	
	public void removeBusFromQueue(Bus bus){
		for(Bus b : busQueue){
			if (b == bus) busQueue.remove(b);
		}
	}
	
	public void calculateNextAction(){
		Direction greenDirection = null;
		boolean pedPriority = false;
		double highestValue = Double.MIN_VALUE;
		
		for(Direction d : availableDirections){
			double value = whatToDoUtility(d, true);
			if(value > highestValue){
				highestValue = value;
				greenDirection = d;
			}
		}
		
		if(highestValue < whatToDoUtility(Direction.FROMSOUTHTONORTH, false)){
			pedPriority = true;
			greenDirection = Direction.FROMSOUTHTONORTH;
			highestValue = whatToDoUtility(Direction.FROMSOUTHTONORTH, false);
		}
		else if(highestValue < whatToDoUtility(Direction.FROMWESTTONORTH, false)){
			pedPriority = true;
			greenDirection = Direction.FROMWESTTONORTH;
			highestValue = whatToDoUtility(Direction.FROMWESTTONORTH, false);
		}
		else if(highestValue < whatToDoUtility(Direction.FROMNORTHTOSOUTH, false)){
			pedPriority = true;
			greenDirection = Direction.FROMNORTHTOSOUTH;
			highestValue = whatToDoUtility(Direction.FROMNORTHTOSOUTH, false);
		}
		
		//First run...
		if(currentGreenDirection == null){
			if(greenDirection != null){
				currentGreenDirection = greenDirection;
				
				changeLights(greenDirection, LightColour.GREEN);
				
			}
		}else{
			currentGreenDirection = greenDirection;
			
		}
	}
	
	private void trySleep(int time){
		try{
			sleep(time);
		}catch(Exception e){
			System.err.println("There was an error trying to sleep: " + e);
		}
	}
	
	/**
	 * This method calculates which light to set based on the values amount of pedestrians at a trafficlight, amount of people in the bus
	 * and how late the bus is according to schedule.
	 *  
	 */

	private double whatToDoUtility(Direction d, boolean bus){
		double utilityValue = 0.0;

		if(bus){
			for(Bus b : busQueue){
				if(b.getHeadingDirection().equals(d) && b.isWaitingAtOverpass()){
					utilityValue += b.getMinutesLate() + b.getPeopleAmount();
				}
			}
		} else{
			if(d.equals(Direction.FROMSOUTHTONORTH)){
				utilityValue += this.southEastLight.getPedLight1().getPeopleAmount() + this.southWestLight.getPedLight1().getPeopleAmount();
			}
			else if (d.equals(Direction.FROMWESTTONORTH) || d.equals(Direction.FROMWESTTOSOUTH)){
				utilityValue += this.southWestLight.getPedLight2().getPeopleAmount() + this.northWestLight.getPedLight2().getPeopleAmount();
			}
			else if (d.equals(Direction.FROMNORTHTOSOUTH)){
				utilityValue += this.northEastLight.getPedLight1().getPeopleAmount() + this.northWestLight.getPedLight1().getPeopleAmount();
			}
		}		
		return utilityValue;
	}

}
