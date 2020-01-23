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
public class EnvioAmbulancia implements Predicate{
    private Ambulancia ambulancia;

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }
        
}
