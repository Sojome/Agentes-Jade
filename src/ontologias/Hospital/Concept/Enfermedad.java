/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Concept;

import jade.content.Concept;

/**
 *
 * @author Aaron Jaramillo
 */
public class Enfermedad implements Concept{
    private String enfermedad;    
    private String cura;

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getCura() {
        return cura;
    }

    public void setCura(String cura) {
        this.cura = cura;
    }
    
}
