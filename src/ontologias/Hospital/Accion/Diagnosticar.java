/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Accion;
import jade.content.AgentAction;
import ontologias.Hospital.Concept.*;
/**
 *
 * @author MI PC
 */
public class Diagnosticar implements AgentAction  {
    private Paciente paciente;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
        
}
