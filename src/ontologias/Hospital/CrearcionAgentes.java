/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital;

import Examen.*;
import agentes_jade.*;
import jade.core.Runtime;

import jade.core.Profile;

import jade.core.ProfileImpl;

import jade.wrapper.*;
import java.net.InetAddress;

/**
 *
 * @author Aaron Jaramillo
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
        //Buscar direccion en el ipconfig
        String IP_local = "";
        try {
            InetAddress direccion = InetAddress.getLocalHost();
            IP_local = direccion.getHostAddress();//ip como String
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Asignar IP hallada
        Profile profile = new ProfileImpl(IP_local, 1099, "main");

        AgentContainer mainContainer = jade.core.Runtime.instance().createMainContainer(profile);

        AgentController rma = null;
        AgentController rme = null;

        try {
            
            rma = mainContainer.createNewAgent("receptor", "ontologias.Hospital.Agents.Receptor", new Object[0]);
            
            rme = mainContainer.createNewAgent("emisor", "ontologias.Hospital.Agents.Emisor", new Object[0]);

            rma.start();
            rme.start();

        } catch (StaleProxyException e) {

            e.printStackTrace();

        }

    }

}
