/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.Runtime;

import jade.core.Profile;

import jade.core.ProfileImpl;

import jade.wrapper.*;

/**
 *
 * @author user
 */
public class CrearcionAgentes {

    public static void main(String args[]) throws StaleProxyException {

        /*    Properties pp = new Properties();

         pp.setProperty(Profile.MAIN, Boolean.FALSE.toString());

         pp.setProperty(Profile.MAIN_HOST, "127.0.0.1");

         pp.setProperty(Profile.MAIN_PORT, Integer.toString(1099));

         Profile p = new ProfileImpl(pp);

         AgentContainer ac = jade.core.Runtime.instance().createAgentContainer(p);

    

         ac.acceptNewAgent("JumpingAgent", new AgenteBasico()).start();

         */
        Runtime rt = Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile profile = new ProfileImpl("172.29.15.68", 1099, "main");

        AgentContainer mainContainer = jade.core.Runtime.instance().createMainContainer(profile);

        AgentController rma = null;

        try {

            rma = mainContainer.createNewAgent("tonto", "agentes_jade.AgenteTonto", new Object[0]);

            rma.start();

        } catch (StaleProxyException e) {

            e.printStackTrace();

        }

    }

}
