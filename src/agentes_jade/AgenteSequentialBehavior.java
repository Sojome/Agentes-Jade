/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/**
 *
 * @author user
 */
public class AgenteSequentialBehavior extends Agent {

    public void setup() {
        //-gui -port 1098 tonto1:agentes_jade.AgenteSequentialBehavior
        SequentialBehaviour sb = new SequentialBehaviour();
        sb.addSubBehaviour(new OneShot(1));
        sb.addSubBehaviour(new OneShot(2));
        sb.addSubBehaviour(new OneShot(3));
        sb.addSubBehaviour(new OneShot(4));
        sb.addSubBehaviour(new OneShot(5));
        addBehaviour(sb);
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    private class OneShot extends OneShotBehaviour {
        private int id=0;
        public OneShot(int arg){
            this.id=arg;
        }
        
        public void action(){
            System.out.println("Ejecutando Comportamiento "+id);
        }
    }
}
