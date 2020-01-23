/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import java.util.Random;

/**
 *
 * @author Aaron Jaramillo
 */
//-gui -port 1098 tonto1:Tarea.Agente_confort(30,50)
public class Agente_confort extends Agent {

    public static int temp_estan;
    public static int htm;

    public void setup() {

        Object[] args = getArguments();

        temp_estan = Integer.parseInt(args[0].toString());
        htm = Integer.parseInt(args[1].toString());

        Ciclodelosvalores c = new Ciclodelosvalores();

        addBehaviour(c);

    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    public class Ciclodelosvalores extends CyclicBehaviour {

        public void action() {
            int leer_temperatura = generatRandomPositiveNegitiveValue(50, 20);

            int leer_humedad = generatRandomPositiveNegitiveValue(100, 0);

            String mensaje = "";

            Random rd = new Random();

            if ( (leer_temperatura >= temp_estan) && (leer_humedad >= htm)){
                mensaje = "Ventana Abierta";
            } else if ( (leer_temperatura <= temp_estan) && (leer_humedad <= htm)){
                mensaje = "Ventana Cerrada";
            } else if ( (leer_temperatura >= temp_estan) && (leer_humedad <= htm)){
                mensaje = "Ventana Abierta";
            } else if ( (leer_temperatura <= temp_estan) && (leer_humedad >= htm)){
                mensaje = "Ventana Cerrada";
            }
            System.out.println("La temperatura es: "+leer_temperatura+"ºC");
            System.out.println("La humedad es: "+leer_humedad+", "+humeda(leer_humedad));
            System.out.println(mensaje);
            System.out.println("************************");
        }

    }

    public String humeda(double valor) {
        String mensaje = "";
        if (valor > htm) {
            mensaje = "Hace calor";
        }
        if (valor == htm) {
            mensaje = "Normal";
        }
        if (valor < htm) {
            mensaje = "Hace Frio";
        }
        return mensaje;
    }

    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }

}
