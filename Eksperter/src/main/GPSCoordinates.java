package main;

/**
 * This is a simplified version of GPSCoordinates 
 * that uses x and y coordinates in the picture.
 * 
 * @author cato , Even
 *
 */

public class GPSCoordinates {
	private int xCoord, yCoord, xDir, yDir;
	
	public GPSCoordinates(int x, int y, int xDir, int yDir){
		xCoord = x;
		yCoord = y;
		this.xDir = xDir;
		this.yDir = yDir;
		
	}
	
	public int getyCoord() {
		return yCoord;
	}
	
	public void setCoords(int dx, int dy){
		this.xCoord = dx;
		this.yCoord = dy;
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

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = -1*yDir;
		
	}
}
