package main;

public class PedestrianLight {
	
	private LightColour myColour;
	private Placement placement;
	
	public PedestrianLight(Placement placement){
		this.myColour = LightColour.GREEN;
		this.placement = placement;
	}
	
	public void buttonPushed(){
		if(myColour == LightColour.RED){
			// Request colour change from OPController
		}
	}
	
	public void changeColour(){
		if(myColour == LightColour.RED){
			this.myColour = LightColour.GREEN;
		} else this.myColour = LightColour.RED;
	}
	
}
