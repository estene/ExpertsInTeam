package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Bus-class
 * @author Even
 *
 */
public class Bus {
	private ArrayList<Person> persons;
	private int minutesLate;
	private GPSCoordinates coords;
	private int x,y,xDir,yDir;
	private ImageIcon image;
	private Image image2;
	
	public Bus(){
		x = 120;
		y = 100;
		xDir = 1;
		yDir = 0;
    	image = new ImageIcon(this.getClass().getResource("/play2.png"));
    	image2 = image.getImage();
	}
	
	//Move da BUZ
	public void move() {
		x += xDir;
		y += yDir;
	}
	
	public void reset() {
    	x = 120;
    	y = 100; 
		xDir = 1;
		yDir = 0;

	}
	
	public void draw(Graphics g) {
		g.drawImage(image2, x, y, null); 
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

	
	// Setters and getters 
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
