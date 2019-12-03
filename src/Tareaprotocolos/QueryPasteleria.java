/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareaprotocolos;
import protocolos.Query.*;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

/**
 *
 * @author Aaron Jaramillo
 */

//-gui -port 1098 Reposteria:Tareaprotocolos.QueryPasteleria;pedro:Tareaprotocolos.QueryCliente
public class QueryPasteleria extends Agent {
 
    protected void setup() {
        System.out.println(this.getLocalName() + ": Abriendo centralita...");
 
        // Filtrado para recibir sólo mensajes del protocolo FIPA-Query.
 
        MessageTemplate plantilla = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_QUERY);
 
        this.addBehaviour(new ComprobarResponder(this, plantilla));
    }
 
    class ComprobarResponder extends AchieveREResponder {
        public ComprobarResponder(Agent agente, MessageTemplate plantilla) {
            super(agente, plantilla);
        }
 
        protected ACLMessage handleRequest(ACLMessage request)
                throws NotUnderstoodException, RefuseException {
            System.out.printf("Recepcionista: Hemos recibido una llamada de %s solicitando informacion sobre la entregado de su pastel.\n", request.getSender().getLocalName());
 
            // Si el solicitante es válido se acepta su petición.
 
            if (comprobarSolicitante(request.getSender().getLocalName())) {
                System.out.println("Recepcionista: Espere un momento por favor...");
                ACLMessage agree = request.createReply();
                agree.setPerformative(ACLMessage.AGREE);
                return agree;
            } else {
                System.out.println(this.myAgent.getLocalName() + ": Todas las recepcionistas estan ocupadas.");
                throw new RefuseException("Por favor intentelo de nuevo mas tarde");
            }
        }
 
        protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
            ACLMessage inform = request.createReply();
            inform.setPerformative(ACLMessage.INFORM);
            String retorno = "No se ha enviado ningun pastel";
 
            if (comprobarSolicitante(request.getSender().getName()))
                retorno = "Si se ha enviado el pastel";
 
            inform.setContent(retorno);
            return inform;
        }
 
        // Método simple de aceptación o rechazo de solicitudes.
        private boolean comprobarSolicitante(String nombre) {
            return (nombre.length() > 2);
        }
    }
}
