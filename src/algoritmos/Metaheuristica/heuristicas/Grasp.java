package algoritmos.Metaheuristica.heuristicas;

import java.util.Random;
import algoritmos.Metaheuristica.mochila.*;
import algoritmos.Metaheuristica.heuristicas.*;

public class Grasp extends Heuristica {

    private double alpha;

    private int interations;

    private Solution solucao;

    private Random random = new Random();

    public Grasp(double alpha, int interations) {

        this.alpha = alpha;
        this.interations = interations;
    }

    public void solve(Mochila mochila) {

        Solution solucaoAlternativa;

        for (int i = 0; i < this.interations; i++) {

            // asignar una codiciosa solución inicial
            this.solucao = this.solucaoInicial(mochila);

            // iterando sobre cada par de la solución
            solucaoAlternativa = this.buscaLocal(mochila);

            if (solucaoAlternativa.z() > this.solucao.z()) {
                this.solucao = solucaoAlternativa;
            }
        }

        System.out.println("GRASP:\n" + this.solucao.toString(false, true));
    }

    protected Solution solucaoInicial(Mochila mochila) {

        this.solucao = this.chuteInicial(mochila, false);

        while (!this.solucao.isFull()) {

            if (this.random.nextDouble() <= this.alpha) {
                this.solucao.addNextBestItem();
            } else {
                this.solucao.addNextItem(true);
            }
        }

        return solucao;
    }

    private Solution buscaLocal(Mochila mochila) {

        Solution solution, newSolution, bestSolution = this.solucao;

        if (this.random.nextDouble() < this.alpha) {

// realice 2-opt en el 20% de las cantidades de artículos de la mochila al azar y elija la mejor solución entre ellas
            int numSolucoes = (int) (0.2 * this.solucao.size()) + 1;

            solution = this.solucao;

            for (int i = 0; i < numSolucoes; i++) {

                newSolution = this.opt2(solution);
                newSolution.tryAddOneMore();

                if (newSolution.z() > solution.z()) {

                    solution = newSolution;

                    if (!newSolution.estoura()) {
                        bestSolution = newSolution;
                    }
                }
            }

        } else {

            // ejecuta 2-opt solo una vez al azar
            newSolution = this.opt2(this.solucao);

            if (!newSolution.estoura()) {

                bestSolution = newSolution;
                bestSolution.tryAddOneMore();
            }
        }

        return bestSolution;
    }

    private Solution opt2(Solution solution) {

        int index1, index2;

        // elegir al azar un elemento seleccionado
        do {

            index1 = this.random.nextInt(solution.size());

        } while (solution.selecionado(index1));

        // retirando este item da mochila
        solution.retiraItem(index1);

        // agregue otro elemento no seleccionado todavía
        do {

            index2 = this.random.nextInt(solution.size());

        } while (!solution.selecionado(index2));

        return new Solution(solution, index2);
    }

}
