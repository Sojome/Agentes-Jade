
package algoritmos.PrimeroMejor.Ejercicio;

import java.util.ArrayList;
 
import java.util.List;
 

 
public class Graph {
 
    private List nodes = new ArrayList();
 
    public void addNode(Node node) {
 
        nodes.add(node);
 
    }
 
    public List getNodes() {
 
        return nodes;
 
    }
 
    @Override
 
    public String toString() {
 
        return "Grafico [Nodos=" + nodes + "]";
 
    }
 
}