/**
 * Class that represents a Human Player.
 *
 * @author Gal Kalimi
 */
public class HumanPlayer implements Player {
     /**
     * Default Constructor.
     */
    public HumanPlayer() {}
    /**
     * Plays a turn on the specified game board by interacting with the player to input the desired
     * coordinates for placing the mark.
     *
     * @param board The game board on which the turn is played.
     * @param mark  The mark to be placed on the board (e.g., X or O).
     */
    public void playTurn(Board board, Mark mark) {
        int coordinates, row, col;
        KeyboardInput.getObject();

        switch (mark) {
            case X: {
                System.out.println(Constants.playerRequestInputString("X"));
                break;
            }
            case O: {
                System.out.println(Constants.playerRequestInputString("O"));
                break;
            }
        }

        while (true) {
            coordinates = KeyboardInput.readInt();
            col = coordinates % 10;
            row = coordinates / 10;
            int boardSize = board.getSize();
            if (row >= boardSize || col >= boardSize || row < 0 || col < 0) {
                System.out.println(Constants.INVALID_COORDINATE);
                continue;
            }
            if (board.putMark(mark, row, col)) {
                return;
            }
            System.out.println(Constants.OCCUPIED_COORDINATE);
        }
    }
}
