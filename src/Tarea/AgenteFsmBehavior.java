/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import agentes_jade.*;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author Aaron Jaramillo
 */
 //-gui -port 1098 tonto1:Tarea.AgenteFsmBehavior
public class AgenteFsmBehavior extends Agent {

    // State names
    private static final String Estado_A = "A";
    private static final String Estado_B = "B";
    private static final String Estado_C = "C";
    private static final String Estado_D = "D";

    protected void setup() {
        FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                System.out.println("FSM behaviour completado.");
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        // Register state A (first state)
        fsm.registerFirstState(new NamePrinter(), Estado_A);

        // Register state B
        fsm.registerState(new RandomGenerator(3), Estado_B);

        fsm.registerState(new RandomGenerator(4), Estado_C);

        // Register state D (final state)
        fsm.registerLastState(new NamePrinter(), Estado_D);

        // Register the transitions
        fsm.registerDefaultTransition(Estado_A, Estado_B);

        fsm.registerTransition(Estado_B, Estado_A, 0);

        fsm.registerTransition(Estado_B, Estado_B, 1);

        fsm.registerTransition(Estado_B, Estado_C, 2);

        fsm.registerTransition(Estado_C, Estado_A, 0);

        fsm.registerTransition(Estado_C, Estado_D, 1);

        fsm.registerTransition(Estado_C, Estado_B, 2);

        fsm.registerTransition(Estado_C, Estado_C, 3);

        addBehaviour(fsm);
    }

    /**
     * Inner class NamePrinter. This behaviour just prints its name
     */
    private class NamePrinter extends OneShotBehaviour {

        public void action() {
            //System.out.println("Ejecutando estado " + getBehaviourName());
            dar_mensaje(getBehaviourName());
        }
    }

    /**
     * Inner class RandomGenerator.
     *
     * Este comportamiento imprime su nombre y sale con un valor aleatorio entre
     * 0 y un valor entero dado
     */
    private class RandomGenerator extends NamePrinter {

        private int maxExitValue;
        private int exitValue;

        private RandomGenerator(int max) {
            super();
            maxExitValue = max;
        }

        public void action() {
            //System.out.println("Ejecutando estado " + getBehaviourName());
            exitValue = (int) (Math.random() * maxExitValue);
            //System.out.println("El valor es " + exitValue);
            dar_mensaje(getBehaviourName());
        }

        public int onEnd() {
            return exitValue;
        }
    }

    public static void dar_mensaje(String nombre) {
        String mensaje = "";
        int temp = generatRandomPositiveNegitiveValue(30, 0);
        mensaje = "El termostato " + nombre
                + " esta recibiendo una tempura de "
                + temp
                + " ºC. "
        ;
        if (temp > 15) {
            mensaje += "La ventana se abre.";
        } else {
            mensaje += "La ventana se cierra.";
        }
        mensaje += "\n ******************************";
        System.out.println(mensaje);
    }

    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }
}
