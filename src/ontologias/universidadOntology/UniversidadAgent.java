/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.universidadOntology;

import jade.proto.SimpleAchieveREResponder;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.*;

import jade.content.lang.Codec;
import jade.content.*;
import jade.content.abs.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.content.lang.sl.*;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


import jade.content.onto.basic.*;
import jade.util.leap.*;
import java.util.Random;

/**
 *
 * @author Maximiliano
 */
public class UniversidadAgent extends Agent {

    // COMPORTAMIENTOS DEL AGENTE
    /**
     * Este comportamiento maneja todas las queries sobre gente que pertenece a
     * la Universidad siguiendo el protocolo FIPA-Query
     */
    class HandleUniversidadQueriesBehaviour extends SimpleAchieveREResponder {

        /**
         * Constructor para la clase
         * <code>HandleUniversidadQueriesBehaviour</code>
         *
         * @param myAgent El agente al que pertenece este comportamiento
         */
        //El comportamiento solo manejará mensajes que sigan el protocolo FIPA-QUERY y la ontología UniversidadOntology
        public HandleUniversidadQueriesBehaviour(Agent myAgent) {
            super(myAgent, MessageTemplate.and(
                    MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_QUERY),
                    MessageTemplate.MatchOntology(UniversidadOntology.ONTOLOGY_NAME)));
        }

        /**
         * Este metodo se ejecuta cuando se recibe un mensaje QUERY-IF o
         * QUERY-REF
         *
         * @param msg El mensaje QUERY recibido
         * @return El mensaje ACL que devolvemos como respuesta
         * @see jade.proto.FipaQueryResponderBehaviour
         */
        public ACLMessage prepareResponse(ACLMessage msg) {

            ACLMessage reply = msg.createReply();

            // El mensaje QUERY puede ser un QUERY-REF. En este caso responder
            // con NOT_UNDERSTOOD
            if (msg.getPerformative() != ACLMessage.QUERY_IF) {
                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                String content = "(" + msg.toString() + ")";
                reply.setContent(content);
                return (reply);
            }

            try {
                //Obtener el predicado cuya veracidad se cuestiona
                Predicate pred = (Predicate) myAgent.getContentManager().extractContent(msg);
                if (!(pred instanceof Admitida)) {
                    // Si el predicado por el que se pregunta si es verdad no es un ADMITIDA
                    //contestar con NOT-UNDERSTOOD
                    reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    String content = "(" + msg.toString() + ")";
                    reply.setContent(content);
                    return (reply);
                }
                // Respuesta (Es un query-if preguntando por un predicado admitida)
                reply.setPerformative(ACLMessage.INFORM);
                Admitida adm = (Admitida) pred;
                Persona p = adm.getPersona(); //Obtenemos la persona asociada a ese predicado
                if (((UniversidadAgent) myAgent).estaEnUniversidad(p)) //Comprobamos si ya esta admitido en la Universidad
                {
                    reply.setContent(msg.getContent());
                } else {     // Si la comprobacion da resultado negativo
                    // Crear un objeto representando el hecho de que el predicado ADMITIDA no es cierto

                    Ontology o = getContentManager().lookupOntology(UniversidadOntology.ONTOLOGY_NAME);
                    AbsPredicate not = new AbsPredicate(SLVocabulary.NOT);
                    not.set(SLVocabulary.NOT_WHAT, o.fromObject(adm));
                    myAgent.getContentManager().fillContent(reply, not);
                }
            } catch (Codec.CodecException fe) {
                System.err.println(myAgent.getLocalName() + " Error al llenar/extraer contenido. Motivo:" + fe.getMessage());
            } catch (OntologyException oe) {
                System.err.println(myAgent.getLocalName() + " getRoleName() ha fallado. Motivo:" + oe.getMessage());
            }

            return (reply);

        } // FIN del metodo handleQueryMessage()

    } // FIN del comportamiento HandleUniversidadQueriesBehaviour

    /**
     * Este comportamiento maneja una única acción de ligación/exclusión que ha
     * sido requerida siguiendo el protocolo FIPA-Request
     */
    class HandleAdmisionExclusionBehaviour extends SimpleAchieveREResponder {

        /**
         * Constructor para la clase
         * <code>HandleAdmisionExclusionBehaviour</code>.
         *
         * @param myAgent El agente al que pertenece este comportamiento
         * @param requestMsg El mensaje ACL en el que se especifica qué acción
         * de ligación ha sido requerida
         */
        public HandleAdmisionExclusionBehaviour(Agent myAgent, MessageTemplate m) {
            super(myAgent, m);
        }

        /**
         * Este método implementa la interfaz
         * <code> FipaRequestResponderBehaviour.Factory</code> Será llamado
         * dentro de un <code> FipaRequestResponderBehaviour</code> cuando una
         * acción de ligación/exclusión es requerida para instanciar un nuevo
         * <code>HandleEngageBehaviour</code> para manejar la acción requerida
         *
         * @param msg El mensaje ACL en el que se especifica qué acción de
         * ligación/exclusión ha sido requerida
         */
        /**
         * Este método maneja la acción de ligación/exclusión
         */
        public ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) {
            //Preparar un ACLMessage dummy usado para crear el contenido de todos los mensajes de respuesta
            ACLMessage msg = request.createReply();

            try {
                //Obtener la accion requerida
                Action a = (Action) myAgent.getContentManager().extractContent(request);
                Persona p = null;
                int result = 0;
                if (a.getAction() instanceof Admitir) {
                    Admitir ad = (Admitir) a.getAction();
                    p = ad.getPersona();

                    // Llevar a cabo la acción de ligación
                    result = ((UniversidadAgent) myAgent).admitir(p);
                } else {
                    if (a.getAction() instanceof Excluir) {
                        Excluir ex = (Excluir) a.getAction();
                        p = ex.getPersona();
                        Persona per = obtenerPersona(p.getDni());
                        p.setNombre(per.getNombre());

                        //Llevar a cabo la exclusión
                        result = ((UniversidadAgent) myAgent).excluir(p);
                    }
                }
                // Respuesta según el resultado
                if (result > 0) {
                    // OK --> INFORM action
                    Done d = new Done();
                    d.setAction(a);
                    myAgent.getContentManager().fillContent(msg, d);
                    msg.setPerformative(ACLMessage.INFORM);
                } else {
                    // NOT OK --> FAILURE
                    ErrorAdmision ead = new ErrorAdmision();
                    Motivo m = new Motivo();
                    m.setMotivo("No hay mas plazas para esta universidad");
                    ead.setMOTIVO(m);
                    myAgent.getContentManager().fillContent(msg, ead);
                    msg.setPerformative(ACLMessage.FAILURE);
                }

            } catch (Exception fe) {
                System.out.println(myAgent.getName() + ": Error manejando la peticion.");
                System.out.println(fe.getMessage().toString());
            }
            //Este mensaje es el que se le envía al agente AlumnoAgent, utiliza como lenguaje de contenido SL
            System.out.println(msg);

            return msg;
        }

        public ACLMessage prepareResponse(ACLMessage request) {
            //Preparar un ACLMessage dummy usado para crear el contenido de todos los mensajes de respuesta
            ACLMessage temp = request.createReply();

            try {
                // Obtener la acción requerida
                Action a = (Action) getContentManager().extractContent(request);
                Persona p = null;
                
                if (a.getAction() instanceof Admitir) {
                    Admitir ad = (Admitir) a.getAction();
                    p = ad.getPersona();
                } else {
                    if (a.getAction() instanceof Excluir) {
                        Excluir ex = (Excluir) a.getAction();
                        p = ex.getPersona();
                    }
                }
                // Generamos un número aleatorio que representa la nota de corte de la universidad
                // Si >= notaCorte --> AGREE, sino REFUSE y exit
                int notaCorte = (int) (Math.random() * 5) + 5;
                if (p.getNota() >= notaCorte) {
                    // AGREE a llevar a cabo la acción de ligación/exclusión sin
                    // ninguna condicion especial
                    ContentElementList l = new ContentElementList();
                    l.add(a);
                    l.add(new TrueProposition());
                    getContentManager().fillContent(temp, l);
                    temp.setPerformative(ACLMessage.AGREE);
                } else {
                    ContentElementList l = new ContentElementList();
                    l.add(a);
                    l.add(new NoSuperaNota());
                    getContentManager().fillContent(temp, l);
                    temp.setPerformative(ACLMessage.REFUSE);
                }

            } catch (Exception fe) {
                fe.printStackTrace();
                System.out.println(getName() + ": Error manejando la accion de admision.");
                System.out.println(fe.getMessage().toString());
            }

            return temp;
        }
    }

    // VARIABLES LOCALES DEL AGENTE
    private List admitidos;    // La gente que esta admitida en la universidad
    private int numPlazas;

    // CONSTRUCTOR DEL AGENTE
    public UniversidadAgent() {
        super();

    }

    // AGENT SETUP
    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length == 1) {
            admitidos = new ArrayList();
            numPlazas = Integer.parseInt((String) args[0]);
        } else {
            System.out.println("\n\nDebe indicar el número de plazas de la universidad");
            System.exit(0);
        }

        System.out.println("Este es el UniversidadAgent, a la espera de peticiones de alumnos para matricularse...");
        System.out.println("\nEn este momento quedan " + numPlazas + " plazas ");
        // Registrar el codec para el lenguaje SL0
        getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);

        // Registrar la ontologia usada por esta aplicacion
        getContentManager().registerOntology(UniversidadOntology.getInstance());

        // Crear y añadir el comportamiento que maneja las REQUEST usando la Universidad-ontology
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
                MessageTemplate.MatchOntology(UniversidadOntology.ONTOLOGY_NAME));
        HandleUniversidadQueriesBehaviour b = new HandleUniversidadQueriesBehaviour(this);
        HandleAdmisionExclusionBehaviour c = new HandleAdmisionExclusionBehaviour(this, mt);
        addBehaviour(b);
        addBehaviour(c);
    }

    // METODOS DEL AGENTE
    //Si el array de personas admitidas universidad esta vacio,nos respondera que la persona en cuestion
    //no esta registrada en la universidad
    boolean estaEnUniversidad(Persona p) {
        boolean admitido = false;
        Iterator i = admitidos.iterator();
        while (i.hasNext()) {
            Persona adm = (Persona) i.next();
            if (p.getDni().compareTo(adm.getDni()) == 0) {
                admitido = true;
                break;
            }
        }

        return admitido;
    }

    Persona obtenerPersona(String dni) {
        Persona admitido = null;
        Iterator i = admitidos.iterator();
        while (i.hasNext()) {
            Persona adm = (Persona) i.next();
            if (dni.compareTo(adm.getDni()) == 0) {
                admitido = adm;
                break;
            }
        }

        return admitido;
    }

    int admitir(Persona p) {
        if (admitidos.size() == numPlazas) {
            return (-1); // No hay mas plazas
        } else {
            admitidos.add(p);
            if ((numPlazas - admitidos.size()) != 1) {
                System.out.println("\nTras el registro quedan " + (numPlazas - admitidos.size()) + " plazas libres \n");
            } else {
                System.out.println("\nTras el registro queda " + (numPlazas - admitidos.size()) + " plaza libres \n");
            }
            return (1);
        }
    }

    int excluir(Persona p) {
        int result = -1;
        Iterator i = admitidos.iterator();
        while (i.hasNext()) {
            Persona adm = (Persona) i.next();
            if (p.getDni().compareTo(adm.getDni()) == 0) {
                admitidos.remove(adm);
                if ((numPlazas - admitidos.size()) != 1) {
                    System.out.println("\nTras la exclusion quedan " + (numPlazas - admitidos.size()) + " plazas libres \n");
                } else {
                    System.out.println("\nTras la exclusion queda " + (numPlazas - admitidos.size()) + " plaza libre \n");
                }
                result = 1;
                break;
            }
        }
        return result;
    }

}
