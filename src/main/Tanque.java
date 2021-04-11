package main;

import processing.core.PApplet;

public class Tanque extends PApplet{

	private int posX;
	private int posY;
	private int color;
	private Main main;
	
	public Tanque(Main main, int x, int y, int color) {
		this.posX = x;
		this.posY = y;
		this.color = color;
		this.main = main;
	}
	
	public void pintar() {
		main.fill(this.color);
		main.ellipse(this.posX, this.posY, 50, 50);
	}
	
	public void moveRight() {
		this.posX+=6;
	}
	
	public void moveLeft() {
		this.posX-=6;
	}
	public void moveUp() {
		this.posY-=6;
	}
	public void moveDown() {
		this.posY+=6;
	}
	
	//Setters y Guetters

	public int getX() {
		return posX;
	}

	public void setX(int x) {
		this.posX = x;
	}

	public int getY() {
		return posY;
	}

	public void setY(int y) {
		this.posY = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	
	
	
	
}