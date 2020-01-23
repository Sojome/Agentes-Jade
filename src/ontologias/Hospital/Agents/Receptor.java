/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Agents;

import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.core.*;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.*;
import ontologias.Hospital.*;
import ontologias.Hospital.Concept.*;
import ontologias.Hospital.Predicate.*;
import ontologias.Hospital.Accion.*;

/**
 *
 * @author Aaron Jaramillo
 */
public class Receptor extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontologia = HospitalOntology.getInstance();

    public void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);

        ReceptorComportamiento recibir = new ReceptorComportamiento(this);
        addBehaviour(recibir);
    }

    public class ReceptorComportamiento extends SimpleBehaviour {

        private boolean fin = false;

        public ReceptorComportamiento(Agent a) {
            super(a);
        }

        public void action() {

            System.out.println("\nConectandose ....");

            MessageTemplate mt = MessageTemplate.and(
                    MessageTemplate.MatchLanguage(codec.getName()),
                    MessageTemplate.MatchOntology(ontologia.getName()));
            ACLMessage msg = blockingReceive(mt);

            try {
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.NOT_UNDERSTOOD) {
                        System.out.println("Mensaje NOT UNDERSTOOD recibido");
                    } else {
                        if (msg.getPerformative() == ACLMessage.INFORM) {

                            ContentElement ce = getContentManager().extractContent(msg);

                            if (ce instanceof PacienteAtendido) {
                                PacienteAtendido pante = (PacienteAtendido) ce;
                                Paciente pac = pante.getPaciente();
                                Sintoma sint = pante.getSintoma();                                
                                Enfermedad enf = new Enfermedad();
                                //Enfermedad
                                switch (sint.getIden()) {
                                    case 1:
                                        enf.setEnfermedad("Dolor estomacal");
                                        enf.setCura("Consumir una manzanilla o un té de jengibre");
                                        break;
                                    case 2:
                                        enf.setEnfermedad("Estres");
                                        enf.setCura("Descansa y tómatelo con calma");                                        
                                        break;
                                    case 3:
                                        enf.setEnfermedad("Mareo");
                                        enf.setCura("Tomar infusiones de jengibre, manzanilla o anís antes de salir, ayuda a proteger las paredes del estómago y previene la sensación de náuseas.");
                                        break;
                                }
                                Recetar rept = new Recetar();
                                rept.setEnfermedad(enf);
                                System.out.println("\n************************************************");
                                System.out.println("Mensaje recibido de " + getAID().getLocalName());
                                System.out.println(
                                        "El paciente "+pac.getNombre()+" "+pac.getApellido()+" con el ci "+
                                        pac.getCedula()+" actualemnete tiene "+
                                        (2019-pac.getNacimiento())+" años de edad\n y vive en "+
                                        pac.getDomicilio()+".\nTiene una enfermedad llamada "+
                                        enf.getEnfermedad()+ " y para su tratamiento es: \n--"+
                                                enf.getCura()
                                        );
                                System.out.println("************************************************\n");
                                Diagnosticar diagno = new Diagnosticar();
                                diagno.setPaciente(pac);

                                ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                msg2.setLanguage(codec.getName());
                                msg2.setOntology(ontologia.getName());
                                msg2.setSender(getAID());
                                msg2.addReceiver(msg.getSender());
                                getContentManager().fillContent(msg2, diagno);
                                send(msg2);
                            }//fin de la condicion
                            else if (ce instanceof PacienteNoAtendido) {
                                PacienteNoAtendido pano = (PacienteNoAtendido) ce;
                                Motivo mot = pano.getMotivo();
                                System.out.println("\n************************************************");
                                System.out.println("Mensaje recibido de " + getAID().getLocalName());
                                System.out.println(mot.getMotivo());
                                System.out.println("************************************************\n");                                
                            }
                            else if (ce instanceof NoDisponible) {
                                NoDisponible ndis = (NoDisponible) ce;
                                Motivo mot = ndis.getMotivo();
                                System.out.println("\n************************************************");
                                System.out.println("Mensaje recibido de " + getAID().getLocalName());
                                System.out.println(mot.getMotivo());
                                System.out.println("************************************************\n");                                
                            }
                            else if (ce instanceof Disponible) {
                                Disponible disp = (Disponible) ce;
                                TiempoLlegada tll = disp.getTiempo();
                                
                                Ambulancia amb = new Ambulancia();
                                switch(generatRandomPositiveNegitiveValue(2, 1)){
                                    case 1:
                                        amb.setPlaca("SAJH3");
                                        amb.setConductor("Adam");
                                        break;
                                    case 2:
                                        amb.setPlaca("POEW5J");
                                        amb.setConductor("Mike");
                                        break;
                                    case 3:
                                        amb.setPlaca("5S5AJH");
                                        amb.setConductor("Juan");
                                        break;
                                }
                                EnvioAmbulancia enm = new EnvioAmbulancia();
                                enm.setAmbulancia(amb);
                                
                                System.out.println("\n************************************************");
                                System.out.println("Mensaje recibido de " + getAID().getLocalName());
                                System.out.println(
                                        "Ira la ambulancia con la placa "+amb.getPlaca()+
                                        " conducido por "+amb.getConductor()+" en un tiempo de "+tll.getTiempo()+" min."
                                        );
                                System.out.println("************************************************\n");
                                
                                EmergencyCall emer = new EmergencyCall();
                                emer.setAmbulancia(amb);
                                
                                ACLMessage msg2 = new ACLMessage(ACLMessage.REQUEST);
                                msg2.setLanguage(codec.getName());
                                msg2.setOntology(ontologia.getName());
                                msg2.setSender(getAID());
                                msg2.addReceiver(msg.getSender());
                                getContentManager().fillContent(msg2, emer);
                                send(msg2);
                            }
                        }
                    }
                }
            } catch (Exception e) {
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
