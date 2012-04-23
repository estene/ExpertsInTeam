package main;

public class BusBoundaries {
	private int westX,
				northY,
				southY;
	
	public BusBoundaries(){
		westX = 290;
		northY = 75;
		southY = 285;
	}	
	
	public boolean amIInsideYou(int x, int y){
		if(x >= westX && (y >= northY && y <= southY)) return true;
		return false;
	}
	
}
