/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareaprotocolos;

import protocolos.Proponse.*;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ProposeInitiator;

/**
 *
 * @author Aaron Jaramillo
 */
public class ProposeEmisor extends Agent {

    protected void setup() {
        //Se crea un mensaje PROPOSE. Se quiere pedir permiso para salir de clase.
        ACLMessage mensaje = new ACLMessage(ACLMessage.PROPOSE);
        mensaje.setProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
        mensaje.setContent("Vas a estudiar para el examen de mañana?");

        //Se añade el destinatario.
        AID id = new AID();
        id.setLocalName("juan");
        mensaje.addReceiver(id);

        //Añadir el comportamiento
        this.addBehaviour(new Examen(this, mensaje));
    }

    private class Examen extends ProposeInitiator {

        public Examen(Agent agente, ACLMessage mensaje) {
            super(agente, mensaje);
        }

        //Manejar la respuesta en caso que acepte: ACCEPT_PROPOSAL
        protected void handleAcceptProposal(ACLMessage aceptacion) {
            System.out.printf("%s: El estudio.\n", this.myAgent.getLocalName());
            System.out.printf("%s: El aprobo el examen...\n", this.myAgent.getLocalName());
        }

        //Manejar la respuesta en caso que rechace: REJECT_PROPOSAL
        protected void handleRejectProposal(ACLMessage rechazo) {
            System.out.printf("%s: El no estudio.\n", this.myAgent.getLocalName());
            System.out.printf("%s: El tendra que repetir de grado.\n", this.myAgent.getLocalName());
        }
    }
    
}
