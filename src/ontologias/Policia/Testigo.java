/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Policia;

import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.*;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;

import ontologias.Policia.*;

import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import java.io.*;

/**
 *
 * @author Aaron Jaramillo
 */
public class Testigo extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontologia = PoliciaOntology.getInstance();

    protected void setup() {

        try {

            Object[] args = getArguments();
            if (args != null && args.length > 0) {

                System.out.println("Acaban de robar, solicitando ayuda a la comisaria de policia...");
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

                for (int i = 0; i < args.length; i++) {
                    msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
                }

                // Se establece el protocolo de comunicaciones
                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

                // Se establece el lenguaje de contenido y la ontología del mensaje.
                msg.setLanguage(codec.getName());
                msg.setOntology(ontologia.getName());

                // Obtener los detalles del ladron a buscar
                try {
                    BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                    Ladron l = new Ladron();
                    Descripcion d = new Descripcion();

                    System.out.println("");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("    INTRODUCE los detalles del ladron a buscar:");
                    System.out.print("      Color de pelo --> ");
                    d.setColorPelo(buff.readLine());
                    System.out.print("      Altura aproximada (cm) ---> ");
                    d.setAltura(new Integer(buff.readLine()));
                    System.out.print("      Peso aproximado -----> ");
                    d.setPeso(new Integer(buff.readLine()));
                    l.setDescripcion(d);

                    System.out.println("");
                    System.out.println("-----------------------------------------------------");
                    System.out.println("");

                    // Crea la accion DetenerLadron
                    DetenerLadron dl = new DetenerLadron();
                    dl.setLadron(l);

                    // Registra el lenguaje de contenido y la ontologia utilizada
                    getContentManager().registerLanguage(codec);
                    getContentManager().registerOntology(ontologia);

                    // Crea la accion a enviar asociándola a la acción DetenerLadron creada
                    Action a = new Action(getAID(), dl);

                    // Rellena el mensaje ACL para que sea consistente con el lenguaje de contenido y la ontologia
                    getContentManager().fillContent(msg, a);

                    addBehaviour(new ManejadorInitiator(this, msg));
                } catch (IOException ioe) {
                    System.err.println("Error I/O: " + ioe.getMessage());
                }

            } else {
                System.out.println("Especifique el nombre de al menos algun agente comisaria.");
            }

        } catch (jade.content.lang.Codec.CodecException ce) {
            System.out.println(ce);

        } catch (jade.content.onto.OntologyException oe) {
            System.out.println(oe);
        }
    }

    class ManejadorInitiator extends AchieveREInitiator {

        public ManejadorInitiator(Agent a, ACLMessage msg) {
            super(a, msg);
        }

        protected void handleAgree(ACLMessage agree) {
            try {
                // Decodifica el mensaje ACL recibido mediante el lenguaje de contenido y la ontologia actuales
                ContentElement ce = getContentManager().extractContent(agree);

                if (ce instanceof Disponible) {
                    // Recibido un AGREE con contenido correcto
                    Disponible disp = (Disponible) ce;   // Transforma el contenido en el objeto predicado EstoyDisponible de la ontologia

                    System.out.println("La comisaria " + agree.getSender().getName() + " informa que va a encontrar al ladron. Tardara en llegar " + disp.getTiempo().getTiempo() + " min");
                } else {
                    System.out.println("Recibido mensaje de tipo AGREE desde comisaria " + agree.getSender().getName() + " cuyo contenido no es el esperado.");
                }

            } catch (Exception ce) {
                System.out.println(ce);
            }
        }

        protected void handleInform(ACLMessage inform) {
            try {

                // Decodifica el mensaje ACL recibido mediante el lenguaje de contenido y la ontologia actuales
                ContentElement ce = getContentManager().extractContent(inform);

                if (ce instanceof LadronDetenido) {
                    // Recibido un INFORM con contenido correcto
                    LadronDetenido ld = (LadronDetenido) ce; // Transforma el contenido en el objeto predicado LadronDetenido de la ontologia

                    System.out.println("La comisaria " + inform.getSender().getName() + " informa que ha detenido al ladron!!");
                } else {
                    System.out.println("Recibido mensaje de tipo INFORM desde la comisaria " + inform.getSender().getName() + " cuyo contenido no es el esperado.");
                }

            } catch (Exception ce) {
                System.out.println(ce);
            }
        }

        protected void handleRefuse(ACLMessage refuse) {
            try {

                // Decodifica el mensaje ACL recibido mediante el lenguaje de contenido y la ontologia actuales
                ContentElement ce = getContentManager().extractContent(refuse);
                if (ce instanceof NoDisponible) {
                    // Recibido un REFUSE con contenido correcto
                    NoDisponible nd = (NoDisponible) ce; // Transforma el contenido en el objeto predicado NoDisponible de la ontologia

                    System.out.println("La comisaria " + refuse.getSender().getName() + " informa que NO puede detener al ladron. Motivo: " + nd.getMotivo().getMotivo());
                } else {
                    System.out.println("Recibido mensaje de tipo REFUSE desde comisaria " + refuse.getSender().getName() + " cuyo contenido no es el esperado.");
                }

            } catch (Exception ce) {
                ce.printStackTrace();
            }
        }

        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.println("La comisaria " + notUnderstood.getSender().getName() + " no entiende el mensaje.");
        }

    }
}
