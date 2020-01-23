/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paginasamarillas;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;

/**
 *
 * @author Aaron Jaramillo
 */
public class Agente2 extends Agent {

    public void setup() {
        addBehaviour(new Publica2(this));
    }

    protected void takeDown() {
        System.out.println("Ejecucion finalizada");
    }

    private class Publica2 extends OneShotBehaviour {

        private Agent este;

        public Publica2(Agent este) {
            this.este = este;
        }

        public void action() {
            //Se crea la descripcion
            DFAgentDescription dex = new DFAgentDescription();
            dex.setName(getAID());
            dex.addLanguages("Castellano");

            //Se crea el servicio
            ServiceDescription s1 = new ServiceDescription();
            s1.setType("type1");
            s1.setName("serviceD");
            //Se añade el servicio a la descripcion
            dex.addServices(s1);

            ServiceDescription s2 = new ServiceDescription();
            s2.setType("type2");
            s2.setName("serviceE");
            //Se añade el servicio a la descripcion
            dex.addServices(s2);
            /*
            ServiceDescription s3 = new ServiceDescription();
            s3.setType("type3");
            s3.setName("service3");
            //Se añade el servicio a la descripcion
            dex.addServices(s3);*/

            try {
                DFService.register(este, dex);
            } catch (Exception e) {
            }

            System.out.println(getAID().getLocalName() + " ha registrado los servicios con exito");
        }
    }
}
