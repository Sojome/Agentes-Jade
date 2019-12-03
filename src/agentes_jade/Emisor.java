/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author user
 */
public class Emisor extends Agent {

    public void setup() {
        addBehaviour(new EmisorComportamiento());
    }

    public class EmisorComportamiento extends SimpleBehaviour {

        private boolean fin = false;

        public void action() {
            
            System.out.println(getLocalName()+" preparando para enviar mensaje");
            AID id = new AID();
            id.setLocalName("receptor");
            
            ACLMessage msm = new ACLMessage(ACLMessage.INFORM);
            msm.setSender(getAID());
            msm.setLanguage("Español");
            msm.addReceiver(id);
            msm.setContent("Hello! How are you?");
            send(msm);
            System.out.println("Enviando mensaje a receptor");
            System.out.println(msm.toString());
            
            ACLMessage respuesta = blockingReceive();
            
            if (msm != null) {
                System.out.println(getLocalName()+" acaba de recibir un mensaje");
                System.out.println(respuesta.toString());
                fin = true;
            }
        }

        public boolean done() {
            return fin;
        }
    }
}
