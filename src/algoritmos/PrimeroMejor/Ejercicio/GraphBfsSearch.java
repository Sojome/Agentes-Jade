
package algoritmos.PrimeroMejor.Ejercicio;

import java.util.ArrayList;
import java.util.HashSet;
 
import java.util.LinkedList;
 
import java.util.List;
import java.util.Optional;
import static javax.management.Query.gt;
import static javax.management.Query.lt;
import java.util.Optional;
import java.util.Queue;
import static javax.management.Query.gt;
import static javax.management.Query.lt;
 


/**
 
import java.util.Optional;
 
import com.raidentrance.graphs.Edge;
 
import com.raidentrance.graphs.Graph;
 
import com.raidentrance.graphs.Node;
 
import com.raidentrance.map.MapBuilder;
 
 */
 
public class GraphBfsSearch {
 
    private Graph graph;
    private String destinations,destinations1;
    private double distances=0,distancea;
    public GraphBfsSearch() {
 
        graph = MapBuilder.getGraph();
 
    }
    
    private Optional<Node> getNode(String city) {
 
        List<Node> nodes = graph.getNodes();//obtine todos los nodos
 
        for (Node node : nodes) {
 
            if (node.getCity().equals(city)) {
 
                return Optional.of(node);
 
            }
 
        }
        
 
        return Optional.empty();
 
    }
    
 
    public boolean hasPathBfs(String source, String destination) {//origen y destino
        Node nodes;
        
        Optional<Node> start = getNode(source);//verifica si existe la ciudad de origen en los nodos ingresados
 
        Optional<Node>  end = getNode(destination);//verifica si existe la ciudad destino en los nodos ingresados
 
        if (start.isPresent() && end.isPresent()) {//verifica si son diferentes de null
 
            return hasPathBfs(start.get(), end.get());//envia el origen y el destino
 
        } else {
 
            return false;
 
        }
 
    }
    public void bfs(String origen){
        Queue<String> q=new LinkedList<String>();// creo una cola para ingresar los nodos visitados
        q.add(origen); // año el nodo a la cola
        
    }
 
    public boolean hasPathBfs(Node source, Node destination) {
 
        LinkedList<Node> nextToVisit = new LinkedList();
 
        HashSet<String> visited = new HashSet();
 
        nextToVisit.add(source);
 
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();//extrae el nodo a visitar
            
            
            /*if (node.getCity().equals(destination.getCity())) {
                return true;
            }*/
 
            if (visited.contains(node.getCity())) {
                continue;
            }
            visited.add(node.getCity());//añade el nodo a la lista de visitados
            int contador=0;
            for (Edge edge : node.getAdjacents()) {
                contador++;
                if(contador>1){
                    if(distances<=edge.getDistance()){
                        distancea=distances;
                        destinations=destinations1;
                    }
                    else{
                        distancea=edge.getDistance();
                        destinations=edge.destino();
                    }
                }
                distances=edge.getDistance();
                destinations1=edge.destino();
                nextToVisit.add(edge.getDestination());
                System.out.println("Destino "+edge.getDestination());
 
            }
 
            return false;
 
        }
        return true;
    }
}