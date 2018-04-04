/*
The main purpose of this class should be to run a single instance of a Player vs player game, then return a value
based on who won. We can prompt them to play again, change settings, etc. in a different class.
 */


public class PlayerVsPlayer {
    //I did a static int so that it could return a value based on who won
    public static int PlayerVsPlayer() {

        int turn = 0; //0 = player 1's turn, 1 = player 2's turn
        //drawBoard() - we should have code somewhere that makes it draw/update the board.
        while(true) {
            if (turn == 0) {
                System.out.println("Player 1's turn, where would you like to move?");
                /*
                getResponse() - Call class that allows player to select which square to move in.
                 */
                turn = 1;

                //drawBoard(some input from getResponse);
            } else {
                System.out.println("Player 2's turn, where would you like to move?");
                //getResponse();
                turn = 0;
                //drawBoard(some input from getResponse);
            }
            //use game logic to check if there are 3 in a row. Also, we will have to make sure the user can't move somewhere that has a piece
            //if there are 3 in a row, check if they are X's or O's, return 0 for X, and return 1 for O.
        }
    }
    //We can also have a main function that keeps calling this sub function for one turn
    public static int turn(int turn) {

    }
}
