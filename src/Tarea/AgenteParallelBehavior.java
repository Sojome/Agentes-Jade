/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import agentes_jade.*;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;

/**
 *
 * @author user
 */
public class AgenteParallelBehavior extends Agent {

    public static double[] valor = {0.0, 0.0, 0.0};

    public void setup() {
        //-gui -port 1098 tonto1:Tarea.AgenteParallelBehavior
        ParallelBehaviour sb = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
        sb.addSubBehaviour(new MyBehaviour(1, 2, "Juan"));

        sb.addSubBehaviour(new MyBehaviour(2, 2, "Pedro"));
        addBehaviour(sb);
    }

    protected void takeDown() {
        System.out.println("\n*****************");
        if (valor[1] > valor[2]) {
            System.out.printf("Juan lanzo a más lejos que Pedro: %.2f", valor[1]);
        } else if (valor[1] > valor[2]) {
            System.out.printf("Pedro lanzo a más lejos que Juan: %.2f", valor[2]);
        } else {
            System.out.println("Fue un empate");
        }
        System.out.println("\nEjecucion finalizada");
    }

    private class MyBehaviour extends Behaviour {

        private int id, cycle, currentcycle;
        private String nombre;

        public MyBehaviour(int id, int cycle, String nombre) {
            this.id = id;
            this.cycle = cycle;
            this.currentcycle = 0;
            this.nombre = nombre;
        }

        public void action() {
            double lanza = Math.random() * 50;
            System.out.println("Comportamiento " + nombre + " ejecutando ciclo " + ++currentcycle);
            System.out.printf("Lanzo un balón a %.2f m de distancia\n", lanza);
            valor[id] += lanza;
        }

        public boolean done() {
            return (currentcycle == cycle);
        }
    }
}
