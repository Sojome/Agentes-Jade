/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.universidadOntology;

import jade.lang.acl.ACLMessage;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAException;
import jade.domain.FIPANames;

import jade.proto.SimpleAchieveREInitiator;

import jade.content.lang.Codec;
import jade.content.lang.sl.*;

import jade.domain.*;
import jade.content.*;
import jade.content.abs.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;


import java.util.Vector;
import jade.util.leap.*;
import java.io.*;

/**
 *
 * @author Maximiliano
 */
public class SecretarioAgent extends Agent {
    private Codec codec = new SLCodec();
    private Ontology ontologia = (Ontology) UniversidadOntology.getInstance();
    public static int seleccion = 0;

    // COMPORTAMIENTOS DEL AGENTE
    /**
     * Comportamiento principal del RequesterAgent Primero se pide al usuario la
     * accion que desea llevar a cabo [admitir|excluir] [Admitir] se pide al
     * usuario los detalles de la persona se comprueba que la persona indicada
     * no pertenece a la universidad de acuerdo con la comprobacion anterior se
     * solicita la admison [Excluir] se pide al usuario el dni de la persona se
     * comprueba que la persona indicada pertenece a la universidad de acuerdo
     * con la comprobación anterior se solicita la exclusion Este comportamiento
     * se ejecuta ciclicamente
     */
    class HandleAdmisionExclusionBehaviour extends SequentialBehaviour {

        // Variables locales
        Behaviour queryBehaviour = null;
        Behaviour requestBehaviour = null;

        // Constructor
        public HandleAdmisionExclusionBehaviour(Agent myAgent) {
            super(myAgent);
        }

        // Esto es ejecutado al principio del comportamiento
        public void onStart() {
            // Obtener los detalles de la persona a incluir en la universidad
            try {
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                String opcion = "";
                String dni = "";
                String nota = "";
                int notaMedia;
                do {
                    System.out.println("  \n\nBIENVENIDO, soy el encargado de gestionar la reserva de plazas para la universidad\n");
                    System.out.println("  Puedes escoger entre las siguientes opciones:");
                    System.out.println("  --> 1. Admitir a una persona en la universidad ");
                    System.out.println("  --> 2. Excluir a una persona en la universidad ");
                    System.out.println("  Que deseas hacer? [1|2]");
                    opcion = buff.readLine();
                } while (!(opcion.equalsIgnoreCase("1") || opcion.equalsIgnoreCase("2")));
                Persona p = new Persona();
                if (opcion.equalsIgnoreCase("1")) {
                    seleccion = 1;
                    System.out.println("  \nIntroduce los detalles de la persona a registrar\n");
                    System.out.print("  Nombre de la persona --> ");
                    p.setNombre(buff.readLine());
                    System.out.print("  Titulacion --> ");
                    p.setTitulacion(buff.readLine());
                    do {
                        System.out.print("  Nota media de la persona   --> ");
                        nota = buff.readLine();
                        notaMedia = Integer.parseInt(nota);

                    } while (notaMedia > 10 || notaMedia < 0);
                    p.setNota(notaMedia);
                }
                if (opcion.equalsIgnoreCase("2")) {
                    seleccion = 2;
                    System.out.println("  \nIntroduce los detalles de la persona a excluir");
                    p.setNombre("");
                    p.setNota(99);
                }
                do {
                    System.out.print("  DNI (8 digitos)      --> ");
                    dni = buff.readLine();
                } while (dni.length() != 8);
                p.setDni(dni);
                System.out.print("\n");

                //Crear un objeto representando el hecho de que la persona p esta admitida en la universidad, mediante el predicado Works-for
                Admitida adm = new Admitida();
                adm.setPersona(p);  //La persona cuyos datos hemos obtenido por consola

                Ontology o = myAgent.getContentManager().lookupOntology(UniversidadOntology.ONTOLOGY_NAME);
                //Crear un mensaje ACL para hacer una peticion al UniversidadAgent para que nos diga si el predicado de arriba es verdadero o falso
                //Es decir, intentamos verficar si la persona se encuentra ya registrada en la universidad o no
                ACLMessage queryMsg = new ACLMessage(ACLMessage.QUERY_IF);
                queryMsg.addReceiver(((SecretarioAgent) myAgent).universidad);
                queryMsg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
                queryMsg.setOntology(UniversidadOntology.ONTOLOGY_NAME);
                //Escribe el predicado Admitida en el mensaje

                try {
                    myAgent.getContentManager().fillContent(queryMsg, adm);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Crea y añade un comportamiento para hacer peticiones al UniversidadAgent para saber
                // si una persona p ya esta admitida en la universidad siguiendo el protocolo FIPA-Query
                //Le pasamos como parametro el mensaje con el predicado admitida que queremos chequear
                queryBehaviour = new CheckAlreadyAdmitidaBehaviour(myAgent, queryMsg);
                addSubBehaviour(queryBehaviour);
            } catch (IOException ioe) {
                System.err.println("Error I/O: " + ioe.getMessage());
            }

        }

        //Esto se ejecuta el final del comportamiento
        public int onEnd() {
            //Comprobar si el usuario quiere continuar
            try {
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                String stop = "";
                do {
                    System.out.println("  \nQuieres continuar? [s|n] ");
                    stop = buff.readLine();
                    if (stop.equalsIgnoreCase("s")) {
                        reset(); // Esto hace que el comportamiento se ejecute ciclicamente
                        myAgent.addBehaviour(this);
                    } else {
                        myAgent.doDelete(); // Exit
                        System.exit(0);
                    }
                } while (!(stop.equalsIgnoreCase("s") || stop.equalsIgnoreCase("n")));
            } catch (IOException ioe) {
                System.err.println("Error I/O: " + ioe.getMessage());
            }
            return 0;
        }

        //Extiende el metodo reset para poder quitar los sub-comportamientos
        //que son añadidos dinamicamente
        public void reset() {
            if (queryBehaviour != null) {
                removeSubBehaviour(queryBehaviour);
                queryBehaviour = null;
            }
            if (requestBehaviour != null) {
                removeSubBehaviour(requestBehaviour);
                requestBehaviour = null;
            }
            super.reset();
        }
    }

    /**
     * Este comportamiento encapsula la comprobacion de que la persona indicada
     * esta o no esta actualmente admitida en la universidad Esto se hace
     * siguiendo el protocolo de interaccion FIPA-Query
     */
    class CheckAlreadyAdmitidaBehaviour extends SimpleAchieveREInitiator {

        // Constructor
        public CheckAlreadyAdmitidaBehaviour(Agent myAgent, ACLMessage queryMsg) {
            super(myAgent, queryMsg);
            queryMsg.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
        }

        protected void handleInform(ACLMessage msg) {
            try {
                AbsPredicate cs = (AbsPredicate) myAgent.getContentManager().extractAbsContent(msg);
                Ontology o = myAgent.getContentManager().lookupOntology(UniversidadOntology.ONTOLOGY_NAME);
                if (cs.getTypeName().equals(UniversidadOntology.ADMITIDA)) {
                    if (seleccion == 1) {
                        //La persona indicada ya esta admitida en la universidad
                        // Informar al usuario, ya no vamos a pedir la admision
                        Admitida adm = (Admitida) o.toObject((AbsObject) cs);
                        Persona p = (Persona) adm.getPersona();
                        System.out.println("  /*/ La persona " + p.getNombre() + " ya esta admitida en la universidad.");
                    }
                    if (seleccion == 2) {
                        Predicate pred = (Predicate) myAgent.getContentManager().extractContent(msg);
                        Admitida adm = (Admitida) pred;
                        Persona p = (Persona) adm.getPersona();
                        Excluir ex = new Excluir();
                        ex.setPersona(p);
                        Action a = new Action();
                        a.setActor(((SecretarioAgent) myAgent).universidad);
                        a.setAction(ex);

                        ACLMessage requestMsg = new ACLMessage(ACLMessage.REQUEST);
                        requestMsg.addReceiver(((SecretarioAgent) myAgent).universidad);
                        requestMsg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
                        requestMsg.setOntology(UniversidadOntology.ONTOLOGY_NAME);
                        // Escribir la accion en el mensaje

                        try {
                            myAgent.getContentManager().fillContent(requestMsg, a);
                        } catch (Exception pe) {
                        }
                        //Crear y añadir un comportamiento para pedirle al UniversidadAgent la admision
                        //de la persona p siguiendo el protocolo FIPA-Request
                        ((HandleAdmisionExclusionBehaviour) parent).requestBehaviour = new RequestAdmisionExclusionBehaviour(myAgent, requestMsg);
                        ((SequentialBehaviour) parent).addSubBehaviour(((HandleAdmisionExclusionBehaviour) parent).requestBehaviour);
                    }
                } else if (cs.getTypeName().equals(SLVocabulary.NOT)) {
                    if (seleccion == 1) {
                        //La persona indicada NO esta en la universidad
                        System.out.println("  /*/ La persona indicada no esta en la universidad, pediremos su admision...");
                        //Obtener los detalles de la persona
                        Admitida adm = (Admitida) o.toObject(cs.getAbsObject(SLVocabulary.NOT_WHAT));
                        Persona p = (Persona) adm.getPersona();
                        Admitir ad = new Admitir();
                        ad.setPersona(p);
                        Action a = new Action();
                        a.setActor(((SecretarioAgent) myAgent).universidad);
                        a.setAction(ad);

                        //Crear un mensaje ACL request para pedir la accion que hemos construido
                        ACLMessage requestMsg = new ACLMessage(ACLMessage.REQUEST);
                        requestMsg.addReceiver(((SecretarioAgent) myAgent).universidad);
                        requestMsg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
                        requestMsg.setOntology(UniversidadOntology.ONTOLOGY_NAME);
                        // Escribir la accion en el mensaje

                        try {
                            myAgent.getContentManager().fillContent(requestMsg, a);
                        } catch (Exception pe) {
                        }
                        //Crear y añadir un comportamiento para pedirle al UniversidadAgent la admision
                        //de la persona p siguiendo el protocolo FIPA-Request
                        ((HandleAdmisionExclusionBehaviour) parent).requestBehaviour = new RequestAdmisionExclusionBehaviour(myAgent, requestMsg);
                        ((SequentialBehaviour) parent).addSubBehaviour(((HandleAdmisionExclusionBehaviour) parent).requestBehaviour);
                    }
                    if (seleccion == 2) {
                        System.out.println("  /*/ La persona indicada no esta admitida en la universidad, no es posible realizar la exclusion.");
                    }
                } else {
                    //Respuesta inesperada recibida del SecretarioAgent
                    // Informar al usuario
                    System.out.println("  /*/ Respuesta inesperada del SecretarioAgent");
                }

            } // Fin del try
            catch (Codec.CodecException fe) {
                System.err.println("FIPAException al llenar/extraer Msgcontent:" + fe.getMessage());
            } catch (OntologyException fe) {
                System.err.println("OntologyException en getRoleName:" + fe.getMessage());
            }
        }

        protected void handleNotUnderstood(ACLMessage msg) {
            System.out.println("  /*/ La petición de comprobación no ha sido entendida por el SecretarioAgent");
        }

    }

    /**
     * Este comportamiento encapsula la peticion de admision o exclusion de la
     * persona indicada en la universidad. Esto se hace siguiendo el protocolo
     * de interaccion FIPA-Request
     */
    class RequestAdmisionExclusionBehaviour extends SimpleAchieveREInitiator {

        // Constructor
        public RequestAdmisionExclusionBehaviour(Agent myAgent, ACLMessage requestMsg) {
            super(myAgent, requestMsg);
            requestMsg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        }

        protected void handleAgree(ACLMessage msg) {
            System.out.println("  /*/ Requisitos superados. Esperando que se complete la operacion...");
        }

        protected void handleInform(ACLMessage msg) {
            try {
                Done d = (Done) myAgent.getContentManager().extractContent(msg);
                Action a = (Action) d.getAction();
                Persona p = null;
                if (a.getAction() instanceof Admitir) {
                    Admitir ad = (Admitir) a.getAction();
                    p = ad.getPersona();
                    System.out.println("  /*/ Admision completada con exito. " + p.getNombre() + "!!");
                } else {
                    if (a.getAction() instanceof Excluir) {
                        Excluir ex = (Excluir) a.getAction();
                        p = ex.getPersona();
                        System.out.println("  /*/ Exclusion completada con exito.  " + p.getNombre() + "!!");
                    }
                }
            } catch (Exception fe) {
                System.out.println(myAgent.getName() + ": Error handling the engagement action.");
                System.out.println(fe.getMessage().toString());
            }
        }

        protected void handleFailure(ACLMessage msg) {
            try {
                ErrorAdmision pred = (ErrorAdmision) myAgent.getContentManager().extractContent(msg);
                Motivo m = pred.getMOTIVO();
                System.out.println("  /*/ Admision fallida por el siguiente motivo : " + m.getMotivo());
            } catch (Codec.CodecException fe) {
                System.err.println("FIPAException reading failure reason: " + fe.getMessage());
            } catch (OntologyException oe) {
                System.err.println("OntologyException reading failure reason: " + oe.getMessage());
            }
        }

        protected void handleRefuse(ACLMessage msg) {
            System.out.println("  /*/ Admision rechazada");
            //Obtener el motivo del rechazo y comunicarselo al usuario
            try {
                AbsContentElementList list = (AbsContentElementList) myAgent.getContentManager().extractAbsContent(msg);
                AbsPredicate absPred = (AbsPredicate) list.get(1);
                System.out.println("  /*/ El motivo es: " + absPred.getTypeName());
            } catch (Codec.CodecException fe) {
                System.err.println("FIPAException reading refusal reason: " + fe.getMessage());
            } catch (OntologyException oe) {
                System.err.println("OntologyException reading refusal reason: " + oe.getMessage());
            }
        }
    }

    // VARIABLES LOCALES DEL AGENTE
    AID universidad; // AID del agente al que se enviaran las peticiones de admision de la universidad

    // AGENT SETUP
    protected void setup() {

        // Registrar el codec para el lenguaje SL0
        
        getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);

        // Registrar la ontologia usada por esta aplicacion
        getContentManager().registerOntology(UniversidadOntology.getInstance());

        //Obtener del usuario el nombre del agente al que se enviaran
        //las peticiones de admision
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("  Introduce el nombre de la universidad de la que soy secretario --> ");
            String name = buff.readLine();
            universidad = new AID(name, AID.ISLOCALNAME);

        } catch (IOException ioe) {
            System.err.println("Error I/O: " + ioe.getMessage());
        }

        //Crear y añadir el comportamiento principal de este agente
        addBehaviour(new HandleAdmisionExclusionBehaviour(this));
    }
}
