package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {

	// Globales
	int xBolita = -1000;
	int yBolita = -1000;
	private Boolean juegoTerminado;
	private Tanque player1;
	private Tanque player2;
	private Boolean balaDir;
	private int balaposX, balaposY;

	private SingletonTCP conexionJ1;
	private SingletonTCP2 conexionJ2;

	private ArrayList<Municion> numeroMunicion;
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	public void settings() {
		size(500, 500);
	}

	public void setup() {

		conexionJ1 = new SingletonTCP();
		// Metodo de suscripcion (El CHISMOSO)
		conexionJ1.setMain(this); 
		conexionJ1.start();

		conexionJ2 = new SingletonTCP2();
		// Metodo de suscripcion
		conexionJ2.setMain(this); //(El CHISMOSO)
		conexionJ2.start();

		numeroMunicion = new ArrayList<Municion>();
		juegoTerminado = false;

		player1 = new Tanque(this, 100, 100, color(255, 0, 0),false,false,false,false);
		player2 = new Tanque(this, 400, 400, color(0, 0, 255),false,false,false,false);
	}

	public void draw() {
		if (!juegoTerminado) {
			
			noStroke();
			background(0, 17, 74);
			fill(255, 0, 0);
			ellipse(xBolita, yBolita, 50, 50);

			player1.pintar();
			player2.pintar();

			player1.movimientoTanque();
			player2.movimientoTanque();


			for (int i = 0; i < numeroMunicion.size(); i++) {
				Municion muni = numeroMunicion.get(i);
				muni.pintameEsta();
				balaposX = muni.getPosX();
				balaposY = muni.getPosY();
				balaDir = muni.getEstaDisparando();
			}
		}
		muerteTanque(balaposX, balaposY, player1.getPosX(), player1.getPosY(),balaDir); //P1
		muerteTanque(balaposX, balaposY, player2.getPosX(), player2.getPosY(),balaDir); //P2
	}
	// El metodo de notificacion al Chismoso
	public void notificar(Coordenada c, Object obj) { //con obj solucionamos que el programa deje de esperar ambos jugadores

		// Definimos qué jugador envia los mensajes
		if (obj instanceof SingletonTCP) { // Así vemos si el objeto es un objeto de SingletonTCP de Jugador 1 o 2
			System.out.println("JUGADOR 1: " + c.getAccion());
			switch (c.getAccion()) {

			//Arriba
			case "ARRIBAMOVER":
				player1.setMovimientoArriba(true);
				break;
			case "ARRIBAPARAR":
				player1.setMovimientoArriba(false);
				break;
				//Derecha	
			case "DERECHAMOVER":
				player1.setMovimientoDerecha(true);
				break;
			case "DERECHAPARAR":
				player1.setMovimientoDerecha(false);
				break;
				//Abajo
			case "ABAJOMOVER":
				player1.setMovimientoAbajo(true);
				break;
			case "ABAJOPARAR":
				player1.setMovimientoAbajo(false);
				break;
				//Izquierda
			case "IZQUIERDAMOVER":
				player1.setMovimientoIzquierda(true);
				break;
			case "IZQUIERDAPARAR":
				player1.setMovimientoIzquierda(false);
				break;

			case "PUMTAKATAKABUM":
				Municion balaMunicion = new Municion(player1.getPosX()+40, player1.getPosY(), true, player1.getColor(),this);
				numeroMunicion.add(balaMunicion);
				break;
			}
		}

		else if (obj instanceof SingletonTCP2) {
			System.out.println("JUGADOR 2:" + c.getAccion());
			switch (c.getAccion()) {

			//Arriba
			case "ARRIBAMOVER":
				player2.setMovimientoArriba(true);
				break;
			case "ARRIBAPARAR":
				player2.setMovimientoArriba(false);
				break;
				//Derecha	
			case "DERECHAMOVER":
				player2.setMovimientoDerecha(true);
				break;
			case "DERECHAPARAR":
				player2.setMovimientoDerecha(false);
				break;
				//Abajo
			case "ABAJOMOVER":
				player2.setMovimientoAbajo(true);
				break;
			case "ABAJOPARAR":
				player2.setMovimientoAbajo(false);
				break;
				//Izquierda
			case "IZQUIERDAMOVER":
				player2.setMovimientoIzquierda(true);
				break;
			case "IZQUIERDAPARAR":
				player2.setMovimientoIzquierda(false);
				break;
				//Disparo
			case "PUMTAKATAKABUM":
				Municion balaMunicion = new Municion(player2.getPosX()+40, player2.getPosY(), false, player2.getColor(),this);
				numeroMunicion.add(balaMunicion);
				break;
			}
		}
	}
	public void muerteTanque ( int posXBala, int posYBala, int posXTanque, int posYTanque, Boolean disparoJugador) {
		if(dist(posXBala, posYBala, posXTanque, posYTanque)<20) {
			juegoTerminado = true;
			
			if(disparoJugador) {
				background(255);
				fill(64,66,74);
				text("Rojo ha ganado", 180, 50);
				text("Haga click para salir", 180, 100);
				
			} else {
				background(255);
				fill(64,66,74);
				text("Azul ha ganado", 180, 50);
				text("Haga click para salir", 180, 100);
			}
		}
	}
	public void mouseClicked () {
		if (mouseX > 0 && mouseX < 500
				&& mouseY > 0 && mouseY < 500 && juegoTerminado) {
			exit();
		}
	}
}