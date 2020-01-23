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
import jade.domain.*;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import java.io.*;
import ontologias.Hospital.*;
import ontologias.Hospital.Concept.*;
import ontologias.Hospital.Predicate.*;
import ontologias.Hospital.Accion.*;

/**
 *
 * @author Aaron Jaramillo
 */
public class Emisor extends Agent {

    private Codec codec = new SLCodec();
    private Ontology ontologia = HospitalOntology.getInstance();

    public void setup() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("emisor");
        sd.setName(getName());
        sd.setOwnership("ARNOIA");
        dfd.setName(getAID());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.err.println(getLocalName() + " registration with DF unsucceeded. Reason: " + e.getMessage());
            doDelete();
        }

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);

        EmisorComportamiento Enviar = new EmisorComportamiento(this);
        addBehaviour(Enviar);
    }

    public class EmisorComportamiento extends SimpleBehaviour {

        private boolean fin = false;

        public EmisorComportamiento(Agent a) {
            super(a);
        }

        public void action() {

            try {
                //Buffer
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                String contenido = "";
                int eleccion = 0;

                //Mensaje de inicio
                do {
                    try {
                        System.out.println("Bienvenido al Call Center");
                        System.out.println("Que necesita:");
                        System.out.println("1. Desea realizar una consulta privada");
                        System.out.println("2. Desea llamar a una ambulancia");

                        contenido = buff.readLine();
                        eleccion = Integer.parseInt(contenido);
                    } catch (Exception e) {
                        contenido = buff.readLine();
                        eleccion = Integer.parseInt(contenido);
                    }
                } while ((eleccion != 1) && ((eleccion != 2)));

                System.out.println("Su eleccion fue eleccion es " + eleccion);

                //Establecer mensaje
                System.out.println(getLocalName() + " preparando para enviar mensaje");
                AID id = new AID();
                id.setLocalName("receptor");

                ACLMessage msm = new ACLMessage(ACLMessage.INFORM);
                msm.setSender(getAID());
                msm.addReceiver(id);
                msm.setLanguage(codec.getName());
                msm.setOntology(ontologia.getName());

                if (eleccion == 1) {//comienzo de la condicion 1
                    if (generatRandomPositiveNegitiveValue(2, 1) == 1) {
                        Paciente pac = new Paciente();
                        Sintoma sint = new Sintoma();

                        System.out.println("\n****************************************");

                        System.out.println("Ingrese su nombre");
                        contenido = buff.readLine();
                        pac.setNombre(contenido);

                        System.out.println("Ingrese su apellido");
                        contenido = buff.readLine();
                        pac.setApellido(contenido);

                        try {
                            do {
                                System.out.println("Ingrese su año de nacimiento");
                                contenido = buff.readLine();
                                pac.setNacimiento(Integer.parseInt(contenido));
                            } while (Integer.parseInt(contenido) > 2020 || Integer.parseInt(contenido) < 1950);
                        } catch (Exception e) {
                            do {
                                System.out.println("Ingrese su año de nacimiento");
                                contenido = buff.readLine();
                                pac.setNacimiento(Integer.parseInt(contenido));
                            } while (Integer.parseInt(contenido) > 2020 || Integer.parseInt(contenido) < 1950);
                        }

                        System.out.println("Ingrese su domicilio");
                        contenido = buff.readLine();
                        pac.setDomicilio(contenido);

                        do {
                            System.out.println("Ingrese su numero de cedula (10 digitos)");
                            contenido = buff.readLine();
                        } while (contenido.length() != 10);
                        pac.setCedula(contenido);

                        do {
                            System.out.println("Elija su sintoma");
                            System.out.println("1. Dolor de barriga");
                            System.out.println("2. Dolor de cabeza");
                            System.out.println("3. Mareo");
                            contenido = buff.readLine();
                            eleccion = Integer.parseInt(contenido);
                        } while (eleccion < 1 && eleccion > 3);

                        switch (eleccion) {
                            case 1:
                                sint.setIden(1);
                                sint.setSintoma("Dolor de barriga");
                                break;
                            case 2:
                                sint.setIden(2);
                                sint.setSintoma("Dolor de cabeza");
                                break;
                            case 3:
                                sint.setIden(3);
                                sint.setSintoma("Mareo");
                                break;
                        }

                        PacienteAtendido pante = new PacienteAtendido();
                        pante.setPaciente(pac);
                        pante.setSintoma(sint);

                        getContentManager().fillContent(msm, pante);
                        send(msm);
                    } else {
                        Motivo mot = new Motivo();
                        mot.setMotivo("El servicio se encuentran sin operatividad.\n"
                                + "Intente contactarse con otra entidad publica.");
                        PacienteNoAtendido pano = new PacienteNoAtendido();
                        pano.setMotivo(mot);
                        getContentManager().fillContent(msm, pano);
                        send(msm);
                    }

                }//fin condicion 1
                else {
                    //System.out.println("No esta funcionado");
                    System.out.println("Es una emegercia");
                    if (generatRandomPositiveNegitiveValue(2, 1) == 1) {
                        TiempoLlegada tll = new TiempoLlegada();
                        tll.setTiempo(generatRandomPositiveNegitiveValue(20, 5));
                        Disponible disp = new Disponible();
                        disp.setTiempo(tll);

                        getContentManager().fillContent(msm, disp);
                        send(msm);
                    } else {
                        Motivo mot = new Motivo();
                        mot.setMotivo("Las ambulancias se encuentran sin operatividad.\n"
                                + "Intente contactarse con otra entidad publica.");
                        NoDisponible ndis = new NoDisponible();
                        ndis.setMotivo(mot);
                        getContentManager().fillContent(msm, ndis);
                        send(msm);
                    }//fin 

                }

            } catch (java.io.IOException io) {
                System.out.println(io);
            } catch (jade.content.lang.Codec.CodecException ce) {
                System.out.println(ce);
            } catch (jade.content.onto.OntologyException oe) {
                System.out.println(oe);
            } catch (Exception e) {
                System.out.println("\n\n... Terminando ...");
                fin = true;
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
