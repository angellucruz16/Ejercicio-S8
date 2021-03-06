package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class SingletonTCP extends Thread{

	//La referencia del Chismoso 
	//Sólo requerimos de un chismoso para ambos jugadores
	private Main ref;	
	
	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(6000);
			System.out.println("Esperando cliente en el 6000...");
			Socket socket = server.accept();
			System.out.println("Player 1 Conectado");

			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// Hacer que el objeto is tenga la capacidad de leer Strings completos
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);

			while (true) {
				// Esperando mensaje
				System.out.println("Esperando mensaje...");
				String mensajeRecibido = breader.readLine();
				System.out.println(mensajeRecibido);	
				Gson gson = new Gson();
				Coordenada c = gson.fromJson(mensajeRecibido, Coordenada.class);		
				//Notificar o avisar con chismoso
				ref.notificar(c, this);  //Decimos this para que el programa sepa cuál jugador elegir
				System.out.println(mensajeRecibido);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Metodo de suscripcion
	public void setMain(Main main) {
		this.ref = main;
	}
	
	
}