package main;

import java.util.ArrayList;

public class TLSensor {

	private ArrayList<Person> persons;
	
	public TLSensor(){
		persons = new ArrayList<Person>();
	}
	
	private void addPerson(Person p){
		persons.add(p);
	}
	
}
