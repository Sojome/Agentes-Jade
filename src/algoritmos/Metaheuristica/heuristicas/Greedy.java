package algoritmos.Metaheuristica.heuristicas;

import algoritmos.Metaheuristica.mochila.*;
import algoritmos.Metaheuristica.heuristicas.*;

public class Greedy extends Heuristica {

    private Solution solucao;

    public void solve(Mochila mochila) {

        this.solucao = this.chuteInicial(mochila, false);

        while (!this.solucao.isFull()) {
            this.solucao.addNextBestItem();
        }

        this.printBestSolution();
    }

    protected void printBestSolution() {

        System.out.println("GULOSO:\n" + this.solucao.toString(true, true) + "\n");
    }

}
