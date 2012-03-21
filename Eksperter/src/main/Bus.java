package main;

import java.util.ArrayList;

public class Bus {
	private ArrayList<Person> persons;
	private int minutesLate;
	private GPSCoordinates coords;
	
	
	public Bus(){
		
	}


	public int getMinutesLate() {
		return minutesLate;
	}


	public void setMinutesLate(int minutesLate) {
		this.minutesLate = minutesLate;
	}


	public GPSCoordinates getCoords() {
		return coords;
	}


	public void setCoords(GPSCoordinates coords) {
		this.coords = coords;
	}
	
	
}
