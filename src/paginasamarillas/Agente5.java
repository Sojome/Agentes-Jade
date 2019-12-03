/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paginasamarillas;

import jade.core.Agent;

import jade.core.behaviours.Behaviour;

import jade.domain.DFService;

import jade.domain.FIPAAgentManagement.*;

import java.util.Iterator;

/**
 *
 * @author Aaron Jaramillo
 */
//-gui -port 1098 o1:paginasamarillas.Agente1;o2:paginasamarillas.Agente2;o3:paginasamarillas.Agente3;buscador:paginasamarillas.Agente5
public class Agente5 extends Agent {

    protected void setup() {

        addBehaviour(new BuscaComportamiento());

    }

    protected void takeDown() {

        System.out.println("****Agente finalizado****");

    }

    private class BuscaComportamiento extends Behaviour {

        public void action() {

            //Tiempo de espera
            block(9000000);
            ServiceDescription servicio = new ServiceDescription();

            servicio.setType("type1");

            //servicio.setName("N1");
            // Plantilla de descripción que busca el agente
            DFAgentDescription descripcion = new DFAgentDescription();

            descripcion.addLanguages("Castellano");

            // Servicio que busca el agente
            descripcion.addServices(servicio);

            try {

                // Todas las descripciones que encajan con la plantilla proporcionada en el DF
                DFAgentDescription[] resultados = DFService.search(myAgent, descripcion);

                if (resultados.length == 0) {
                    System.out.println("Ningun agente ofrece el servicio deseado");
                }

                for (int i = 0; i < resultados.length; ++i) {

                    System.out.println("El agente " + resultados[i].getName().getLocalName() + " ofrece el servicio buscado");

                    Iterator servicios = resultados[i].getAllServices();

                    int j = 1;

                    /*while(servicios.hasNext())
                     {

                     //     servicio = (ServiceDescription)servicios.next();
                     //   System.out.println(servicio.getName());
                     //  System.out.println();
                     //  j++;
                     }*/
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        public boolean done() {

            return true;

        }

    }

}
