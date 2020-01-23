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
        
        AgentController AgenteTonto = null;
        AgentController AgenteCyclicBehavior = null;
        AgentController AgenteFsmBehavior = null;
        AgentController AgenteOneShotBehavior = null;
        AgentController AgenteParallelBehavior = null;
        AgentController AgenteSequentialBehavior = null;
        AgentController Comportamiento_Ciclico_Periodico_Tarea = null;
        

        try {

            AgenteTonto = mainContainer.createNewAgent("tonto", "agentes_jade.AgenteTonto", new Object[] {"argumento1","argumento2","888"});
            //AgenteTonto.start();
            
            AgenteCyclicBehavior = mainContainer.createNewAgent("tonto1", "agentes_jade.AgenteCyclicBehavior", new Object[0]);
            //AgenteCyclicBehavior.start();            
            
            AgenteFsmBehavior = mainContainer.createNewAgent("tonto2", "agentes_jade.AgenteFsmBehavior", new Object[0]);
            AgenteFsmBehavior.start();
            
            AgenteOneShotBehavior = mainContainer.createNewAgent("tonto3", "agentes_jade.AgenteOneShotBehavior", new Object[0]);
            //AgenteOneShotBehavior.start();
            
            AgenteParallelBehavior = mainContainer.createNewAgent("tonto4", "agentes_jade.AgenteParallelBehavior", new Object[0]);
            //AgenteParallelBehavior.start();
            
            AgenteSequentialBehavior = mainContainer.createNewAgent("tonto5", "agentes_jade.AgenteSequentialBehavior", new Object[0]);
            //AgenteSequentialBehavior.start();
            
            Comportamiento_Ciclico_Periodico_Tarea = mainContainer.createNewAgent("tonto6", "agentes_jade.Comportamiento_Ciclico_Periodico_Tarea", new Object[0]);
            //Comportamiento_Ciclico_Periodico_Tarea.start();
            
        } catch (StaleProxyException e) {

            e.printStackTrace();

        }

    }

}
