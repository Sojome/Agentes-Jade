/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
//import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author Aaron Jaramillo
 */
//-gui -port 1098 tonto1:Tarea.Agente_logico_matematico(2.0,3.0,true,false)
public class Agente_logico_matematico extends Agent {

    public static double valor1;
    public static double valor2;
    public static boolean valor3;
    public static boolean valor4;
    
    public void setup() {
        
        Object[] args = getArguments();

        valor1 = Double.parseDouble(args[0].toString());
        valor2 = Double.parseDouble(args[1].toString());
        valor3 = Boolean.valueOf(args[2].toString());
        valor4 = Boolean.valueOf(args[3].toString());
        
        Operacion c = new Operacion();
        addBehaviour(c);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    public class Operacion extends OneShotBehaviour {

        public void action() {
            System.out.println("Ejecución del comportamiento OneShotBehavior");
            
            String imp="";
            imp+="La suma = "+suma(valor1, valor2)+"\n";
            imp+="La resta = "+resta(valor1, valor2)+"\n";
            imp+="La multiplicacion = "+multi(valor1, valor2)+"\n";
            imp+="La division = "+div(valor1, valor2)+"\n";
            imp+="La operacion or = "+or(valor3, valor4)+"\n";
            imp+="La operacion and = "+and(valor3, valor4)+"\n";
            imp+="La operacion not = "+not(valor3)+"\n";
            imp+="La operacion not = "+not(valor4)+"\n";
            System.out.println(imp);
        //Si comentamos la linea de abajo matamos al agente
            //myAgent.doDelete();
        }
        
        public double suma(double n1, double n2){
            return n1+n2;            
        }
        public double resta(double n1, double n2){
            return n1-n2;            
        }
        public double multi(double n1, double n2){
            return n1*n2;            
        }
        public double div(double n1, double n2){
            return n1/n2;            
        }
        public boolean or(boolean  n1, boolean n2){
            return n1||n2;
        }
        public boolean and(boolean  n1, boolean n2){
            return n1&&n2;
        }
        public boolean not(boolean  n){
            return !n;
        }
    }
    
}
