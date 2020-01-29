package algoritmos.costo_uniforme;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import algoritmos.costo_uniforme.*;

public class TareaCostoUniforme {

    public static void main(String[] args) {

        // inicializa la base del gráfico
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        Node n3 = new Node("C");
        Node n4 = new Node("D");
        Node n5 = new Node("E");
        Node n6 = new Node("F");
        Node n7 = new Node("G");
        Node n8 = new Node("Z");

// inicializa los bordes
        n1.adjacencies = new Edge[]{
            new Edge(n3, 239),
            new Edge(n2, 368),
            new Edge(n7, 203),};

        n2.adjacencies = new Edge[]{
            new Edge(n1, 368),
            new Edge(n3, 299),};

        n3.adjacencies = new Edge[]{
            new Edge(n1, 239),
            new Edge(n2, 299),
            new Edge(n5, 322),
            new Edge(n4, 350),};

        n4.adjacencies = new Edge[]{
            new Edge(n3, 350),
            new Edge(n8, 352),};

        n5.adjacencies = new Edge[]{
            new Edge(n3, 322),
            new Edge(n6, 323),
            new Edge(n8, 299),};

        n6.adjacencies = new Edge[]{
            new Edge(n7, 111),
            new Edge(n5, 323),};

        n7.adjacencies = new Edge[]{
            new Edge(n1, 203),
            new Edge(n6, 111),};

        n8.adjacencies = new Edge[]{
            new Edge(n4, 352),
            new Edge(n5, 299),};

        UniformCostSearch(n1, n8);

        List<Node> path = printPath(n8);

        System.out.println("Path: " + path);

    }

    public static void UniformCostSearch(Node source, Node goal) {

        source.pathCost = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(8,
                new Comparator<Node>() {

// anular el método de comparación
                    public int compare(Node i, Node j) {
                        if (i.pathCost > j.pathCost) {
                            return 1;
                        } else if (i.pathCost < j.pathCost) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
        );

        queue.add(source);
        Set<Node> explored = new HashSet<Node>();
        boolean found = false;

// mientras la frontera no está vacía
        do {

            Node current = queue.poll();
            explored.add(current);

            if (current.value.equals(goal.value)) {
                found = true;

            }

            for (Edge e : current.adjacencies) {
                Node child = e.target;
                double cost = e.cost;
                child.pathCost = current.pathCost + cost;

                if (!explored.contains(child) && !queue.contains(child)) {

                    child.parent = current;
                    queue.add(child);

                    System.out.println(child);
                    System.out.println(queue);
                    System.out.println();

                } else if ((queue.contains(child)) && (child.pathCost > current.pathCost)) {
                    child.parent = current;

                    // las siguientes dos llamadas disminuyen la clave del nodo en la cola
                    queue.remove(child);
                    queue.add(child);
                }

            }
        } while (!queue.isEmpty());

    }

    public static List<Node> printPath(Node target) {
        List<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.parent) {
            path.add(node);
        }

        Collections.reverse(path);

        return path;

    }

}
