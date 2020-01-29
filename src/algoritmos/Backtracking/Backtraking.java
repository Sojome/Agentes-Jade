/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.Backtracking;

/**
 *
 * @author Fernando Guerra
 */
public class Backtraking {

    // Declaracion de variables privadas
    private int counter = 0;
    private final int Valor_Maxiom_de_solucion = 1000;
    private int mejor_solucion = Valor_Maxiom_de_solucion;
    private char[][] entrada;
    private char[][] laberinto;

// constructor // laberinto es una matriz
    public Backtraking(char[][] laberinto, int x, int y, char objeto) {
        this.laberinto = laberinto;
        this.laberinto[x][y] = objeto;
        this.entrada = null;
    }

    // 
    private void Copia_Laberinto() {
        entrada = new char[this.laberinto.length][this.laberinto[0].length];
        for (int x = 0; x < this.laberinto.length; x++) {
            for (int y = 0; y < this.laberinto[0].length; y++) {
                entrada[x][y] = laberinto[x][y];
            }
        }
    }

    public void Salida(int x, int y) {
        mejor_solucion = Valor_Maxiom_de_solucion;

        if (Algotitmo_Resolucion(x, y, 0) != Valor_Maxiom_de_solucion) {
            entrada[x][y] = 'S';
        }
    }

    public int Algotitmo_Resolucion(int x, int y, int count) {
        this.counter++;
        // Esta contando cuantas veces tardo en llegar del inicial al final para compararlo
        if (laberinto[x][y] == 'X') {
            mejor_solucion = count;
            this.Copia_Laberinto();
            return count;
        }
        if (laberinto[x][y] == '#' || laberinto[x][y] == '*') {
            return Valor_Maxiom_de_solucion;
        }

        if (count == mejor_solucion) {
            return Valor_Maxiom_de_solucion;
        }

        // imprime el *
        laberinto[x][y] = '*';

        //imprime cada vez que sale el laberinto
        System.out.println(this.imprimir_matriz());

        int result = Valor_Maxiom_de_solucion;

        int new_result = Valor_Maxiom_de_solucion;

        new_result = Algotitmo_Resolucion(x, (y >= this.laberinto[0].length ? y + 1 : y), count + 1);
        if (new_result < result) {
            result = new_result;
        }

        new_result = Algotitmo_Resolucion(x - 1, y, count + 1);
        if (new_result < result) {
            result = new_result;
        }

        new_result = Algotitmo_Resolucion(x, y - 1, count + 1);
        if (new_result < result) {
            result = new_result;
        }

        new_result = Algotitmo_Resolucion(x + 1, y, count + 1);
        if (new_result < result) {
            result = new_result;
        }
        laberinto[x][y] = ' ';
        if (result < Valor_Maxiom_de_solucion) {
            return result;
        }
        return Valor_Maxiom_de_solucion;
    }

    // funcion que imprime los laberintos varias veces
    private String imprimir_matriz() {
        System.out.println("---------------------------------------------");
        String output = "";
        for (int x = 0; x < this.laberinto.length; x++) {
            for (int y = 0; y < this.laberinto[x].length; y++) {
                output += laberinto[x][y] + " ";
            }
            output += "\n";
        }
        return output;
    }

    // imprime la solucion
    public String imprimir_matrizSolution() {
        System.out.println("---------------------------------------------");
        if (this.entrada == null) {
            return "No hay solucion!";
        }
        String output = "";
        for (int x = 0; x < this.laberinto.length; x++) {
            for (int y = 0; y < this.laberinto[0].length; y++) {
                output += this.entrada[x][y] + " ";
            }
            output += "\n";
        }
        return output;
    }
}