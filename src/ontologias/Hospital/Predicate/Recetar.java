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
 * @author Aaron Jaramillo
 */
public class Recetar implements Predicate{
    private Enfermedad enfermedad;

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }
    
}
