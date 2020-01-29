package algoritmos.Metaheuristica.testdrive;

import java.util.ArrayList;
import java.util.Scanner;
import algoritmos.Metaheuristica.heuristicas.*;
import algoritmos.Metaheuristica.mochila.*;
import algoritmos.Metaheuristica.mochila.InstanceFactory;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        InstanceFactory factory = new InstanceFactory("C:\\Users\\user\\Documents\\NetBeansProjects\\agentes_jade\\src\\algoritmos\\Metaheuristica\\instancias.txt");
        ArrayList<Mochila> instancias = factory.produce();

        SubidaEncosta se = new SubidaEncosta();
        SimulatedAnnealing sa = new SimulatedAnnealing();
        Greedy guloso = new Greedy();
        Grasp grasp = new Grasp(0.8, 100);
        int eleccion = 0;
        do {
            System.out.println("Que algoritmo metahuristico desea utilizar");
            System.out.println(
                    "1. Hill Climb\n"
                    + "2. Simulated Anneling\n"
                    + "3. Greedy\n"
                    + "4. Grasp\n"
                    + "5. Todas"
            );
            eleccion = entrada.nextInt();
        } while (eleccion > 5 || eleccion < 0);

        for (Mochila instancia : instancias) {

            switch (eleccion) {
                case 1:
                    se.solve(instancia);
                    System.out.println("-----------------------------------------");
                    break;
                case 2:
                    sa.solve(instancia);
                    System.out.println("-----------------------------------------");
                    break;
                case 3:
                    guloso.solve(instancia);
                    System.out.println("-----------------------------------------");
                    break;
                case 4:
                    grasp.solve(instancia);
                    System.out.println("-----------------------------------------");
                    break;
                case 5:
                    guloso.solve(instancia);
                    System.out.println("-----------------------------------------");
                    grasp.solve(instancia);
                    System.out.println("-----------------------------------------");
                    se.solve(instancia);
                    System.out.println("-----------------------------------------");
                    sa.solve(instancia);
                    System.out.println("-----------------------------------------");
                    break;
            }
        }

        entrada.close();
    }

}
