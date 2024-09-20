import java.util.Random;

/**
 * Class that represents a Genius Player, who is better than Whatever Player and Clever Player.
 *
 * @author Gal Kalimi
 */
public class GeniusPlayer implements Player{
    /**
     * Default Constructor.
     */
    public GeniusPlayer() {}
    /**
     * Plays a turn on the specified game board by attempting to place the given mark the first available
     * cell of the second row. if the send row is full - it moves to the next one snd so on. At the end
     * it moves to the first row and try to place the given mark the first available cell of it, until there
     * are no more available cells.
     *
     * @param board The game board on which the turn is played.
     * @param mark  The mark to be placed on the board (X or O).
     */
    public void playTurn(Board board, Mark mark) {
        for (int i = 1; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.putMark(mark, i, j)) {
                    return;
                }
            }
        }
        for (int j = 0; j < board.getSize(); j++) {
            if (board.putMark(mark, 0, j)) {
                return;
            }

        }
    }
}
