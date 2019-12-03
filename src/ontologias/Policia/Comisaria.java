/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Policia;

import ontologias.Policia.*;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.*;
import jade.content.onto.basic.*;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.FailureException;

/**
 *
 * @author Aaron Jaramillo
 */
public class Comisaria extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontologia = PoliciaOntology.getInstance();
    private double DISTANCIA_MAX;
    private double DISTANCIA_ROBO;

    protected void setup() {
        DISTANCIA_MAX = (Math.random() * 10);
        DISTANCIA_ROBO = (Math.random() * 10);
        System.out.println("[Comisaria " + getLocalName() + "] : Pendiente de avisos...");

        // Crea una plantilla que solo acepte los mensajes ACL recibidos que utilizan el protocolo FIPA_REQUEST
        // Y ademas que sean del tipo REQUEST
        // Y ademas que esten codificados mediante el lenguaje de contenido SLCodec
        // Y que utilicen la ontologia policiaOntology
        MessageTemplate protocolo = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        MessageTemplate performativa = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        MessageTemplate lenguajeContenido = MessageTemplate.MatchLanguage(codec.getName());
        MessageTemplate ontoTemplate = MessageTemplate.MatchOntology(ontologia.getName());

        MessageTemplate plantilla = MessageTemplate.and(MessageTemplate.and(protocolo, performativa), MessageTemplate.and(lenguajeContenido, ontoTemplate));

        // Registra el lenguaje de contenido y la ontologia en el gestor de contenidos
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);

        addBehaviour(new ManejadorResponder(this, plantilla));
    }

    class ManejadorResponder extends AchieveREResponder {

        public ManejadorResponder(Agent a, MessageTemplate mt) {
            super(a, mt);
        }

        protected ACLMessage prepareResponse(ACLMessage request) {

            ACLMessage respuesta = request.createReply();
            try {
                // Decodifica el mensaje ACL recibido a un objeto de tipo Action mediante el lenguaje de contenido y la ontologia actuales
                Action a = (Action) myAgent.getContentManager().extractContent(request);
                // Recupera el objeto accion de la ontologia
                DetenerLadron dl = (DetenerLadron) a.getAction();

        // Recibido un INFORM con contenido correcto
                String colorPelo = dl.getLadron().getDescripcion().getColorPelo();
                float altura = dl.getLadron().getDescripcion().getAltura();
                int peso = dl.getLadron().getDescripcion().getPeso();

                System.out.println("[Comisaria " + getLocalName() + "] : Hemos recibido una llamada de " + request.getSender().getName() + " avisando de un robo.");
                System.out.println("[Comisaria " + getLocalName() + "] : Intentando localizar su ubicacion...");

                if (DISTANCIA_ROBO <= DISTANCIA_MAX) {
                    System.out.println("[Comisaria " + getLocalName() + "] : Salimos a todo meter en busca de un hombre que responde a la siguiente descripcion:");
                    System.out.println("----------------------------------------");
                    System.out.println("  Color de pelo:      " + colorPelo);
                    System.out.println("  Altura aproximada:  " + altura / 100 + " m");
                    System.out.println("  Peso aproximado:    " + peso + " kg");
                    System.out.println("----------------------------------------");
                    System.out.println("");

                    respuesta.setPerformative(ACLMessage.AGREE);

                    // Crea un predicado Disponible de la ontologia añadiandole el concepto TiempoLlegada
                    TiempoLlegada tl = new TiempoLlegada();
                    tl.setTiempo((int) (Math.random() * 10));
                    Disponible disp = new Disponible();
                    disp.setTiempo(tl);

                    getContentManager().fillContent(respuesta, disp);
                } else {
                    System.out.println("[Comisaria " + getLocalName() + "]: Ubicacion del robo fuera de nuestro ambito de actuacion. Pasamos!!");
                    respuesta.setPerformative(ACLMessage.REFUSE);

                    // Crea un predicado NoDisponible de la ontologia y le añade el concepto Motivo
                    Motivo m = new Motivo();
                    m.setMotivo("Robo demasiado lejos.");
                    NoDisponible nd = new NoDisponible();
                    nd.setMotivo(m);

                    getContentManager().fillContent(respuesta, nd);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(getLocalName() + ": Error manejando la acción de detener ladron");
                System.out.println(ex.getMessage().toString());
            }

            return respuesta;
        }

        protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) {

            // Si la policia fue a detener al ladron
            ACLMessage inform = request.createReply();

            try {
                ContentElement ce = getContentManager().extractContent(response);

                Disponible disp = (Disponible) ce;
                System.out.println("[Comisaria " + getLocalName() + "]: Hemos conseguido detener al ladron!!");
                inform.setPerformative(ACLMessage.INFORM);

                Action a = (Action) myAgent.getContentManager().extractContent(request);

                DetenerLadron dl = (DetenerLadron) a.getAction();
                // Crea un predicado LadronDetenido de la ontologia y le añade el ladron detenido
                LadronDetenido ld = new LadronDetenido();
                ld.setLadron(dl.getLadron());
                getContentManager().fillContent(inform, ld);

            } catch (Exception ex) {
                System.out.println(getLocalName() + ": Error manejando el predicado Disponible");
                System.out.println(ex.getMessage().toString());
            }

            return inform;
        }

    }
}
