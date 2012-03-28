package main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Person class - Includes testmethods to perform animation, subject to change
 * @author Even
 */

public class Person {
	private GPSCoordinates coordinates;
	private int x,y,xDir,yDir;
	
	public Person(int x, int y){
		xDir = 0;
		yDir = 1;
		coordinates = new GPSCoordinates(x,y,xDir,yDir);
		this.x = coordinates.getxCoord();
		this.y = coordinates.getyCoord();


	}
	
	// Move the men
	public void move() {
		x = coordinates.getxCoord();
		y = coordinates.getyCoord();
		x += coordinates.getxDir();
		y += coordinates.getyDir();
		coordinates.setxCoord(x);
		coordinates.setyCoord(y);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(coordinates.getxCoord(), coordinates.getyCoord(), 20, 20);
	}
	
	//Getters and setters
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public GPSCoordinates getCoords() {
		return coordinates;
	}


	public void setCoords(GPSCoordinates coords) {
		this.coordinates = coords;
	}

	
}
