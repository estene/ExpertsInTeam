package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Scenario {
	
	private String thisScenario;
	private ArrayList<Person> people;
	private ArrayList<Bus> buses;
	private AnimationPanel aP;
	
	public Scenario(String scenario, AnimationPanel aP){
		people = new ArrayList<Person>();
		buses = new ArrayList<Bus>();
		thisScenario = scenario;
		
		this.aP = aP;
		
		if(scenario.equals("scen1")){
			people.add(new Person(507, 280, "4", Direction.FROMEASTTOWESTSOUTH));
			people.add(new Person(268, 280, "5", Direction.FROMWESTTOEASTSOUTH));
			
			buses.add(new Bus(390, 420, "30", Direction.FROMSOUTHTOWEST));
			buses.get(0).setMinutesLate(10);
			buses.add(new Bus(23, 190, "15", Direction.FROMWESTTONORTH));
			buses.add(new Bus(330, 0, "20", Direction.FROMNORTHTOWEST));
			
			for(Bus b : buses){
				aP.addBusToQueue(b);
			}
		}
		else if(scenario.equals("scen2")){
			// TODO
		}
	}
	
	public ArrayList<Person> getPeople(){
		return this.people;
	}
	
	public ArrayList<Bus> getBuses(){
		return this.buses;
	}
	
	public void draw(Graphics g){
		for(Person p : people){
			p.draw(g);
		}
		for(Bus b : buses){
			b.draw(g);
		}
	}
	
	public void move(){
		for(Person p : people){
			if(p.getGroupDirection().equals(Direction.FROMEASTTOWESTSOUTH) || p.getGroupDirection().equals(Direction.FROMWESTTOEASTSOUTH)){
				if(aP.getTrafficLights().get(2).getPedLight1().isGreen()){
					p.move();
				}
			}
			else if(p.getGroupDirection().equals(Direction.FROMEASTTOWESTNORTH) || p.getGroupDirection().equals(Direction.FROMWESTTOEASTNORTH)){
				if(aP.getTrafficLights().get(1).getPedLight1().isGreen()){
					p.move();
				}
			}
			else if(p.getGroupDirection().equals(Direction.FROMSOUTHTONORTH) || p.getGroupDirection().equals(Direction.FROMNORTHTOSOUTH)){
				if(aP.getTrafficLights().get(3).getPedLight2().isGreen()){
					p.move();
				}
			}
		}
		for(Bus b : buses){
			if(b.getHeadingDirection().equals(Direction.FROMSOUTHTOWEST)){
				if(aP.getTrafficLights().get(0).fromSouthToWest.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
			else if(b.getHeadingDirection().equals(Direction.FROMSOUTHTONORTH)){
				if(aP.getTrafficLights().get(0).fromSouthToNorth.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
			else if(b.getHeadingDirection().equals(Direction.FROMWESTTONORTH)){
				if(aP.getTrafficLights().get(0).fromWestToNorth.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
			else if(b.getHeadingDirection().equals(Direction.FROMWESTTOSOUTH)){
				if(aP.getTrafficLights().get(0).fromWestToSouth.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
			else if(b.getHeadingDirection().equals(Direction.FROMNORTHTOSOUTH)){
				if(aP.getTrafficLights().get(0).fromNorthToSouth.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
			else if(b.getHeadingDirection().equals(Direction.FROMNORTHTOWEST)){
				if(aP.getTrafficLights().get(0).fromNorthToWest.equals(LightColour.GREEN)){
					b.setWaitingAtOverpass(false);
					b.move();
				}else{
					b.setWaitingAtOverpass(true);
					b.stopForRed();
				}
			}
		}		
		
	}

}
