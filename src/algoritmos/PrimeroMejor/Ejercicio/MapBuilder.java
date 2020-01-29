
package algoritmos.PrimeroMejor.Ejercicio;

public class MapBuilder {
 
    private static final Graph instance = new Graph();
 
    MapBuilder() {
 
    }
 
    public static Graph getGraph() {
 
        Node df = new Node("DF");
 
        Node toluca = new Node("Toluca");
 
        Node cuernavaca = new Node("Cuernavaca");
 
        Node puebla = new Node("Puebla");
 
        Node tlaxcala = new Node("Tlaxcala");
 
        Node cancun = new Node("Cancún");
 
        df.addEdge(new Edge(df, toluca, 100));
 
        df.addEdge(new Edge(df, cuernavaca, 90));
 
        toluca.addEdge(new Edge(toluca, puebla, 350));
 
        toluca.addEdge(new Edge(toluca, cuernavaca, 150));
 
        toluca.addEdge(new Edge(toluca, tlaxcala, 340));
 
        toluca.addEdge(new Edge(toluca, df, 100));
 
        cuernavaca.addEdge(new Edge(cuernavaca, df, 90));
 
        cuernavaca.addEdge(new Edge(cuernavaca, puebla, 100));
 
        puebla.addEdge(new Edge(puebla, tlaxcala, 20));
 
        puebla.addEdge(new Edge(puebla, toluca, 350));
 
        instance.addNode(df);
 
        instance.addNode(toluca);
 
        instance.addNode(cuernavaca);
 
        instance.addNode(puebla);
 
        instance.addNode(cancun);
 
        instance.addNode(tlaxcala);
 
        return instance;
 
    }
 
}