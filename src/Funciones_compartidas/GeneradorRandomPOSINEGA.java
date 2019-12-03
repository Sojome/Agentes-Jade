/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones_compartidas;

/**
 *
 * @author Aaron Jaramillo
 */
public class GeneradorRandomPOSINEGA {
    
    private int max;
    private int min;

    public GeneradorRandomPOSINEGA(int max, int min) {
        this.max = max;
        this.min = min;
    }

    public int valorrandom() {
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }
    
}
