/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Policia;

import jade.content.Concept;

/**
 *
 * @author Aaron Jaramillo
 */
public class Ladron implements Concept{
    private Descripcion descripcion;

    public Descripcion getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Descripcion descripcion) {
        this.descripcion = descripcion;
    }
    
}
