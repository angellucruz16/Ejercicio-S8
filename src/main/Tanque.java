package main;

import processing.core.PApplet;

public class Tanque extends PApplet{

	private int posX;
	private int posY;
	private int color;
	private Main main;
	private Boolean movimientoArriba, movimientoDerecha, movimientoIzquierda, movimientoAbajo;
	
	
	public Tanque (Main main, int x, int y, int color, Boolean movimientoArriba, Boolean movimientoDerecha, Boolean movimientoIzquierda, Boolean movimientoAbajo) {
		this.posX = x;
		this.posY = y;
		this.color = color;
		this.main = main;
		this.movimientoArriba = movimientoArriba;
		this.movimientoDerecha = movimientoDerecha;
		this.movimientoAbajo = movimientoAbajo;
		this.movimientoIzquierda = movimientoIzquierda;
	}
	
	public void pintar() {
		main.fill(this.color);
		main.ellipse(this.posX, this.posY, 50, 50);
	}
	
	/*public void moveRight() {
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
	}*/
	
	//Lo hacemos en el draw para que siemore esté ejecutandose
		public void movimientoTanque () {
			
			 if (movimientoArriba) {
				 this.posY-=6;
			 }
			 
			 if (movimientoDerecha) {
				 this.posX+=6;
			 }
			 
			 if (movimientoAbajo) {
				 this.posY+=6;
			 }
			 
			 if (movimientoIzquierda) {
				 this.posX-=6;
			 }
		}
	
	//Setters y Guetters

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

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Boolean getMovimientoArriba() {
		return movimientoArriba;
	}

	public void setMovimientoArriba(Boolean movimientoArriba) {
		this.movimientoArriba = movimientoArriba;
	}

	public Boolean getMovimientoDerecha() {
		return movimientoDerecha;
	}

	public void setMovimientoDerecha(Boolean movimientoDerecha) {
		this.movimientoDerecha = movimientoDerecha;
	}

	public Boolean getMovimientoIzquierda() {
		return movimientoIzquierda;
	}

	public void setMovimientoIzquierda(Boolean movimientoIzquierda) {
		this.movimientoIzquierda = movimientoIzquierda;
	}

	public Boolean getMovimientoAbajo() {
		return movimientoAbajo;
	}

	public void setMovimientoAbajo(Boolean movimientoAbajo) {
		this.movimientoAbajo = movimientoAbajo;
	}

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