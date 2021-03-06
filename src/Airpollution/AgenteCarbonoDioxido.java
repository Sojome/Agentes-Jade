/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Airpollution;

import Examen.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Aaron Jaramillo
 */
public class AgenteCarbonoDioxido extends Agent {

    private long tini;

    protected void setup() {
        //-gui -port 1098 hume:Examen.AgenteHumedad
        tini = System.currentTimeMillis();
        addBehaviour(new miTicker(this, 1000));
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
            String mensaje = "";
            int valor = generatRandomPositiveNegitiveValue(2000, 0);
            if (valor >= 0 && valor < 800) {
                mensaje="bajo";
            } else if (valor >= 800 && valor < 1600) {
                mensaje="normal";
            } else if(valor >= 1200){
                mensaje="alto";
            }
           //System.out.println(valor+"ppm es "+mensaje);
            
            mensaje = Integer.toString(valor);
            
            //------------------ENVIAR
            //System.out.println(getLocalName()+" preparando para enviar mensaje");
            AID id = new AID();
            id.setLocalName("air");
            
            ACLMessage msm = new ACLMessage(ACLMessage.INFORM);
            msm.setSender(getAID());
            msm.setLanguage("Español");
            msm.addReceiver(id);
            msm.setContent(mensaje);
            send(msm);
            //System.out.println("Enviando mensaje a "+id.getLocalName());            
        }
    }

    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }

}
