package main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Bus-class
 * @author Even
 *
 */
public class Bus {
	private int minutesLate;
	private GPSCoordinates coordinates;
	private int x,y,xDir,yDir;
	private Image image;
	private ClassLoader classLoader;
	
	public Bus(int x, int y){
		xDir = 1;
		yDir = 0;
		coordinates = new GPSCoordinates(x,y,xDir,yDir);
		this.x = coordinates.getxCoord();
		this.y = coordinates.getyCoord();
		
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("bussmall.png");
	}
	
	//Move da BUZ
	public void move() {
		x = coordinates.getxCoord();
		y = coordinates.getyCoord();
		x += coordinates.getxDir();
		y += coordinates.getyDir();
		coordinates.setxCoord(x);
		coordinates.setyCoord(y);
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null); 
	}


	public int getMinutesLate() {
		return minutesLate;
	}


	public void setMinutesLate(int minutesLate) {
		this.minutesLate = minutesLate;
	}


	public GPSCoordinates getCoords() {
		return coordinates;
	}


	public void setCoords(GPSCoordinates coords) {
		this.coordinates = coords;
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
	
	// Retrieve image from resource
	public Image getImage(String imgName) {
		InputStream input = classLoader.getResourceAsStream("" + imgName);
		Image inputImage = null;
		try {
			inputImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputImage;
	}
	
	
}
