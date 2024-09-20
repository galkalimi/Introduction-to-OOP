/**
 * Class that represents a Game.
 *
 * @author Gal Kalimi
 */
public class Game {
    private static final int DEFAULT_BOARD_SIZE = 4;
    private static final int DEFAULT_WIN_STREAK_SIZE = 3;
    private Player playerX;
    private Player playerO;
    private Renderer renderer;
    private int size;
    private int winStreak;
    private Board board;
    private int maxTurnsToPlay;

    /**
     * Initializes a new game with specified players and renderer.
     *
     * @param playerX   The player representing 'X' in the game.
     * @param playerO   The player representing 'O' in the game.
     * @param renderer  The renderer responsible for displaying the game state.
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.size = DEFAULT_BOARD_SIZE;
        this.winStreak = DEFAULT_WIN_STREAK_SIZE;
        this.board = new Board(size);
        maxTurnsToPlay = size * size;
    }

    /**
     * Initializes a new game with the specified players, board size, win streak size, and renderer.
     *
     * @param playerX    The player representing 'X' in the game.
     * @param playerO    The player representing 'O' in the game.
     * @param size       The size of the game board.
     * @param winStreak  The number of consecutive marks required to win the game.
     * @param renderer   The renderer responsible for displaying the game state.
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.size = size;
        this.renderer = renderer;
        this.winStreak = (winStreak > size) ? this.size : winStreak;
        this.board = new Board(size);
        maxTurnsToPlay = size * size;
    }

    /**
     * Retrieves the number of consecutive marks required to win the game.
     *
     * @return The win streak size for the game.
     */
    int getWinStreak() {
        return winStreak;
    }

    /**
     * Retrieves the size of the game board.
     *
     * @return The size of the game board.
     */
    int getBoardSize() {
        return size;
    }
    /**
     * Runs game in a loop, allowing players to take turns until a win condition is met or the maximum
     * number of turns is reached.
     *
     * @return The Mark representing the winner, or BLANK if the game ends in a draw.
     */
    Mark run() {
        while (true) {
            playerX.playTurn(board, Mark.X);
            maxTurnsToPlay--;
            renderer.renderBoard(board);
            if (turnWinsGame(Mark.X)) {
                return Mark.X;
            }
            if (maxTurnsToPlay == 0) {
                return Mark.BLANK;
            }
            playerO.playTurn(board, Mark.O);
            maxTurnsToPlay--;
            renderer.renderBoard(board);
            if (turnWinsGame(Mark.O)) {
                return Mark.O;
            }
            if (maxTurnsToPlay == 0) {
                return Mark.BLANK;
            }
        }
    }

    /**
     * Checks if the current player's move results in a win by completing a row, column, or diagonal on
     * the game board.
     *
     * @param mark The Mark representing the current player's move.
     * @return True if the move wins the game; False otherwise.
     */
    private boolean turnWinsGame(Mark mark) {
        return turnCompletesRowOrColStreak(mark) || turnCompletesDiagonalStreak(mark);
    }

    /**
     * Checks there is a row/column on the game board with win streak of marks.
     *
     * @param mark  The Mark representing the current player's move.
     * @return      True if the specified mark has won the game; False otherwise.
     */
    private boolean turnCompletesRowOrColStreak(Mark mark) {
        int rowStreak, colStreak;
        for (int i = 0; i < size; i++) {
            rowStreak = 0;
            colStreak = 0;
            for (int j = 0; j < size; j++) {
                if (board.getMark(i, j) == mark) {
                    rowStreak++;
                } else {
                    rowStreak = 0;
                }
                if (board.getMark(j, i) == mark) {
                    colStreak++;
                } else {
                    colStreak = 0;
                }
                if (rowStreak == winStreak || colStreak == winStreak) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the current player's move completes a diagonal streak of the specified win streak size on
     * the game board.
     * We scan 2 types of diagonals - left to right descending, and right to left ascending.
     * Each type is being divided to the upper and the lower half's of the board, relative to the diagonal
     * direction, and is being checked on a helper function - rightToLeftAscendingDiagonalWin and
     * leftToRightDescendingDiagonalWin.
     *
     * @param mark  The Mark representing the current player's move.
     * @return      True if the move completes a diagonal streak; False otherwise.
     */
    private boolean turnCompletesDiagonalStreak(Mark mark) {
        return turnCompletesRightToLeftAscendingDiagonal(mark) ||
                turnCompletesLeftToRightDescendingDiagonal(mark);
    }

    /**
     * Checks if the current player's move completes a right to left ascending diagonal.
     * The check is being made by dividing the board to a lower and upper half (relative to the diagonal),
     * and checking each half with a helper function.
     *
     * @param mark  The mark to check if it has completed a right to left ascending diagonal.
     * @return      True if the specified mark has completed a right to left ascending diagonal; False
     *              otherwise.
     */
    private boolean turnCompletesRightToLeftAscendingDiagonal(Mark mark) {
        int lowerStreak, upperStreak;
        for (int i = 0; i < size; i++) {
            lowerStreak = 0;
            upperStreak = 0;
            for (int j = 0; j < size - i; j++) {
                lowerStreak = checkStreak(mark, size - j - 1, size - i - j - 1, lowerStreak);
                upperStreak = checkStreak(mark, size - j - 1, size - i - j - 1, upperStreak);
                if (lowerStreak == winStreak) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Checks if the current player's move completes a left to right descending diagonal.
     * The check is being made by dividing the board to a lower and upper half (relative to the diagonal),
     * and checking each half with a helper function.
     *
     * @param mark  The mark to check if it has completed a left to right descending diagonal.
     * @return      True if the specified mark has completed a left to right descending diagonal; False
     *              otherwise.
     */
    private boolean turnCompletesLeftToRightDescendingDiagonal(Mark mark) {
        int lowerStreak, upperStreak;
        for (int i = 0; i < size; i++) {
            lowerStreak = 0;
            upperStreak = 0;
            for (int j = 0; j < size - i; j++) {
                lowerStreak = checkStreak(mark, i + j, j, lowerStreak);
                upperStreak = checkStreak(mark, j, i + j, upperStreak);
                if (lowerStreak == winStreak) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helps to update a streak of specified mark, given the current streak and the next position to check.
     *
     * @param mark          The mark to update its current streak.
     * @param row           The row of the next position in the streak.
     * @param col           The column of the next position in the streak.
     * @param currentStreak The amount of marks in a streak up until now.
     * @return              The updated streak.
     */
    private int checkStreak(Mark mark, int row, int col, int currentStreak) {
        if (board.getMark(row, col) == mark) {
            return currentStreak++;
        }
        return 0;
    }
}
