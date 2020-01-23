/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.Agent;

/**
 *
 * @author Aaron Jaramillo
 */
public class AgenteTonto extends Agent {

    public void setup() {
        //jade.Boot
        //-gui -port 1098 tonto1:agentes_jade.AgenteTonto(argumento1,argumento2,888)
        //-gui -port 1098 tonto1:agentes_jade.AgenteTonto(argumento1,argumento2,888);tonto2:agentes_jade.AgenteTonto(mery,anthony,aaron,jose)

        System.out.println("Soy el agente " + getName());
        System.out.println("Soy el agente " + getLocalName());
        System.out.println("Soy el agente " + getAID());

        System.out.println("Mis argumentos son: ");
        Object[] args = getArguments();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println("--> " + args[i]);
            }
        }
    }
}
