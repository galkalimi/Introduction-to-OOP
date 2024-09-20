import java.util.Random;

/**
 * Class that represents a Random Player.
 *
 * @author Gal Kalimi
 */
public class WhateverPlayer implements Player{
    /**
     * Constructor
     */
    public WhateverPlayer(){}

    /**
     * Plays a turn of specified mark on board. The mark is being placed randomly.
     *
     * @param board Board to play a turn on.
     * @param mark  Mark to place oon the board.
     */
    public void playTurn(Board board, Mark mark) {
        Random random = new Random();
        int row, col;
        while (true){
            row = random.nextInt(board.getSize()) ;
            col = random.nextInt(board.getSize());
            if (board.putMark(mark, row, col)) return;
        }

    }
}
