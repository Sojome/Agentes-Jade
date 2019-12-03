/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import Comportamientos.MyOneShotBehavior;
import jade.core.Agent;
//import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author user
 */
public class AgenteOneShotBehavior extends Agent {

    public void setup() {
        //-gui -port 1098 tonto1:agentes_jade.AgenteOneShotBehavior
        MyOneShotBehavior c = new MyOneShotBehavior();
        addBehaviour(c);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }
    /*private class MyOneShotBehavior extends OneShotBehaviour{
     public void action(){
     System.out.println("Ejecución del comportamiento OneShotBehavior");
     //Si comentamos la linea de abajo matamos al agente
     //myAgent.doDelete();
     }
     }*/
}
