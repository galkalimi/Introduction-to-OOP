/**
 *Interface that represents a player in a game.
 */
public interface Player {
    /**
     * Makes a move on the game board.
     *
     * @param board     The game board on which the player will play on.
     * @param mark      The mark to use for the move.
     */
    public void playTurn(Board board, Mark mark);
}
