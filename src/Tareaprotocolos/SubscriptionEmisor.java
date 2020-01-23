/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareaprotocolos;

import protocolos.Subscription.*;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SubscriptionInitiator;

/**
 *
 * @author Aaron Jaramillo
 */
public class SubscriptionEmisor extends Agent {

    protected void setup() {
        //Se crea un mensaje de tipo SUBSCRIBE y se asocia al protocolo FIPA-Subscribe.
        ACLMessage mensaje = new ACLMessage(ACLMessage.SUBSCRIBE);
        mensaje.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
        mensaje.setContent("Enviame todas las tareas de tus compañeros.");

        //Se añade el destinatario del mensaje
        AID id = new AID();
        id.setLocalName("Maestro");
        mensaje.addReceiver(id);

        //Añadir el comportamiento
        this.addBehaviour(new SuscribirCatalogo(this, mensaje));
    }

    private class SuscribirCatalogo extends SubscriptionInitiator {

        private int suscripciones = 0;

        public SuscribirCatalogo(Agent agente, ACLMessage mensaje) {
            super(agente, mensaje);
        }

        //Maneja la respuesta en caso que acepte: AGREE
        protected void handleAgree(ACLMessage inform) {
            System.out.println(SubscriptionEmisor.this.getLocalName() + ": Solicitud aceptada.");
        }

        // Maneja la respuesta en caso que rechace: REFUSE
        protected void handleRefuse(ACLMessage inform) {
            System.out.println(SubscriptionEmisor.this.getLocalName() + ": Solicitud rechazada.");
        }

        //Maneja la informacion enviada: INFORM
        protected void handleInform(ACLMessage inform) {
            System.out.printf("%s: Tarea recibida: %s.\n", SubscriptionEmisor.this.getLocalName(), inform.getContent());

            this.suscripciones++;
            if (this.suscripciones == 10) {
                //Enviamos la cancelación de la suscripcion
                this.cancel(inform.getSender(), false);

                //Comprobamos que se ha cancelado correctamente
                this.cancellationCompleted(inform.getSender());
            }
        }

        //Maneja la respuesta en caso de fallo: FAILURE
        protected void handleFailure(ACLMessage failure) {
            //Se comprueba si el fallo viene del AMS o de otro agente.
            if (failure.getSender().equals(myAgent.getAMS())) {
                System.out.println(SubscriptionEmisor.this.getLocalName() + ": El destinatario no existe.");
            } else {
                System.out.printf("%s: El agente %s falló al intentar realizar la acción solicitada.\n",
                        SubscriptionEmisor.this.getLocalName(), failure.getSender().getName());
            }
        }

        public void cancellationCompleted(AID agente) {
            //Creamos una plantilla para solo recibir los mensajes del agente que va a cancelar la suscripción
            MessageTemplate template = MessageTemplate.MatchSender(agente);
            ACLMessage msg = blockingReceive(template);

            //Comprobamos que tipo de mensaje llegó: INFORM o FAILURE
            if (msg.getPerformative() == ACLMessage.INFORM) {
                System.out.printf("%s : Suscripcion cancelada con el agente %s.\n",
                        SubscriptionEmisor.this.getLocalName(), agente.getLocalName());
            } else {
                System.out.printf("%s: Se ha producido un fallo en la cancelación con el agente %s.\n",
                        SubscriptionEmisor.this.getLocalName(), agente.getLocalName());
            }
        }
    }
}
