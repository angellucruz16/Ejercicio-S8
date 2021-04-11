package com.example.clienteandroidtcps9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private ImageButton botonArriba, botonAbajo, botonDerecha, botonIzquierda, botonDisparo;

    private BufferedWriter bwriter;
    private SingletonTCP tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonArriba = findViewById(R.id.botonArriba);
        botonAbajo = findViewById(R.id.botonAbajo);
        botonIzquierda = findViewById(R.id.botonIzquierda);
        botonDerecha = findViewById(R.id.botonDerecha);
        botonDisparo = findViewById(R.id.botonDisparo);

        tcp = SingletonTCP.getInstance();
        tcp.start();

        //Metodo de suscripcion
        botonArriba.setOnTouchListener(this);
        botonAbajo.setOnTouchListener(this);
        botonIzquierda.setOnTouchListener(this);
        botonDerecha.setOnTouchListener(this);
        botonDisparo.setOnClickListener(this);
    }
    //metodo de notificacion
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.botonDisparo:
                Gson gson = new Gson();
                Coordenada disparo = new Coordenada("PUMTAKATAKABUM");
                String enviarDisparo = gson.toJson(disparo);
                tcp.enviarMensaje(enviarDisparo);
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Gson gson = new Gson();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                switch (v.getId()) {
                    case R.id.botonArriba:
                        Coordenada arriba = new Coordenada("ARRIBAMOVER");
                        String enviarArriba = gson.toJson(arriba);
                        tcp.enviarMensaje(enviarArriba);
                        break;
                    case R.id.botonDerecha:
                        Coordenada derecha = new Coordenada("DERECHAMOVER");
                        String enviarDerecha = gson.toJson(derecha);
                        tcp.enviarMensaje(enviarDerecha);
                        break;
                    case R.id.botonAbajo:
                        Coordenada abajo = new Coordenada("ABAJOMOVER");
                        String enviarAbajo = gson.toJson(abajo);
                        tcp.enviarMensaje(enviarAbajo);
                        break;
                    case R.id.botonIzquierda:
                        Coordenada izquierda = new Coordenada("IZQUIERDAMOVER");
                        String enviarIzquierda = gson.toJson(izquierda);
                        tcp.enviarMensaje(enviarIzquierda);
                        break;

                }
                break;

            case MotionEvent.ACTION_UP:

                switch (v.getId()) {

                    case R.id.botonArriba:
                        Coordenada arriba = new Coordenada("ARRIBAPARAR");
                        String enviarArriba = gson.toJson(arriba);
                        tcp.enviarMensaje(enviarArriba);
                        break;
                    case R.id.botonDerecha:
                        Coordenada derecha = new Coordenada("DERECHAPARAR");
                        String enviarDerecha = gson.toJson(derecha);
                        tcp.enviarMensaje(enviarDerecha);
                        break;
                    case R.id.botonAbajo:
                        Coordenada abajo = new Coordenada("ABAJOPARAR");
                        String enviarAbajo = gson.toJson(abajo);
                        tcp.enviarMensaje(enviarAbajo);
                        break;
                    case R.id.botonIzquierda:
                        Coordenada izquierda = new Coordenada("IZQUIERDAPARAR");
                        String enviarIzquierda = gson.toJson(izquierda);
                        tcp.enviarMensaje(enviarIzquierda);
                        break;

                }
                break;
        }
        return false;
    }

}