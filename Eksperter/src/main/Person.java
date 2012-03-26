package main;

import java.awt.Color;
import java.awt.Graphics;

public class Person {
	private int x,y,xDir,yDir;
	
	public Person(){
    	x = 285;
    	y = 85; 
		yDir = 1;
		//xDir = 1;
	}
	public void move() {
		//x += xDir;
		y += yDir;
	}
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		System.out.println(this.x);
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 20, 20);
	}
	
}
