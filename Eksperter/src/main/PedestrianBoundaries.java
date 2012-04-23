package main;

public class PedestrianBoundaries {

	private int southY,
				northY,
				westX,
				eastX;
	
	
	public PedestrianBoundaries(){
		southY = 315;
		northY = 70;
		westX = 255;
		eastX = 506;
	}
	
	public boolean amIOnYou(int x, int y){
		if((x >= westX && x <= eastX && (y >= northY && y <= southY))) return true;
		return false;
	}
	
}
