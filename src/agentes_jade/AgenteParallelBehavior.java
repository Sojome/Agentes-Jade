/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;

/**
 *
 * @author user
 */
public class AgenteParallelBehavior extends Agent {

    public void setup() {
        //-gui -port 1098 tonto1:agentes_jade.AgenteParallelBehavior
        ParallelBehaviour sb = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
        sb.addSubBehaviour(new MyBehaviour(1, 3));
        sb.addSubBehaviour(new MyBehaviour(2, 4));
        sb.addSubBehaviour(new MyBehaviour(3, 2));
        addBehaviour(sb);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    private class MyBehaviour extends Behaviour {
        private int id, cycle, currentcycle;
        public MyBehaviour(int a_id, int a_cycle){
            this.id=a_id;
            this.cycle=a_cycle;
            this.currentcycle=0;
        }
        
        public void action(){
            System.out.println("Comportamiento "+id+" ejecutando ciclo "+ ++currentcycle);
        }
        
        public boolean done(){
            return(currentcycle==cycle);
        }
    }
}
