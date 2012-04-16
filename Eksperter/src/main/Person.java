package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Person class - Includes testmethods to perform animation, subject to change
 * @author Even
 */

public class Person {
	private GPSCoordinates coordinates;
	private int x,y,xDir,yDir;
	private ClassLoader classLoader;
	private Image image;
	private Font font;
	private String number;
	private Direction groupDirection;
	
	public Person(int x, int y, String number, Direction dir){
		font = new Font("Arial", Font.PLAIN, 20);
		xDir = 0;
		yDir = 1;
		this.number = number;
		coordinates = new GPSCoordinates(x,y,xDir,yDir);
		this.x = coordinates.getxCoord();
		this.y = coordinates.getyCoord();
		
		groupDirection = dir;
		
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("");
		
	}
	
	// Move the men
	public void move() {
		if (coordinates.getyCoord() <= 310 || coordinates.getyCoord() >= 60 || coordinates.getxCoord() <= 260 || coordinates.getxCoord() >= 500) {
			if(groupDirection.equals(Direction.FROMWESTTOEASTSOUTH) || groupDirection.equals(Direction.FROMWESTTOEASTNORTH)){
				xDir = 1;
				yDir = 0;
			}
			else if(groupDirection.equals(Direction.FROMEASTTOWESTSOUTH) || groupDirection.equals(Direction.FROMEASTTOWESTNORTH)){
				xDir = -1;
				yDir = 0;
			}
			else if(groupDirection.equals(Direction.FROMNORTHTOSOUTH)){
				xDir = 0;
				yDir = -1;
			}
			else if(groupDirection.equals(Direction.FROMSOUTHTONORTH)){
				xDir = 0;
				yDir = 1;
			}
			x = coordinates.getxCoord();
			y = coordinates.getyCoord();
			x += coordinates.getxDir();
			y += coordinates.getyDir();
			coordinates.setxCoord(x);
			coordinates.setyCoord(y);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(coordinates.getxCoord(), coordinates.getyCoord(), 30, 30);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(number, coordinates.getxCoord() + 4, coordinates.getyCoord() + 22);
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
