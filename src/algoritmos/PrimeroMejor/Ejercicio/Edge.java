
package algoritmos.PrimeroMejor.Ejercicio;

public class Edge {
 
    public Node origin;
 
    public Node destination;
 
    public double distance;
 
    public Edge(Node origin, Node destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public Node getOrigin() {
        return origin;
    }
 
    public void setOrigin(Node origin) {
        this.origin = origin;
    }
 
    public Node getDestination() {
        return destination;
 
    }
 
    public void setDestination(Node destination) {
        this.destination = destination;
    }
 
    public double getDistance() {
        return distance;
    }
 
    public void setDistance(double distance) {
 
        this.distance = distance;
 
    }
 
    public String destino(){
        return destination.getCity();
    }
    @Override
    public String toString() {
 
        return "Recorrido [Origen=" + origin.getCity() + ", Destino =" + destination.getCity() + ", Distancia=" + distance
 
                + " Km] \n";
 
    }
 
}