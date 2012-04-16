package main;

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
 * Even -> Hvor skal det bestemmes hvor bussen kommer fra?
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
	
	public Bus(int x, int y, String numberPeople, Direction mHD){
		xDir = 1;
		yDir = 0;
		coordinates = new GPSCoordinates(x,y,xDir,yDir);
		this.x = coordinates.getxCoord();
		this.y = coordinates.getyCoord();
		personQueue = numberPeople;
		waitingAtOverpass = false;
		myHeadingDirection = mHD;
		
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
	
	//Move da BUZ
	public void move() {
		//Hardcode direction change in order to test bus movement/animation - make this universal 
		
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
			if (coordinates.getxCoord() > 270) {
				coordinates.setyDir(1);
				if (coordinates.getyCoord() < 130) {
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
	
	public void stopForRed() {
		//Hardcode direction change in order to test bus movement/animation - make this universal 
		
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
