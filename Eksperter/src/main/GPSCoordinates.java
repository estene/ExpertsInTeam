package main;

/**
 * This is a simplified version of GPSCoordinates 
 * that uses x and y coordinates in the picture.
 * 
 * @author cato
 *
 */

public class GPSCoordinates {
	private int xCoord;
	private int yCoord;
	
	public GPSCoordinates(){
		
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	

}
