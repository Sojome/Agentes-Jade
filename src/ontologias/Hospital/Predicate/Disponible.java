/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Hospital.Predicate;

import ontologias.Hospital.Concept.TiempoLlegada;
import jade.content.Predicate;
 
/**
 *
 * @author MI PC
 */
public class Disponible implements Predicate  {
    private TiempoLlegada tiempo;

    public TiempoLlegada getTiempo() {
        return tiempo;
    }

    public void setTiempo(TiempoLlegada tiempo) {
        this.tiempo = tiempo;
    }
    
}
