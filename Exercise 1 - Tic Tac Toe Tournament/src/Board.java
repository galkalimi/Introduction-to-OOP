/**
 * Class that represents Board.
 *
 * @author Gal Kalimi
 */
public class Board {
    private int size = 4;
    private Mark[][] markArr;

    /**
     * Constructor
     */
    public Board(){
        markArr = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                markArr[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * Constructor to a size X size board.
     *
     * @param size the amount of rows and columns in the new board.
     */
    public Board(int size){
        this.size = size;
        markArr = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                markArr[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * Gets the size of the board.
     *
     * @return The size of the board.
     */
    public int getSize() {
        return size;
    }

    /**
     * Places a mark on the game board at the specified coordinates if possible.
     *
     * @param mark  The mark to be placed.
     * @param row   The row index.
     * @param col   The column index.
     * @return      True if the mark is placed successfully; otherwise, false.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!coordinatesInBounds(row, col)) {
            return false;
        }
        if (markArr[row][col] != Mark.BLANK) {
            return false;
        }
        markArr[row][col] = mark;
        return true;
    }

    /**
     * Gets the mark at specified coordinates.
     *
     * @param row   The row index.
     * @param col   The column index.
     * @return      The mark at specified coordinates.
     */
    public Mark getMark(int row, int col) {
        if (!coordinatesInBounds(row, col)) {
            return Mark.BLANK;
        }
        return markArr[row][col];
    }

    /**
     * Checks if the specified coordinates are within the bounds of the game board.
     *
     * @param row   The row index.
     * @param col   The col index.
     * @return      True if the specified coordinates are within the bounds of the game board;
     *              False otherwise.
     */
    private boolean coordinatesInBounds(int row, int col) {
        boolean rowIsValid = false, colIsValid = false;
        if (row >= 0 && row < size) {
            rowIsValid = true;
        }
        if (col >= 0 && col < size) {
            colIsValid = true;
        }
        return rowIsValid && colIsValid;
    }
}
