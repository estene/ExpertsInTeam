package main;

import java.util.ArrayList;

public class PedestrianLight {
	
	private LightColour myColour;
	private Placement placement;
	private TLSensor pedSensor;
	private boolean isGreen;
	
	public PedestrianLight(Placement placement){
		this.myColour = LightColour.RED;
		this.isGreen = false;
		this.placement = placement;
		pedSensor = new TLSensor();
	}

	public int getPeopleAmount(){
		return pedSensor.getPersonQueueAmount();
	}
	
	public void buttonPushed(){
		if(myColour == LightColour.RED){
			// Request colour change from OPController
		}
	}
	
	public void changeColour(){
		if(myColour == LightColour.RED){
			this.myColour = LightColour.GREEN;
			this.isGreen = true;
		} else {
			this.myColour = LightColour.RED;
			this.isGreen = false;
		}
	}
	
}
