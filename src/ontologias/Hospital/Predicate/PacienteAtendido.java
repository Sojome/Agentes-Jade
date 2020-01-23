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
public class PacienteAtendido implements Predicate{
    private Paciente paciente;
    private Sintoma sintoma;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }
    
}
