/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Accion;
import jade.content.AgentAction;
import ontologias.Hospital.Concept.Ambulancia;
import ontologias.Hospital.Predicate.*;
/**
 *
 * @author MI PC
 */
public class EmergencyCall implements AgentAction  {
    private Ambulancia ambulancia;

    public Ambulancia getAmbulancia() {
        return ambulancia;
    }

    public void setAmbulancia(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }
        
}
