package algoritmos.minimax;

import java.util.Scanner;

/**
 * GomokuPlay class contains main method that plays the game.
 */
public class GomokuPlay {

    /**
     * Main method. Parse command line arguments to get game settings
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n, m, g;
        
        do {
            System.out.println("Ingrese el tamaño del tablero (mayor o igual a 8):");
            n = scan.nextInt();
        } while (n<8);
        
        do {            
            System.out.println("Ingrese la longitud de la cadena ganadora que no sea mayor a "+n+" :");
            m = scan.nextInt();
        } while (m>n || m<5);
        
        System.out.println("Ingrese el modo de juego:\n"
                + "1. Humano vs Smart Agent\n"
                + "2. Random vs Smart Agent\n"
                + "3. Smart vs Smart Agent");
        do {            
            g = scan.nextInt();
        } while (g<1 || g>3);
        /*int n = Integer.parseInt(args[0]); // size of board
         int m = Integer.parseInt(args[1]); // length of winning chain
         int g = Integer.parseInt(args[3]); // game mode*/
        System.out.println("**********************************");
        // human vs smart agent
        if (g == 1) {
            System.out.println("Would you like to go first or second? Please enter 1 or 2");
            int turn = scan.nextInt();
            if (turn == 1) {
                Human x = new Human(n, m, true);
                SmartAgent o = new SmartAgent(n, m, false);
                o.setTamano(n);
                String xMove, oMove = "";
                xMove = x.takeTurn();
                System.out.println(xMove);
                boolean play = true;
                while (play) {
                    o.receiveTurn(xMove);
                    oMove = o.takeTurn();
                    if (o.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                    x.receiveTurn(oMove);
                    xMove = x.takeTurn();
                    if (x.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                }

            } else {
                SmartAgent x = new SmartAgent(n, m, false);
                Human o = new Human(n, m, true);
                String xMove, oMove = "";
                xMove = x.firstTurn();
                System.out.println(xMove);
                boolean play = true;
                while (play) {
                    o.receiveTurn(xMove);
                    oMove = o.takeTurn();
                    if (o.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                    x.receiveTurn(oMove);
                    xMove = x.takeTurn();
                    if (x.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                }
            }
        }

        // random vs smart agent
        if (g == 2) {
            System.out.println("Would you like the smart agent to go first or second? Please enter 1 or 2");
            int turn = scan.nextInt();
            if (turn == 1) {
                SmartAgent x = new SmartAgent(n, m, true);
                RandomAgent o = new RandomAgent(n, m, false);
                x.setTamano(n);
                String xMove, oMove = "";
                xMove = x.firstTurn();
                System.out.println(xMove);
                boolean play = true;
                while (play) {
                    o.receiveTurn(xMove);
                    oMove = o.takeTurn();
                    if (o.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                    x.receiveTurn(oMove);
                    xMove = x.takeTurn();
                    if (x.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                }
            } else {
                RandomAgent x = new RandomAgent(n, m, true);
                SmartAgent o = new SmartAgent(n, m, false);
                o.setTamano(n);
                String xMove, oMove = "";
                xMove = x.takeTurn();
                System.out.println(xMove);
                boolean play = true;
                while (play) {
                    o.receiveTurn(xMove);
                    oMove = o.takeTurn();
                    if (o.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (o.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                    x.receiveTurn(oMove);
                    xMove = x.takeTurn();
                    if (x.board.winner == 'x') {
                        System.out.println("Winner: x");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'o') {
                        System.out.println("Winner: o");
                        play = false;
                        break;
                    }
                    if (x.board.winner == 'd') {
                        System.out.println("Winner: d");
                        play = false;
                        break;
                    }
                }
            }
        }

        // smart vs smart agent
        if (g == 3) {
            SmartAgent x = new SmartAgent(n, m, true);
            SmartAgent o = new SmartAgent(n, m, false);
            o.setTamano(n);
            x.setTamano(n);
            String xMove, oMove = "";
            xMove = x.firstTurn();
            boolean play = true;
            while (play) {
                o.receiveTurn(xMove);
                oMove = o.takeTurn();
                if (o.board.winner == 'x') {
                    System.out.println("Winner: x");
                    play = false;
                    break;
                }
                if (o.board.winner == 'o') {
                    System.out.println("Winner: o");
                    play = false;
                    break;
                }
                if (o.board.winner == 'd') {
                    System.out.println("Winner: d");
                    play = false;
                    break;
                }
                x.receiveTurn(oMove);
                xMove = x.takeTurn();
                if (x.board.winner == 'x') {
                    System.out.println("Winner: x");
                    play = false;
                    break;
                }
                if (x.board.winner == 'o') {
                    System.out.println("Winner: o");
                    play = false;
                    break;
                }
                if (x.board.winner == 'd') {
                    System.out.println("Winner: d");
                    play = false;
                    break;
                }
            }
        }
    }
}
