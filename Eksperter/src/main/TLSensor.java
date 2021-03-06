package main;

import java.util.ArrayList;

public class TLSensor {

	private Person personQueue;
	
	public TLSensor(){
		personQueue = new Person();
		personQueue.setNumber("0");
	}
	
	public void addPerson(Person p){
		personQueue = p;
	}
	
	public void removePerson(){
		personQueue = new Person();
		personQueue.setNumber("0");
	}
	
	public Person getPeople(){
		return personQueue;
	}
	
	public int getPersonQueueAmount(){
		if(personQueue != null)	return personQueue.getNumber();
		return 0;
	}
	
}
