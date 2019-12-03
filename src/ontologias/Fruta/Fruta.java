/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologias.Fruta;

import jade.content.Concept;

/**
 *
 * @author Aaron Jaramillo
 */
public class Fruta implements Concept {

    private String nombre;
    private double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    /*public int getPrecio() {
        return precio;
    }

    public void setPrecio(int p) {
        precio = p;
    }*/

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        this.precio = p;
    }
    
    
}
