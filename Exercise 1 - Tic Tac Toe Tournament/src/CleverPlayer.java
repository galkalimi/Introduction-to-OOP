import java.util.Random;

/**
 * Class that represents a Clever Player, who is better than Whatever Player and worse than Genius Player.
 *
 * @author Gal Kalimi
 */
public class CleverPlayer implements Player {
    /**
     * Constructor.
     */
    public CleverPlayer() {

    }

    /**
     * Plays a turn on the specified game board by attempting to place the given mark in the first available
     * cell in the second column.
     * If all cells in the column are occupied, moves to the next column.
     *
     *
     * @param board The game board on which the turn is played.
     * @param mark  The mark to be placed on the board (X or O).
     */
    public void playTurn(Board board, Mark mark) {

        for (int col = 0; col < board.getSize(); col++) {
            for (int row = 0; row < board.getSize(); row++) {
                if (board.putMark(mark, row, col)) {
                    return;
                }
            }
        }
    }
}
