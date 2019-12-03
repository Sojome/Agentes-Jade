/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Aaron J
 */
//-gui -port 1098 receptor:Tarea.ReceptordeClima;emisor:Tarea.EmisordeClima
public class ReceptordeClima extends Agent {

    public void setup() {
        addBehaviour(new ReceptorComportamiento());
        addBehaviour(new ReceptorComportamiento());
        addBehaviour(new ReceptorComportamiento());
        addBehaviour(new ReceptorComportamiento());
        
    }

    public class ReceptorComportamiento extends SimpleBehaviour {
        
        private boolean fin = false;

        public void action() {
            String mensaje = "No estoy autorizado para contestar";
            block();

            ACLMessage msm = receive();
            System.out.println(getLocalName()+" esta esperando recibir un mensaje");
            if (msm != null) {
                System.out.println("Acaba de recibir un mensaje");
                //System.out.println(msm.toString());
                System.out.println(msm.getContent());

                if (msm.getContent().equals("temperatura")) {
                    mensaje = "La temperatura es "+generatRandomPositiveNegitiveValue(40, 0)+" C";
                }else if (msm.getContent().equals("humedad")){
                    mensaje = "La humedad es "+generatRandomPositiveNegitiveValue(100, 0);
                }
                
                ACLMessage respuesta = msm.createReply();
                respuesta.setPerformative(ACLMessage.INFORM);
                respuesta.setContent(mensaje);
                send(respuesta);
                fin = true;
                System.out.println("****************FIN CONVERSAION****************");
            }
        }

        public boolean done() {
            return fin;
        }
    }

    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }

}
