/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareaprotocolos;
import protocolos.Query.*;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

/**
 *
 * @author Aaron Jaramillo
 */
public class QueryCliente extends Agent {
 
    protected void setup() {
 
        //Creamos el mensaje de la consulta.
 
        ACLMessage mensaje = new ACLMessage(ACLMessage.QUERY_IF);
        mensaje.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
        mensaje.setContent("¿Mi pastel ya fue entregado?");
 
        AID id = new AID();
        id.setLocalName("Reposteria");
        mensaje.addReceiver(id);
 
        //Añadimos el comportamiento de la consulta.
 
        this.addBehaviour(new ComprobarInitiator(this, mensaje));
    }
 
    class ComprobarInitiator extends AchieveREInitiator {
        public ComprobarInitiator(Agent agente, ACLMessage mensaje) {
            super(agente, mensaje);
        }
 
        protected void handleAgree(ACLMessage agree) {
            System.out.printf("Espere un momento por favor, estamos buscando en la Base de Datos de pasteles a entregar.\n", agree.getSender().getLocalName());
        }
 
        protected void handleRefuse(ACLMessage refuse) {
            System.out.printf("%s: En estos momentos todas las recepcionistas estan ocupadas. No podemos atenderle.\n", this.myAgent.getLocalName(), refuse.getSender().getLocalName());
        }
 
        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.printf("%s: La recepcionista no entiende el mensaje.\n", this.myAgent.getLocalName(), notUnderstood.getSender().getLocalName());
        }
 
    protected void handleInform(ACLMessage inform) {
            System.out.printf("La recepcionista informa: %s.\n", inform.getContent());
        }
 
        protected void handleFailure(ACLMessage fallo) {
            System.out.println(this.myAgent.getLocalName() + ": Se ha producido un fallo.");
        }
    }
}
