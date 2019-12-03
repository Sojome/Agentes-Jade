/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes_jade;

import jade.core.Agent;
import jade.core.behaviours.*;

/**
 *
 * @author user
 */

public class Comportamiento_Ciclico_Periodico_Tarea extends Agent {

    long tini;

    protected void setup() {
        //-gui -port 1098 tonto1:agentes_jade.Comportamiento_Ciclico_Periodico_Tarea
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
            System.out.println("reseteo!");
        }

        protected void onTick() {
            long tfin = System.currentTimeMillis() - tini;
            int nticks = getTickCount(); // obtenemos el numero de ticks desde el último reset
            minticks++;
            if (nticks == 5) {
                System.out.println("[" + tfin + "ms.] tick = " + nticks + ", mitick =  " + minticks + " y reseteo");
                reset();
            } else {
                System.out.println("[" + tfin + "ms.] tick = " + nticks + ", mitick =  " + minticks);
            }
        }
    }
}
