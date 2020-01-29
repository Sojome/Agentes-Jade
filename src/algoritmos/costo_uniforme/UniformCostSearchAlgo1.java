package algoritmos.costo_uniforme;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class UniformCostSearchAlgo1 {

    public static void main(String[] args) {

        // inicializa la base del gráfico
        Node n1 = new Node("Esmeraldas");
        Node n2 = new Node("Carchi");
        Node n3 = new Node("Imbabura");
        Node n4 = new Node("Pichincha");
        Node n5 = new Node("Santo Domingo De Los Tsachilas");
        Node n6 = new Node("Manabi");
        Node n7 = new Node("Sucumbios");
        Node n8 = new Node("Napo");
        Node n9 = new Node("Cotopaxi");
        Node n10 = new Node("Los Rios");
        Node n11 = new Node("Guayas");
        Node n12 = new Node("Santa Elena");
        Node n13 = new Node("Bolivar");
        Node n14 = new Node("Tungurahua");
        Node n15 = new Node("Chimborazo");
        Node n16 = new Node("Orellana");
        Node n17 = new Node("Pastaza");
        Node n18 = new Node("Morona Santiago");
        Node n19 = new Node("Cañar");
        Node n20 = new Node("Azuay");
        Node n21 = new Node("Zamora Cinchipe");
        Node n22 = new Node("El Oro");
        Node n23 = new Node("Loja");
        Node n24 = new Node("Galapagos");

// inicializa los bordes
        n1.adjacencies = new Edge[]{
            new Edge(n2, 75),
            new Edge(n3, 140),
            new Edge(n4, 118),
            new Edge(n5, 89),
            new Edge(n6, 125)

        };

        n2.adjacencies = new Edge[]{
            new Edge(n1, 75),
            new Edge(n3, 71),
            new Edge(n7, 230)

        };

        n3.adjacencies = new Edge[]{
            new Edge(n1, 140),
            new Edge(n2, 99),
            new Edge(n7, 151),
            new Edge(n4, 80),};

        n4.adjacencies = new Edge[]{
            new Edge(n1, 71),
            new Edge(n3, 50),
            new Edge(n7, 111),
            new Edge(n8, 94),
            new Edge(n9, 123),
            new Edge(n2, 68),
            new Edge(n5, 100)

        };

        n5.adjacencies = new Edge[]{
            new Edge(n1, 99),
            new Edge(n4, 211),
            new Edge(n6, 140),
            new Edge(n9, 99),
            new Edge(n10, 151),
            new Edge(n11, 96),
            new Edge(n12, 125)

        };

        n6.adjacencies = new Edge[]{
            new Edge(n1, 99),
            new Edge(n5, 211),
            new Edge(n10, 140),
            new Edge(n11, 99),
            new Edge(n12, 151)

        };

        n7.adjacencies = new Edge[]{
            new Edge(n2, 97),
            new Edge(n3, 115),
            new Edge(n4, 138),
            new Edge(n8, 98),
            new Edge(n16, 121)
        };

        n8.adjacencies = new Edge[]{
            new Edge(n4, 98),
            new Edge(n9, 132),
            new Edge(n14, 85),
            new Edge(n17, 99),
            new Edge(n16, 112),
            new Edge(n7, 124)

        };

        n9.adjacencies = new Edge[]{
            new Edge(n5, 92),
            new Edge(n4, 101),
            new Edge(n8, 116),
            new Edge(n14, 81),
            new Edge(n13, 125),
            new Edge(n10, 77)
        };

        n10.adjacencies = new Edge[]{
            new Edge(n6, 70),
            new Edge(n5, 75),
            new Edge(n9, 74),
            new Edge(n13, 88),
            new Edge(n11, 124),
            new Edge(n6, 104)

        };

        n11.adjacencies = new Edge[]{
            new Edge(n6, 75),
            new Edge(n10, 121),
            new Edge(n19, 103),
            new Edge(n20, 120),
            new Edge(n22, 129),
            new Edge(n12, 86),};

        n12.adjacencies = new Edge[]{
            new Edge(n12, 119),
            new Edge(n6, 146),};

        n13.adjacencies = new Edge[]{
            new Edge(n10, 101),
            new Edge(n9, 90),
            new Edge(n14, 211),
            new Edge(n15, 91),
            new Edge(n11, 124),};
        n14.adjacencies = new Edge[]{
            new Edge(n9, 120),
            new Edge(n8, 146),
            new Edge(n18, 138),
            new Edge(n15, 124),
            new Edge(n13, 128)
        };

        n15.adjacencies = new Edge[]{
            new Edge(n13, 101),
            new Edge(n14, 90),
            new Edge(n18, 211),
            new Edge(n19, 100),
            new Edge(n11, 111),};
        n16.adjacencies = new Edge[]{
            new Edge(n7, 120),
            new Edge(n8, 146),
            new Edge(n17, 138)
        };

        n17.adjacencies = new Edge[]{
            new Edge(n8, 101),
            new Edge(n16, 90),
            new Edge(n18, 211)
        };
        n18.adjacencies = new Edge[]{
            new Edge(n17, 120),
            new Edge(n15, 146),
            new Edge(n20, 138)
        };

        n19.adjacencies = new Edge[]{
            new Edge(n15, 101),
            new Edge(n18, 90),
            new Edge(n11, 211)
        };
        n20.adjacencies = new Edge[]{
            new Edge(n19, 120),
            new Edge(n11, 146),
            new Edge(n18, 138),
            new Edge(n22, 124),
            new Edge(n23, 124),
            new Edge(n16, 124)
        };

        n21.adjacencies = new Edge[]{
            new Edge(n18, 101),
            new Edge(n20, 90),
            new Edge(n23, 211)
        };
        n22.adjacencies = new Edge[]{
            new Edge(n20, 120),
            new Edge(n23, 146),
            new Edge(n21, 138)
        };
        n23.adjacencies = new Edge[]{
            new Edge(n22, 120),
            new Edge(n21, 146),
            new Edge(n20, 138)
        };

        n24.adjacencies = new Edge[]{
            new Edge(n23, 90)
        };

        UniformCostSearch(n10, n20);

        List<Node> path = printPath(n20);

        System.out.println("Path: " + path);

    }

    public static void UniformCostSearch(Node source, Node goal) {

        source.pathCost = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
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

                    /*System.out.println(child);
                    System.out.println(queue);
                    System.out.println();*/
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

class Node {

    public final String value;
    public double pathCost;
    public Edge[] adjacencies;
    public Node parent;

    public Node(String val) {

        value = val;

    }

    public String toString() {
        return value;
    }

}

class Edge {

    public final double cost;
    public final Node target;

    public Edge(Node targetNode, double costVal) {
        cost = costVal;
        target = targetNode;

    }
}
