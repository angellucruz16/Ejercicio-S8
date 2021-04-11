package com.example.clienteandroidtcps9;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class SingletonTCP extends Thread {
    private static  SingletonTCP unicainstancia;

    public static SingletonTCP getInstance(){

        if (unicainstancia == null){
            unicainstancia = new SingletonTCP();
        }
        return unicainstancia; //Lo hacemos para referirnos a esta clase y no generar objetos diferentes
    }

    private SingletonTCP(){
    }

    private Socket socket;
    private BufferedWriter bWriter;

    @Override
    public void run () {
        try {
            socket = new Socket("192.168.1.14", 6000);
            Log.e("::", "entrando a socket");
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            bWriter = new BufferedWriter(osw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void enviarMensaje (String mensajito){
        new Thread(
                ()->{
                    try {
                        Log.e("::", mensajito);
                        bWriter.write(mensajito + "\n");
                        bWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}




