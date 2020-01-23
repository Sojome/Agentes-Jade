/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.universidadOntology;

import jade.content.Predicate;

/**
 *
 * @author Maximiliano
 */
public class ErrorAdmision implements Predicate{
    
    private Motivo MOTIVO;

    public Motivo getMOTIVO() {
        return MOTIVO;
    }

    public void setMOTIVO(Motivo MOTIVO) {
        this.MOTIVO = MOTIVO;
    }
}
