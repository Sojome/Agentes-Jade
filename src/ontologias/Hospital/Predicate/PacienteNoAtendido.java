/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Predicate;
import jade.content.Predicate;
import ontologias.Hospital.Concept.*;
/**
 *
 * @author MI PC
 */
public class PacienteNoAtendido implements Predicate{
    private Motivo motivo;

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }
        
}
