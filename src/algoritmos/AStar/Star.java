package algoritmos.AStar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Star {

    //Indice de nodo inicial, final y minimo de coste.
    private static int row, col, indexOfStartingNode, indexOfEndingNode, indexOfMinCostNode;
    private static String startingNode, endingNode;
    private static double[][] node_cost_node;
    private static ArrayList<String> node_list = new ArrayList<String>();
    private static Queue open_list = new LinkedList(); //lista abierta
    private static Queue closed_list = new LinkedList();  //lista cerrada
    private static double[] total_cost; //costo total
    private static double[] heuristic_cost; //heuristica del costo
    private static boolean visited_node[], destinationReached; //nod visitado, destino alcanzado
    private static int parent_node[]; //nodo padre

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String s = "";
        // BufferedReader clase de java para leer textos
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\NetBeansProjects\\agentes_jade\\src\\algoritmos\\AStar\\tarea.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Documents\\NetBeansProjects\\agentes_jade\\src\\algoritmos\\AStar\\input.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Maximiliano\\Desktop\\input.txt"));
        s = br.readLine();
        String[] a = s.split(" ");//genera un array a partir de una cadena
        row = Integer.parseInt(a[0]);
        col = Integer.parseInt(a[1]);
        s = br.readLine();
        startingNode = s;
        s = br.readLine();
        endingNode = s;
        node_cost_node = new double[row][col];
        total_cost = new double[row]; //fila.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                node_cost_node[i][j] = -1;
            }
            total_cost[i] = Double.MAX_VALUE;
        }
        for (int i = 0; i < col; i++) {
            s = br.readLine();
            String[] found_node = s.split(" ");//genera un array a partir de una cadena
            //Realiza los recoridos antes mencionados y los compara
            if (!node_list.contains((String) found_node[0])) {
                node_list.add((String) found_node[0]);
            }
            if (!node_list.contains((String) found_node[1])) {
                node_list.add((String) found_node[1]);
            }

            node_cost_node[node_list.indexOf((String) found_node[0])][node_list.indexOf((String) found_node[1])] = Double.parseDouble(found_node[2]);
            //System.out.println(node_cost_node [node_list.indexOf((String) found_node[0])] [node_list.indexOf((String) found_node[1])]);
        }

        heuristic_cost = new double[row]; //heuristica del costo
        for (int i = 0; i < row; i++) {
            s = br.readLine();
            String[] found_node = s.split(" ");
            heuristic_cost[node_list.indexOf((String) found_node[0])] = Double.parseDouble(found_node[1]);//cambia si el nodo fue encontrado
        }
        //Inicializacion de variables
        visited_node = new boolean[row];
        parent_node = new int[row];
        indexOfStartingNode = node_list.indexOf(startingNode);
        indexOfEndingNode = node_list.indexOf(endingNode);
        visited_node[indexOfStartingNode] = true;
        open_list.add(node_list.get(indexOfStartingNode));
        total_cost[indexOfStartingNode] = 0;
        startSearch();

    }

    private static void startSearch() {
        while (!destinationReached) {
            if (!open_list.isEmpty()) { //Condicion si la lista abierta esta vacia.
                indexOfMinCostNode = findMinCostNode();
                if (indexOfEndingNode != indexOfMinCostNode) { // vertice actual sea distinto del vertice final
                    open_list.remove(node_list.get(indexOfMinCostNode));
                    for (int i = 0; i < row; i++) {
                        double cost1 = node_cost_node[indexOfMinCostNode][i];
                        double cost2 = node_cost_node[i][indexOfMinCostNode];
                        if (cost1 != -1) { //Busca de entre la lista abierta aquel vertice con menor valor, para luego hacerlo actual
                            if (visited_node[i] == false) {
                                open_list.add(node_list.get(i));
                                if (indexOfStartingNode != indexOfMinCostNode) {
                                    total_cost[i] = heuristic_cost[i] + cost1
                                            + total_cost[indexOfMinCostNode]
                                            - heuristic_cost[indexOfMinCostNode];
                                } else {
                                    total_cost[i] = heuristic_cost[i] + cost1;
                                }

                                visited_node[i] = true;
                                parent_node[i] = indexOfMinCostNode; //
                            } else {
                                //System.out.println("cost1 is not -1 but visited is true");                          
                            }
                        } else if (cost2 != -1) {
                            if (visited_node[i] == false) {
                                open_list.add(node_list.get(i));
                                if (indexOfStartingNode != indexOfMinCostNode) {
                                    total_cost[i] = heuristic_cost[i] + cost2
                                            + total_cost[indexOfMinCostNode]
                                            - heuristic_cost[indexOfMinCostNode];
                                } else {
                                    total_cost[i] = heuristic_cost[i] + cost2;
                                }
                                visited_node[i] = true;
                                parent_node[i] = indexOfMinCostNode;
                            } else {
                                //System.out.println("cost2 is not -1 but visited is true");
                            }
                        } else {
                            // System.out.println("bost costs are -1");
                        }
                    }
                } else {
                    System.out.println("Destino alcanzado!");
                    destinationReached = true;
                    break;
                }

            } else {
                System.out.println("La lista está vacía pero no se alcanzó el destino");
            }

        }
        if (destinationReached) {
            String path = "-->" + node_list.get(indexOfEndingNode);
            int parentIndex = -1;
            double cost = total_cost[indexOfEndingNode];
            int currentIndex = indexOfEndingNode;
            while ((parentIndex = parent_node[currentIndex]) != indexOfStartingNode) {
                path = "-->" + node_list.get(parentIndex) + path;
                currentIndex = parentIndex;
            }
            path = node_list.get(indexOfStartingNode) + path;
            System.out.println("Trayectoria más corta : " + path);
            System.out.println("Coste minimo = " + cost);
        }
    }

    private static int findMinCostNode() {
        int minIndex = -1;
        double minValue = Double.MAX_VALUE;
        for (int i = 0; i < total_cost.length; i++) {
            if (total_cost[i] < minValue && open_list.contains(node_list.get(i))) {
                minValue = total_cost[i];
                minIndex = i;
            }

        }
//      System.out.println("Min value: "+minValue);
//        System.out.println("Min value index is: "+minIndex);
        return minIndex;
    }

}
