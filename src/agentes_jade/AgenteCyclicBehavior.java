/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import Comportamientos.MyCyclicBehavior;
import jade.core.Agent;
//import jade.core.behaviours.CyclicBehaviour;

/**
 *
 * @author user
 */
public class AgenteCyclicBehavior extends Agent {

    public void setup() {
        //-gui -port 1098 tonto1:agentes_jade.AgenteCyclicBehavior
        MyCyclicBehavior c = new MyCyclicBehavior();
        addBehaviour(c);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }
    /*private class MyCyclicBehavior extends CyclicBehaviour{
     public void action(){
     System.out.println("Ejecución del comportamiento CyclicBehavior");
     }
     }*/
}
