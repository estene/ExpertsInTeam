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
	private int xDir;
	private int yDir;
	private int x,y;
	
	public GPSCoordinates(int x, int y){
		this.x = x;
		this.y = y;
		xCoord = x;
		yCoord = y;
		xDir = 0;
		yDir = 0;
		
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
		this.yDir = yDir;
	}
	public void reset() {
		setxCoord(x);
		setyCoord(y);
	}
	

}
