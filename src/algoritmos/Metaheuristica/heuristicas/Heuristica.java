package algoritmos.Metaheuristica.heuristicas;

import algoritmos.Metaheuristica.mochila.*;
import algoritmos.Metaheuristica.heuristicas.*;

import java.util.Random;

public abstract class Heuristica {

    protected Solution chuteInicial(Mochila mochila, boolean random) {

        int[] s = new int[mochila.getNumItens()];

        if (random) {

            Random rand = new Random();
            boolean b;

            for (int i = 0; i < mochila.getNumItens(); i++) {

                b = rand.nextBoolean();
                System.out.print(b + ",");
                //System.out.print(mochila.getItens() +" ");
                if (b) {
                    s[i] = 1; // si es verdadero le da el valor de 1

                    //System.out.print(b);
                } else {
                    s[i] = 0;   // caso contrario 0
                    //System.out.print(b);
                }
            }

        } else {

            for (int i : s) {
                s[i] = 0;   // le da a todo el arrglo valor de cero
            }

        }

        return new Solution(s, mochila);
    }

    public void solve(Mochila mochila) {
    }

    protected void printBestSolution() {
    }

}
