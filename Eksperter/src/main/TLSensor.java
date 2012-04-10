package main;

import java.util.ArrayList;

public class TLSensor {

	private ArrayList<Person> personQueue;
	
	public TLSensor(){
		personQueue = new ArrayList<Person>();
	}
	
	public void addPerson(Person p){
		personQueue.add(p);
	}
	
	public void removePerson(Person p){
		for(Person pQ : personQueue){
			if(pQ == p) personQueue.remove(pQ);
		}
	}
	
	
}
