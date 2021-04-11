package main;

import processing.core.PApplet;

public class Municion extends PApplet{
private int posX, posY,color;
private Boolean direccionDisparo;
private Main main;

public Municion(int posX, int posY, Boolean direccionDisparo,int color, Main main) {
	super();
	this.posX = posX;
	this.posY = posY;
	this.direccionDisparo = direccionDisparo;
	this.color = color;
	this.main = main;
}

public void pintameEsta () {
	
	main.fill(this.color);
	main.ellipse(this.posX, this.posY, 10, 10);
	//Perdon profe por los nombres de mis variables xd
	
	if(direccionDisparo) {
		posX +=6;
	} else {
		posX -=6;
	}
	}
// Setters y Guetters
public int getPosX() {
	return posX;
}

public void setPosX(int posX) {
	this.posX = posX;
}

public int getPosY() {
	return posY;
}

public void setPosY(int posY) {
	this.posY = posY;
}

public int getColor() {
	return color;
}

public void setColor(int color) {
	this.color = color;
}

public Boolean getEstaDisparando() {
	return direccionDisparo;
}

public void setEstaDisparando(Boolean estaDisparando) {
	this.direccionDisparo = estaDisparando;
}

public Main getMain() {
	return main;
}

public void setMain(Main main) {
	this.main = main;
}
}
