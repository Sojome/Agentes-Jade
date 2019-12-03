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
public class NoDisponible implements Predicate{
    
    private Motivo motivo;

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }
    
    
}
