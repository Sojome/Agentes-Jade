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
 * @author Aaron J
 */
//-gui -port 1098 tonto1:Tarea.Agente_confort(43.3)
public class Agente_confort extends Agent {

    public static double estandar;

    public void setup() {

        Object[] args = getArguments();

        estandar = Double.parseDouble(args[0].toString());

        Ciclodelosvalores c = new Ciclodelosvalores();

        addBehaviour(c);

    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    public class Ciclodelosvalores extends CyclicBehaviour {

        public void action() {
            double leer_temperatura = (double) generatRandomPositiveNegitiveValue(50, 20);

            double leer_humedad = (double) generatRandomPositiveNegitiveValue(100, 0);

            Random rd = new Random();
            boolean leer_estado_ventanas = rd.nextBoolean();

            if (leer_temperatura > estandar) {
                leer_estado_ventanas = true;
                System.out.println("Ventana Abierta");
            } else {
                leer_estado_ventanas = true;
                System.out.println("Ventana Cerrada");
            }
            System.out.println(humeda(leer_humedad));
            System.out.println("************************");
        }

    }

    public String humeda(double valor) {
        String mensaje = "";
        if (valor > estandar) {
            mensaje = "Hace calor";
        }
        if (valor == estandar) {
            mensaje = "Normal";
        }
        if (valor < estandar) {
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
