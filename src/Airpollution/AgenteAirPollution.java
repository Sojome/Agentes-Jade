/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Airpollution;

import Examen.*;
import agentes_jade.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Aaron Jaramillo
 */
public class AgenteAirPollution extends Agent {

    long tini;
    int[] comp = new int[4];

    protected void setup() {
        tini = System.currentTimeMillis();
        addBehaviour(new miTicker(this, 1000));
    }

    private class miTicker extends TickerBehaviour {

        int minticks;

        public miTicker(Agent a, long intervalo) {
            super(a, intervalo);
            minticks = 0;
        }

        public void reset() {
            super.reset();
            //minticks = 0;
            //System.out.println("reseteo!");
        }

        protected void onTick() {
            long tfin = System.currentTimeMillis() - tini;
            int nticks = getTickCount(); // obtenemos el numero de ticks desde el último reset
            minticks++;

            String mensaje = "No estoy autorizado para contestar";
            String estado = "";
            block();
            ACLMessage msm = receive();
            //System.out.println(getLocalName() + " esta esperando recibir un mensaje");
            if (msm != null) {
                //System.out.println("LLego mensaje de "+msm.getSender().getLocalName());
                if (msm.getSender().getLocalName().equals("temp")) {
                    comp[0] = Integer.parseInt(msm.getContent());
                }
                if (msm.getSender().getLocalName().equals("hume")) {
                    comp[1] = Integer.parseInt(msm.getContent());
                }
                if (msm.getSender().getLocalName().equals("dio")) {
                    comp[2] = Integer.parseInt(msm.getContent());
                }
                if (msm.getSender().getLocalName().equals("mono")) {
                    comp[3] = Integer.parseInt(msm.getContent());
                }
                //------1
                if ((comp[0] >= 5 && comp[0] < 15) && (comp[1] >= 30 && comp[1] < 50) && (comp[2] >= 0 && comp[2] < 800) && (comp[3] >= 0 && comp[3] < 4)) {
                    estado = "Zero";
                } //------2
                else if ((comp[0] >= 15 && comp[0] < 25) && (comp[1] >= 30 && comp[1] < 50) && (comp[2] >= 1200) && (comp[3] >= 20)) {
                    estado = "VH";
                } //------3
                else if ((comp[0] >= 25 && comp[0] < 40) && (comp[1] >= 50) && (comp[2] >= 800 && comp[2] < 1600) && (comp[3] >= 4 && comp[3] < 12)) {
                    estado = "H";
                } //------4
                else if ((comp[0] >= 35) && (comp[1] >= 50) && (comp[2] >= 1200) && (comp[3] >= 4 && comp[3] < 12)) {
                    estado = "VH";
                } //------5
                else if ((comp[0] >= 35) && (comp[1] >= 50) && (comp[2] >= 800 && comp[2] < 1600) && (comp[3] >= 20)) {
                    estado = "VH";
                } //------6
                else if ((comp[0] >= 35) && (comp[1] >= 50) && (comp[2] >= 1200) && (comp[3] >= 8 && comp[3] < 16)) {
                    estado = "VH";
                } //------7
                else if ((comp[0] >= 35) && (comp[1] >= 50) && (comp[2] >= 1200) && (comp[3] >= 12 && comp[3] <= 20)) {
                    estado = "VH";
                } //------8
                else if ((comp[0] >= 35) && (comp[1] >= 50) && (comp[2] >= 1200) && (comp[3] >= 20)) {
                    estado = "VH";
                } //------9
                else if ((comp[0] >= 25 && comp[0] < 40) && (comp[1] >= 40 && comp[1] <= 60) && (comp[2] >= 800 && comp[2] < 1600) && (comp[3] >= 0 && comp[3] < 4)) {
                    estado = "Zero";
                } //------10
                else if ((comp[0] >= 25 && comp[0] < 40) && (comp[1] >= 50) && (comp[2] >= 1200) && (comp[3] >= 8 && comp[3] < 16)) {
                    estado = "H";
                }
                
                //if (nticks == 5 && !estado.equals("")) {
                if (nticks == 5) {
                    System.out.println("----------------------------------------");
                    System.out.println("Tempreratura: " + comp[0] + "   "
                            + "Humedad: " + comp[1] + "   "
                            + "Dioxido: " + comp[2] + "   "
                            + "Monoxido: " + comp[3]);
                    System.out.println("El estado del AIR POLLUTION es: " + estado);
                    reset();
                    estado = "";
                }/*else{
                    System.out.println(nticks);
                }*/
            }
        }
    }
}
