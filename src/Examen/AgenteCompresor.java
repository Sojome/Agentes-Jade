/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import agentes_jade.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author user
 */
//-gui -port 1098 compre:Examen.AgenteCompresor;hume:Examen.AgenteHumedad;fusi:Examen.AgenteFusion;temp1:Examen.AgenteTemperatura1;temp2:Examen.AgenteTemperatura1
public class AgenteCompresor extends Agent {

    long tini;
    int [] comp = new int[2];

    protected void setup() {
        tini = System.currentTimeMillis();
        addBehaviour(new miTicker(this, 4000));
    }

    private class miTicker extends TickerBehaviour {

        int minticks;

        public miTicker(Agent a, long intervalo) {
            super(a, intervalo);
            minticks = 0;
        }

        protected void onTick() {
            long tfin = System.currentTimeMillis() - tini;
            int nticks = getTickCount(); // obtenemos el numero de ticks desde el último reset
            minticks++;

            String mensaje = "No estoy autorizado para contestar";
            String estado="";
            block();
            ACLMessage msm = receive();
            System.out.println(getLocalName() + " esta esperando recibir un mensaje");
            if (msm != null) {
                System.out.println("LLego mensaje de "+msm.getSender().getLocalName());
                if (msm.getSender().getLocalName().equals("fusi")) {
                    comp[0] = Integer.parseInt(msm.getContent());
                }
                if (msm.getSender().getLocalName().equals("hume")) {
                    comp[1] = Integer.parseInt(msm.getContent());
                }
                
                //Temperatura muy bajo
                if ( (comp[0] >= 0 && comp[0] < 10) && (comp[1] >= 0 && comp[1] < 20) ) {
                    estado = "apagado";
                } else if ( (comp[0] >= 0 && comp[0] < 10) && (comp[1] >= 20 && comp[1] < 40) ) {
                    estado = "apagado";
                } else if ( (comp[0] >= 0 && comp[0] < 10) && (comp[1] >= 40 && comp[1] < 70) ) {
                    estado = "apagado";
                } else if ( (comp[0] >= 0 && comp[0] < 10) && (comp[1] >= 70 && comp[1] < 100) ) {
                    estado = "bajo";
                }
                //Temperatura bajo
                else if ( (comp[0] >= 10 && comp[0] < 25) && (comp[1] >= 10 && comp[1] < 20) ) {
                    estado = "apagado";
                } else if ( (comp[0] >= 10 && comp[0] < 25) && (comp[1] >= 20 && comp[1] < 40) ) {
                    estado = "apagado";
                } else if ( (comp[0] >= 10 && comp[0] < 25) && (comp[1] >= 40 && comp[1] < 70) ) {
                    estado = "bajo";
                } else if ( (comp[0] >= 10 && comp[0] < 25) && (comp[1] >= 70 && comp[1] < 250) ) {
                    estado = "medio";
                }
                //Temperatura alto
                else if ( (comp[0] >= 25 && comp[0] < 35) && (comp[1] >= 25 && comp[1] < 20) ) {
                    estado = "bajo";
                } else if ( (comp[0] >= 25 && comp[0] < 35) && (comp[1] >= 20 && comp[1] < 40) ) {
                    estado = "medio";
                } else if ( (comp[0] >= 25 && comp[0] < 35) && (comp[1] >= 40 && comp[1] < 70) ) {
                    estado = "rapido";
                } else if ( (comp[0] >= 25 && comp[0] < 35) && (comp[1] >= 70 && comp[1] < 350) ) {
                    estado = "rapido";
                }
                //Temperatura muy alto
                else if ( (comp[0] >= 35 && comp[0] < 45) && (comp[1] >= 35 && comp[1] < 20) ) {
                    estado = "medio";
                } else if ( (comp[0] >= 35 && comp[0] < 45) && (comp[1] >= 20 && comp[1] < 40) ) {
                    estado = "rapido";
                } else if ( (comp[0] >= 35 && comp[0] < 45) && (comp[1] >= 40 && comp[1] < 70) ) {
                    estado = "rapido";
                } else if ( (comp[0] >= 35 && comp[0] < 45) && (comp[1] >= 70 && comp[1] < 450) ) {
                    estado = "rapido";
                }
                
                System.out.println("El estado del compresor esta "+estado);
                //System.out.println("SI LLEGAN ----"+comp[0]+"-------"+comp[1]);
            }
        }
    }
}
