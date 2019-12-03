/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import agentes_jade.*;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author Aaron J
 */
public class AgenteSequentialBehavior extends Agent {

    public void setup() {
        //-gui -port 1098 tonto1:Tarea.AgenteSequentialBehavior
        int n = 5;
        int max = 3;
        SequentialBehaviour sb = new SequentialBehaviour();
        
        for(int i=1; i<=max; i++)
        {
            sb.addSubBehaviour(new OneShot(max, (Math.random() * n), (Math.random() * n)));
        }
        addBehaviour(sb);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    private class OneShot extends OneShotBehaviour {

        private int id;
        private double a;
        private double b;

        public OneShot(int id, double a, double b) {
            this.id = id;
            this.a = (double)a;
            this.b = (double)b;
        }

        public void action() {
            System.out.println("Ejecutando Comportamiento " + id);
            operacion(a, b);
        }
    }

    public void operacion(double a, double b) {
        int exitValue = generatRandomPositiveNegitiveValue(3, 0);
        double operacion = 0;
        String mensaje="";
        switch (exitValue) {
            case 0:
                operacion = a + b;
                mensaje="Se ha realizado una suma";
                break;
            case 1:
                operacion = a - b;
                mensaje="Se ha realizado una resta";
                break;
            case 2:
                operacion = a * b;
                mensaje="Se ha realizado una multiplicacion";
                break;
            case 3:
                operacion = a / b;
                mensaje="Se ha realizado una division";
                break;
        }
        System.out.print(mensaje);        
        System.out.printf(" %.2f y %.2f como resultado dio %.2f",a,b,operacion);
        System.out.println("");
    }
    
    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }
}
