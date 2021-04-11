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

	private Tanque player1;
	private Tanque player2;

	private SingletonTCP conexionJ1;
	private SingletonTCP2 conexionJ2;

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	// 1
	public void settings() {
		size(500, 500);
	}

	// 1
	public void setup() {
		
		conexionJ1 = new SingletonTCP();
		// Metodo de suscripcion (El CHISMOSO)
		conexionJ1.setMain(this); 
		conexionJ1.start();

		conexionJ2 = new SingletonTCP2();
		// Metodo de suscripcion
		conexionJ2.setMain(this); //(El CHISMOSO)
		conexionJ2.start();

		player1 = new Tanque(this, 100, 100, color(255, 0, 0));
		player2 = new Tanque(this, 400, 400, color(0, 0, 255));
	}
	 
	public void draw() {
		background(0, 0, 0);
		fill(255, 0, 0);
		ellipse(xBolita, yBolita, 50, 50);
		
		player1.pintar();
		player2.pintar();
	}

	// El metodo de notificacion al Chismoso
	public void notificar(Coordenada c, Object obj) { //con obj solucionamos que el programa deje de esperar ambos jugadores
		
		// Definimos qué jugador envia los mensajes
		if (obj instanceof SingletonTCP) { // Así vemos si el objeto es un objeto de SingletonTCP de Jugador 1 o 2
			System.out.println("JUGADOR 1: " + c.getAccion());
			switch (c.getAccion()) {

			case "ABAJO":
				player1.moveDown();
				break;
			case "ARRIBA":
				player1.moveUp();
				break;
			case "DERECHA":
				player1.moveRight();
				break;
			case "IZQUIERDA":
				player1.moveLeft();
				break;
			}

		}
		else if (obj instanceof SingletonTCP2) {
			System.out.println("JUGADOR 2:" + c.getAccion());
			switch (c.getAccion()) {

			case "DOWN":
				player2.moveDown();
				break;
			case "UP":
				player2.moveUp();
				break;
			case "RIGHT":
				player2.moveRight();
				break;
			case "LEFT":
				player2.moveLeft();
				break;
			}
		}
	}
}