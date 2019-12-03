/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author user
 */
public class MyOneShotBehavior extends OneShotBehaviour {

    public void action() {
        System.out.println("Ejecución del comportamiento OneShotBehavior");
        //Si comentamos la linea de abajo matamos al agente
        //myAgent.doDelete();
    }
}
