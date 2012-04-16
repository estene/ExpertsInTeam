package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Scenario {
	
	private String thisScenario;
	private ArrayList<Person> people;
	private ArrayList<Bus> buses;
	
	public Scenario(String scenario){
		people = new ArrayList<Person>();
		buses = new ArrayList<Bus>();
		thisScenario = scenario;
		
		if(scenario.equals("scen1")){
			people.add(new Person(507, 280, "4", Direction.FROMEASTTOWESTSOUTH));
			people.add(new Person(268, 305, "2", Direction.FROMWESTTOEASTSOUTH));
			
			buses.add(new Bus(390, 420, "30", Direction.FROMSOUTHTOWEST));
			buses.add(new Bus(390, 320, "10", Direction.FROMSOUTHTOWEST));
			buses.add(new Bus(23, 190, "15", Direction.FROMWESTTONORTH));
			buses.add(new Bus(330, 40, "20", Direction.FROMNORTHTOWEST));
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
			p.move();
		}
		for(Bus b : buses){
			b.move();
		}		
	}

}
