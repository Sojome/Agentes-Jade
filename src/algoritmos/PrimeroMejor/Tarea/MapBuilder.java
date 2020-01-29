
package algoritmos.PrimeroMejor.Tarea;

import algoritmos.PrimeroMejor.Ejercicio.*;

public class MapBuilder {
 
    private static final Graph instance = new Graph();
 
    MapBuilder() {
 
    }
 
    public static Graph getGraph() {
 
        Node Esmeraldas = new Node("Esmeraldas");
 
        Node Guayaquil = new Node("Guayaquil");
 
        Node Quito = new Node("Quito");
 
        Node Manabí = new Node("Manabí");
 
        Node Ambato = new Node("Ambato");
 
        Node Cuenca = new Node("Cuenca");
 
        Esmeraldas.addEdge(new Edge(Esmeraldas, Guayaquil, 100));
 
        Esmeraldas.addEdge(new Edge(Esmeraldas, Quito, 90));
 
        Guayaquil.addEdge(new Edge(Guayaquil, Manabí, 350));
 
        Guayaquil.addEdge(new Edge(Guayaquil, Quito, 150));
 
        Guayaquil.addEdge(new Edge(Guayaquil, Ambato, 340));
 
        Guayaquil.addEdge(new Edge(Guayaquil, Esmeraldas, 100));
 
        Quito.addEdge(new Edge(Quito, Esmeraldas, 90));
 
        Quito.addEdge(new Edge(Quito, Manabí, 100));
 
        Manabí.addEdge(new Edge(Manabí, Ambato, 20));
 
        Manabí.addEdge(new Edge(Manabí, Guayaquil, 350));
 
        instance.addNode(Esmeraldas);
 
        instance.addNode(Guayaquil);
 
        instance.addNode(Quito);
 
        instance.addNode(Manabí);
 
        instance.addNode(Cuenca);
 
        instance.addNode(Ambato);
 
        return instance;
 
    }
 
}