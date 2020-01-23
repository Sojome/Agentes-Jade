/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Aaron Jaramillo
 */
public class EmisordeClima extends Agent {

    public void setup() {
        addBehaviour(new EmisorComportamiento("hola", this));
        addBehaviour(new EmisorComportamiento("temperatura", this));
        addBehaviour(new EmisorComportamiento("humedad", this));
        addBehaviour(new EmisorComportamiento("chao", this));
    }

    public class EmisorComportamiento extends SimpleBehaviour {
        
        private String mensaje;

        public EmisorComportamiento(String mensaje, Agent a) {
            super(a);
            this.mensaje = mensaje;
        }
        
        
        
        private boolean fin = false;

        public void action() {
            
            System.out.println(getLocalName()+" preparando para enviar mensaje");
            AID id = new AID();
            id.setLocalName("receptor");
            
            ACLMessage msm = new ACLMessage(ACLMessage.INFORM);
            msm.setSender(getAID());
            msm.setLanguage("Español");
            msm.addReceiver(id);
            msm.setContent(mensaje);
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
