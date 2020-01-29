/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.Backtracking;

import java.util.Scanner;

/**
 *
 * @author Aaron Jaramillo Mera
 */
public class LaberintoTarea {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        // se dibuja el laberinto por medio de # y se lo guarda en un array bidimensional
        //11 columnas -- 10 fila
        int fila = 10, columna = 11, pxI, pyI, pxF, pyF;
        boolean confirmado = false;
        char[][] laberinto = new char[fila][columna];

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if (i == 0
                        || i == (fila - 1)
                        || j == 0
                        || j == (columna - 1)) {
                    laberinto[i][j] = '#';
                    //laberinto[i][j] = Integer.toString(j).charAt(0);
                    //System.out.print("# ");
                } else if (((i == 3 || i == 7) && (j > 2 && j < 8))
                        || ((j == 3 || j == 7) && (i > 2 && i < 8))) {
                    laberinto[i][j] = ' ';
                    //System.out.print("  ");
                } else {
                    if (generatRandomPositiveNegitiveValue(1, 0) == 1) {
                        //System.out.print("# ");
                        laberinto[i][j] = '#';
                    } else {
                        //System.out.print("  ");
                        laberinto[i][j] = ' ';
                    }
                }
            }
        }

        System.out.println("    ---.:LABERINTO:.---");
        System.out.print(" ");
        for (int i = 0; i < columna; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");
        for (int i = 0; i < fila; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < columna; j++) {
                System.out.print(laberinto[i][j] + " ");
            }
            System.out.println("");
        }
        //Posicion inicial-------------------------------------
        System.out.println("Ingrese un punto de inicio:");
        do {
            //-----------------------------------------------------
            do {
                System.out.print("En fila: ");
                pxI = entrada.nextInt();
                if (pxI >= 0 || pxI < fila) {
                    break;
                } else {
                    System.out.println("Ingrese un numero entre 0 y " + (fila - 1));
                }
            } while (true);
            //-----------------------------------------------------
            do {
                System.out.print("En columna: ");
                pyI = entrada.nextInt();
                if (pyI >= 0 || pyI < columna) {
                    break;
                } else {
                    System.out.println("Ingrese un numero entre 0 y " + (columna - 1));
                }
            } while (true);
            //--condicion---------------------------------
            if (laberinto[pxI][pyI] != '#') {
                break;
            } else {
                System.out.println("La posicion ya esta ocupada, elija otra");
            }
        } while (true);
        System.out.println("+-+-+-+-+-+-+-+-+-+-+");
        //Punto final-------------------------------------
        do {
            System.out.println("Ingrese un punto de llegada:");
            //-----------------------------------------------------
            do {
                System.out.print("En fila: ");
                pxF = entrada.nextInt();
                if (pxF >= 0 || pxF < fila) {
                    break;
                } else {
                    System.out.println("Ingrese un numero entre 0 y " + (fila - 1));
                }
            } while (true);
            //-----------------------------------------------------
            do {
                System.out.print("En columna: ");
                pyF = entrada.nextInt();

                if (pyF >= 0 || pyF < columna) {
                    if (pyF <= pyI) {
                        break;
                    } else {
                        System.out.println("Ingre una columna menor o igual que " + pyI);
                    }
                } else {
                    System.out.println("Ingrese un numero entre 0 y " + (columna - 1));
                }
            } while (true);
            //--condicion---------------------------------
            if ((laberinto[pxF][pyF] != '#') || (laberinto[pxF][pyF] != laberinto[pxI][pyI])) {
                break;
            } else {
                System.out.println("La posicion ya esta ocupada, elija otra");
            }
        } while (true);
        System.out.println("+-+-+-+-+-+-+-+-+-+-+");
        confirmado = true;
        if (confirmado) {
            Backtraking clase_laberinto = new Backtraking(laberinto, pxF, pyF, 'X');
            clase_laberinto.Salida(pxI, pyI);
            System.out.println("Solucion: \n" + clase_laberinto.imprimir_matrizSolution());
        }
    }

    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }

}
