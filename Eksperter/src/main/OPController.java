package main;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.ChangedCharSetException;

/**
 * This is the overpass controller that controls what the trafficlights should do.
 * 
 * @author cato
 *
 */

public class OPController{
	private TrafficLight northWestLight;
	private TrafficLight northEastLight;

	private TrafficLight southWestLight;
	private TrafficLight southEastLight;

	private ArrayList<Person> personQueue;
	private ArrayList<Bus> busQueue;
	private ArrayList<Direction> availableDirections;
	private Direction currentGreenDirection = null;

	public TrafficLight getNWL(){
		return northWestLight;
	}

	public TrafficLight getNEL(){
		return northEastLight;
	}

	public TrafficLight getSWL(){
		return southWestLight;
	}

	public TrafficLight getSEL(){
		return southEastLight;
	}


	public OPController(){
		northWestLight = new TrafficLight(Placement.NORTHWEST);
		northEastLight = new TrafficLight(Placement.NORTHEAST);
		southWestLight = new TrafficLight(Placement.SOUTHWEST);
		southEastLight = new TrafficLight(Placement.SOUTHEAST);

		busQueue = new ArrayList<Bus>();
		personQueue = new ArrayList<Person>();
		availableDirections = new ArrayList<Direction>();
		availableDirections.add(Direction.FROMNORTHTOSOUTH);
		availableDirections.add(Direction.FROMNORTHTOWEST);

		availableDirections.add(Direction.FROMSOUTHTONORTH);
		availableDirections.add(Direction.FROMSOUTHTOWEST);

		availableDirections.add(Direction.FROMWESTTONORTH);
		availableDirections.add(Direction.FROMWESTTOSOUTH);
	}

	private void changeLights(Direction dir, LightColour lC){			
		southEastLight.changeColour(dir, lC);
		southWestLight.changeColour(dir, lC);
		northEastLight.changeColour(dir, lC);
		northWestLight.changeColour(dir, lC);
	}

	private void changePedLights(Direction dir){
		southEastLight.changePedLight(dir);
		southWestLight.changePedLight(dir);
		northWestLight.changePedLight(dir);
		northEastLight.changePedLight(dir);
	}

	public ArrayList<TrafficLight> getTrafficLights(){
		return new ArrayList<TrafficLight>(Arrays.asList(northWestLight, northEastLight, southEastLight, southWestLight));
	}

	public void addBusToQueue(Bus bus){
		busQueue.add(bus);
	}

	public void addPersonToQueue(Person p){
		personQueue.add(p);
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
		personQueue = new ArrayList<Person>();

		if(northWestLight.getPedLight1().getPeopleAmount() > 0){
			if(northWestLight.getPedLight1().getPeople().isWaitingForGreen()){
				personQueue.add(northWestLight.getPedLight1().getPeople());
			}			
		}
		if(northWestLight.getPedLight2().getPeopleAmount() > 0){
			if(northWestLight.getPedLight2().getPeople().isWaitingForGreen()) {
				personQueue.add(northWestLight.getPedLight2().getPeople());
			}
		}
		if(northEastLight.getPedLight1().getPeopleAmount() > 0){
			if(northEastLight.getPedLight1().getPeople().isWaitingForGreen()) {
				personQueue.add(northEastLight.getPedLight1().getPeople());
			}
		}
		if(southWestLight.getPedLight1().getPeopleAmount() > 0){
			if(southWestLight.getPedLight1().getPeople().isWaitingForGreen()) {
				personQueue.add(southWestLight.getPedLight1().getPeople());
			}
		}
		if(southWestLight.getPedLight2().getPeopleAmount() > 0){
			if(southWestLight.getPedLight2().getPeople().isWaitingForGreen()){
				personQueue.add(southWestLight.getPedLight2().getPeople());	
			}
		}

		if(southEastLight.getPedLight1().getPeopleAmount() > 0){
			personQueue.add(southWestLight.getPedLight1().getPeople());
		}

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

		if(!pedPriority){
			currentGreenDirection = greenDirection;
			if(greenDirection == Direction.FROMSOUTHTONORTH && !southEastLight.getPedLight1().isGreen()){
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMWESTTONORTH, LightColour.RED);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.RED);

				if(!southWestLight.getPedLight2().isGreen()) changeLights(Direction.FROMNORTHTOWEST, LightColour.GREEN);
				if(!northEastLight.getPedLight1().isGreen()) changeLights(Direction.FROMNORTHTOSOUTH, LightColour.GREEN);
				changeLights(greenDirection, LightColour.GREEN);
			}
			else if(greenDirection == Direction.FROMSOUTHTOWEST && !southEastLight.getPedLight1().isGreen() && !southWestLight.getPedLight2().isGreen()){
				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);
				changeLights(Direction.FROMNORTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMWESTTONORTH, LightColour.RED);

				changeLights(Direction.FROMSOUTHTONORTH, LightColour.GREEN);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.GREEN);
				changeLights(greenDirection, LightColour.GREEN);
			}
			else if(greenDirection == Direction.FROMNORTHTOSOUTH && !northEastLight.getPedLight1().isGreen() && !southEastLight.getPedLight1().isGreen()){
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMWESTTONORTH, LightColour.RED);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.RED);

				if(!northWestLight.getPedLight2().isGreen()) changeLights(Direction.FROMNORTHTOWEST, LightColour.GREEN);
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.GREEN);
				changeLights(greenDirection, LightColour.GREEN);
			}
			else if(greenDirection == Direction.FROMNORTHTOWEST  && !northWestLight.getPedLight2().isGreen()){
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.RED);
				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);

				if(!southWestLight.getPedLight1().isGreen()) changeLights(Direction.FROMWESTTOSOUTH, LightColour.GREEN);
				if(!northEastLight.getPedLight1().isGreen()){
					changeLights(Direction.FROMWESTTONORTH, LightColour.GREEN);					
					changeLights(greenDirection, LightColour.GREEN);
				}
			}
			else if(greenDirection == Direction.FROMWESTTONORTH && !northWestLight.getPedLight2().isGreen()){
				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);

				if(!northEastLight.getPedLight1().isGreen()) changeLights(Direction.FROMNORTHTOWEST, LightColour.GREEN);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.GREEN);
				changeLights(greenDirection, LightColour.GREEN);
			}
			else if(greenDirection == Direction.FROMWESTTOSOUTH && !southWestLight.getPedLight2().isGreen()){

				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);

				if(!northWestLight.getPedLight2().isGreen()) changeLights(Direction.FROMNORTHTOWEST, LightColour.GREEN);
				changeLights(Direction.FROMWESTTONORTH, LightColour.GREEN);
				changeLights(greenDirection, LightColour.GREEN);
			}
		}else{
			if(greenDirection == Direction.FROMSOUTHTONORTH){ //For pedestrians
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.RED);
				
				changePedLights(greenDirection);
			}
			else if(greenDirection == Direction.FROMNORTHTOSOUTH){ //For pedestrians
				changeLights(Direction.FROMNORTHTOSOUTH, LightColour.RED);
				changeLights(Direction.FROMSOUTHTONORTH, LightColour.RED);
				changeLights(Direction.FROMNORTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMWESTTONORTH, LightColour.RED);

				changePedLights(greenDirection);
			}
			else if(greenDirection == Direction.FROMWESTTONORTH){ //For pedestrians
				changeLights(Direction.FROMNORTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMSOUTHTOWEST, LightColour.RED);
				changeLights(Direction.FROMWESTTONORTH, LightColour.RED);
				changeLights(Direction.FROMWESTTOSOUTH, LightColour.RED);

				changePedLights(greenDirection);
			}
		}			

		if(pedPriority){
			for(Person p : personQueue){
				if(southWestLight.getPedLight1().isGreen()){
					if(p.getGroupDirection().equals(Direction.FROMEASTTOWESTSOUTH) || p.getGroupDirection().equals(Direction.FROMWESTTOEASTSOUTH)){
						p.setWaitingForGreen(false);
					}
				}
				if(southWestLight.getPedLight2().isGreen()){
					if(p.getGroupDirection().equals(Direction.FROMNORTHTOSOUTHWEST) || p.getGroupDirection().equals(Direction.FROMSOUTHTONORTHWEST)){
						p.setWaitingForGreen(false);
					}
				}
				if(northEastLight.getPedLight1().isGreen()){
					if(p.getGroupDirection().equals(Direction.FROMEASTTOWESTNORTH) || p.getGroupDirection().equals(Direction.FROMEASTTOWESTNORTH)){
						p.setWaitingForGreen(false);
					}
				}
			}
		}
		else{
			for(Bus b : busQueue){
				if(northWestLight.fromNorthToSouth.equals(LightColour.GREEN)){
					if(b.getHeadingDirection().equals(Direction.FROMNORTHTOSOUTH)) {
						b.setWaitingAtOverpass(false);
					}
				}
				if(northWestLight.fromNorthToWest.equals(LightColour.GREEN)) {
					if(b.getHeadingDirection().equals(Direction.FROMNORTHTOWEST)){
						b.setWaitingAtOverpass(false);
					}
				}
				if(northWestLight.fromSouthToNorth.equals(LightColour.GREEN)) {
					if(b.getHeadingDirection().equals(Direction.FROMSOUTHTONORTH)){
						b.setWaitingAtOverpass(false);
					}								
				}
				if(northWestLight.fromSouthToWest.equals(LightColour.GREEN)){
					if(b.getHeadingDirection().equals(Direction.FROMSOUTHTOWEST)){
						b.setWaitingAtOverpass(false);
					}
				}
				if(northWestLight.fromWestToNorth.equals(LightColour.GREEN)){
					if(b.getHeadingDirection().equals(Direction.FROMWESTTONORTH)){
						b.setWaitingAtOverpass(false);
					}				
				}
				if(northWestLight.fromWestToSouth.equals(LightColour.GREEN)){
					if(b.getHeadingDirection().equals(Direction.FROMWESTTOSOUTH)){
						b.setWaitingAtOverpass(false);
					}				
				}
			}
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
			for(Person p : personQueue){
				if(d.equals(Direction.FROMSOUTHTONORTH) && p.isWaitingForGreen()){
					utilityValue += this.southEastLight.getPedLight1().getPeopleAmount() + this.southEastLight.getPeopleFromPedL1S().getWaitTime() + this.southWestLight.getPedLight1().getPeopleAmount() + this.southWestLight.getPeopleFromPedL1S().getWaitTime();
				}
				else if (d.equals(Direction.FROMWESTTONORTH) || d.equals(Direction.FROMWESTTOSOUTH) && p.isWaitingForGreen()){
					utilityValue += this.southWestLight.getPedLight2().getPeopleAmount() + this.southWestLight.getPeopleFromPedL2S().getWaitTime() + this.northWestLight.getPedLight2().getPeopleAmount() + this.northWestLight.getPeopleFromPedL2S().getWaitTime();
				}
				else if (d.equals(Direction.FROMNORTHTOSOUTH) && p.isWaitingForGreen()){
					utilityValue += this.northEastLight.getPedLight1().getPeopleAmount() + this.northEastLight.getPeopleFromPedL1S().getWaitTime() + this.northWestLight.getPedLight1().getPeopleAmount() + this.northWestLight.getPeopleFromPedL1S().getWaitTime();
				}
			}			
		}
		return utilityValue;
	}

}
