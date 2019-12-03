/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Policia;

import jade.content.Predicate;

/**
 *
 * @author Aaron Jaramillo
 */
public class LadronDetenido implements Predicate{
    private Ladron ladron;

    public Ladron getLadron() {
        return ladron;
    }

    public void setLadron(Ladron ladron) {
        this.ladron = ladron;
    }
    
    
}
