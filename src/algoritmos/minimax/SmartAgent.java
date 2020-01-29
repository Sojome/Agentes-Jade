package algoritmos.minimax;

/**
 * SmartAgent class that uses minimax algorithm and heuristic evaluation to pick
 * next move
 */
public class SmartAgent extends Agent {

    Minimax minimax;

    int tamano;

    public SmartAgent(int n, int m, boolean isFirst) {
        super(n, m, isFirst);
        minimax = new Minimax();
    }

    String firstTurn() {
        // pick default first move
        //String move = "7 7";
        String move = generatRandomPositiveNegitiveValue(getTamano(), 0)+
                " "+
                generatRandomPositiveNegitiveValue(getTamano(), 0);
        board.placeMove(me, move, true);
        System.out.println(board);
        return move;
    }

    String takeTurn() {
        String move = pickMove();
        board.placeMove(me, move, true);
        System.out.println(board);
        return move;
    }

    String pickMove() {
        // use minimax
        Object[] move = minimax.mmab(board, 1, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY);
        return (String) move[1];
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    public static int generatRandomPositiveNegitiveValue(int max, int min) {
        //Random rand = new Random();
        int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
        return ii;
    }

}
