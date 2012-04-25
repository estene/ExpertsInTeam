package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;


/**
 * Bus-class
 * @author Even, Cato
 * 
 * The bus class is responsible for creating the bus objects
 *
 */
public class Bus {
	private int minutesLate;
	private GPSCoordinates coordinates;
	private int x,y,xDir,yDir;
	private Image image;
	private ClassLoader classLoader;
	private Direction myHeadingDirection;
	private String personQueue;
	private boolean waitingAtOverpass;
	private Font font;
	private Color color;
	
	/**
	 * Constructor
	 * @param x - x coordinate of bus
	 * @param y - y coordinate of bus
	 * @param numberPeople - number of people on bus
	 * @param mHD - the bus' heading direction
	 */
	public Bus(int x, int y, String numberPeople, Direction mHD){
		font = new Font("Arial", Font.BOLD, 20);
		color = new Color(255, 0, 0); // Ferrari red
		xDir = 1;
		yDir = 0;
		coordinates = new GPSCoordinates(x,y,xDir,yDir);
		this.x = coordinates.getxCoord();
		this.y = coordinates.getyCoord();
		personQueue = numberPeople;
		waitingAtOverpass = true;
		myHeadingDirection = mHD;
		minutesLate = 0;
		
		classLoader = Thread.currentThread().getContextClassLoader();
		image = getImage("bussmall.png");
	}
	
	public int getPeopleAmount(){
		return Integer.valueOf(personQueue);
	}
	
	public boolean isWaitingAtOverpass(){
		return waitingAtOverpass;
	}
	
	public void setWaitingAtOverpass(boolean b){
		waitingAtOverpass = b;
	}
	/**
	public int getPeopleAmount(){
		return personQueue.size();
	}
	**/
	public Direction getHeadingDirection(){
		return this.myHeadingDirection;
	}
	
	/**
	 * The bus' move method . checks which direction the bus is headed and adjusts x-direction , y-direction and sets the x and y coordinates.
	 */
	public void move() {
		
		if(myHeadingDirection.equals(Direction.FROMWESTTONORTH)){
			coordinates.setxDir(1);
			coordinates.setyDir(0);
			
			if (coordinates.getxCoord() > 330) {
				coordinates.setyDir(-1);
				if (coordinates.getxCoord() > 400) {
					coordinates.setxDir(0);
				}
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMSOUTHTOWEST)){
			coordinates.setxDir(0);
			coordinates.setyDir(-1);
			if (coordinates.getyCoord() < 260) {
				coordinates.setxDir(-1);
				if (coordinates.getyCoord() < 118) {
					coordinates.setyDir(0);
				}
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMWESTTOSOUTH)){
			coordinates.setyDir(0);
			coordinates.setxDir(1);
			if (coordinates.getxCoord() > 260) {
				coordinates.setyDir(1);
				if (coordinates.getxCoord() > 330) {
					coordinates.setxDir(0);
				}
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMNORTHTOWEST)){
			coordinates.setyDir(1);
			coordinates.setxDir(0);
			if (coordinates.getyCoord() > 55) {
				coordinates.setxDir(-1);
				if (coordinates.getyCoord() > 118) {
					coordinates.setxDir(-1);
					coordinates.setyDir(0);
				}
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMNORTHTOSOUTH)){
			coordinates.setyDir(1);
			coordinates.setxDir(0);
		}
		else if(myHeadingDirection.equals(Direction.FROMSOUTHTONORTH)){
			coordinates.setyDir(-1);
			coordinates.setxDir(0);
		}
		
		x = coordinates.getxCoord();
		y = coordinates.getyCoord();
		x += coordinates.getxDir();
		y -= coordinates.getyDir();
		coordinates.setxCoord(x);
		coordinates.setyCoord(y);
	}
	
	/**
	 * If there is a red light the bus should move to the light and stop
	 */
	public void stopForRed() {
		
		
		if(myHeadingDirection.equals(Direction.FROMWESTTONORTH)){
			coordinates.setxDir(1);
			coordinates.setyDir(0);
			
			if (coordinates.getxCoord() > 240) {
				coordinates.setyDir(0);
				coordinates.setxDir(0);
				}
		}
		else if(myHeadingDirection.equals(Direction.FROMSOUTHTOWEST)){
			coordinates.setxDir(0);
			coordinates.setyDir(-1);
			if (coordinates.getyCoord() < 315) {
				coordinates.setxDir(0);
				coordinates.setyDir(0);
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMWESTTOSOUTH)){
			coordinates.setyDir(0);
			coordinates.setxDir(1);
			if (coordinates.getxCoord() > 240) {
				coordinates.setyDir(0);
				coordinates.setxDir(0);
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMNORTHTOWEST)){
			coordinates.setyDir(1);
			coordinates.setxDir(0);
			if (coordinates.getyCoord() > 25) {
				coordinates.setxDir(0);
				coordinates.setyDir(0);
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMNORTHTOSOUTH)){
			coordinates.setyDir(1);
			coordinates.setxDir(0);
			if (coordinates.getyCoord() > 25) {
				coordinates.setxDir(0);
				coordinates.setyDir(0);
			}
		}
		else if(myHeadingDirection.equals(Direction.FROMSOUTHTONORTH)){
			coordinates.setyDir(-1);
			coordinates.setxDir(0);
			if (coordinates.getyCoord() < 315) {
				coordinates.setxDir(0);
				coordinates.setyDir(0);
			}
		}
		
		x = coordinates.getxCoord();
		y = coordinates.getyCoord();
		x += coordinates.getxDir();
		y -= coordinates.getyDir();
		coordinates.setxCoord(x);
		coordinates.setyCoord(y);
	}
	
	/**
	 * Bus' draw method, simply draws the bus at x and y coords.
	 * @param g
	 */
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null); 
		g.setFont(font);
		g.setColor(color);
		g.drawString(String.valueOf(getPeopleAmount()), coordinates.getxCoord() + 8, coordinates.getyCoord() + 40);
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
		return coordinates.getxCoord();
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return coordinates.getyCoord();
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Method to create an image from the given filepath
	 * @param imgName
	 * @return inputImage , the loaded image from resource
	 */
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
