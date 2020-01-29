
package algoritmos.PrimeroMejor.Ejercicio;

public class Main {
    public static void main(String[] args) {

        GraphBfsSearch graph = new GraphBfsSearch();
        System.out.println("\n\t Caminos desde DF \n");
        System.out.println(String.format("Desde DF Hasta Puebla %s", graph.hasPathBfs("DF", "Puebla")));
    }
}
