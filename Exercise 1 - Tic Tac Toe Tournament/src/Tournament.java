/**
 * Class that represents a tournament.
 *
 * @author Gal Kalimi
 */
public class Tournament {
    private int rounds;
    private Renderer renderer;
    private Player[] players;
    private static final int NUM_OF_PLAYERS = 2;

    /**
     * Initializes a Tournament with specified players, renderer, and the amount of rounds to play.
     *
     * @param rounds    The amount of rounds to play.
     * @param renderer  Renderer to render the board with.
     * @param player1   A player that plays this tournament.
     * @param player2   A player that plays this tournament.
     */
    Tournament(int rounds, Renderer renderer,Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = new Player[NUM_OF_PLAYERS];
        this.players[0] = player1;
        this.players[1] = player2;
    }

    /**
     * Plays out the tournament with specified values and prints a summary of the results.
     *
     * @param size          Size of the tournament's Board.
     * @param winStreak     The amount of marks in a row (row/column/diagonal) required to win a game.
     * @param playerName1   Name of player 1
     * @param playerName2   Name of player 2
     */
    void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        Mark winner;
        String[] playerNames = new String[2];
        playerNames[0] = playerName1;
        playerNames[1] = playerName2;
        int[] playersWinCounts = new int []{0, 0};
        for (int roundIndex = 0; roundIndex < rounds; roundIndex++) {
            Game game = new Game(players[roundIndex % 2],
                    players[(roundIndex + 1) % 2], size, winStreak, renderer);
            winner = game.run();
            switch (winner){
                case X:
                    playersWinCounts[roundIndex % 2]++;
                    break;
                case O:
                    playersWinCounts[(roundIndex + 1) % 2]++;
                    break;
            }
        }
        System.out.println("Player 1, " + playerNames[0] + " won: " + playersWinCounts[0] + " rounds");
        System.out.println("Player 2, " + playerNames[1] + " won: " + playersWinCounts[1] + " rounds");
        System.out.println("Ties: " + (rounds - playersWinCounts[0] - playersWinCounts[1]));
    }

    /**
     * Run a Tic Tac Toe tournament.
     * Simulates a Tic Tac Toe tournament with specified parameters.
     * It validates input arguments, and runs the tournament by using the playTournament method.
     *
     * @param args Command-line arguments provided by the user.
     */
    public static void main(String[] args) {
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        String rendererName = args[3];

        if (!validateAndPrintErrorMessageForRendererName(rendererName)) {
            return;
        }
        RendererFactory renderFactory = new RendererFactory();
        Renderer renderer = renderFactory.buildRenderer(rendererName, size);

        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            if (!validateAndPrintErrorMessageForPlayerName(args[4 + i])) {
                return;
            }
        }
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(args[4]);
        Player player2 = playerFactory.buildPlayer(args[5]);
        Tournament tournament = new Tournament(rounds, renderer, player1, player2);
        tournament.playTournament(size, winStreak, args[4], args[5]);
    }

    /**
     * Checks if name is a valid Player name. Prints an error if the name isn't valid.
     *
     * @param name      The Player name to validate.
     * @return          True if name is a valid Player name; False otherwise.
     */
    private static boolean validateAndPrintErrorMessageForPlayerName(String name) {
        if (isValidPlayerName(name)){
            return true;
        }
        System.out.println(Constants.UNKNOWN_PLAYER_NAME);
        return false;
    }
    /**
     * Checks if name is a valid Renderer name. Prints an error if the name isn't valid.
     *
     * @param name      The Renderer name to validate.
     * @return          True if name is a valid Renderer name; False otherwise.
     */
    private static boolean validateAndPrintErrorMessageForRendererName(String name) {
        if (isValidRendererName(name)){
            return true;
        }
        System.out.println(Constants.UNKNOWN_RENDERER_NAME);
        return false;
    }

    /**
     * Checks if name is a valid Player name.
     *
     * @param name      The Player name to validate.
     * @return          True if name is a valid Player name; False otherwise.
     */
    private static boolean isValidPlayerName(String name) {
        String[] validPlayerNames = {"human", "whatever", "clever", "genius"};
        return isValidString(name,validPlayerNames);
    }
    /**
     * Checks if name is a valid Renderer name.
     *
     * @param name      The Renderer name to validate.
     * @return          True if name is a valid Renderer name; False otherwise.
     */
    private static boolean isValidRendererName(String name) {
        String[] validPlayerNames = {"none", "console"};
        return isValidString(name,validPlayerNames);
    }

    /**
     * Checks if a String is valid, given a string array of valid Strings - validOptions.
     *
     * @param stringToValidate  A String we want to validate.
     * @param validOptions      A String array of valid String options.
     * @return                  True if stringToValidate appears in validOptions; False otherwise.
     */
    private static boolean isValidString(String stringToValidate, String[] validOptions) {
        for (String validOption: validOptions)
            if (stringToValidate.equalsIgnoreCase(validOption)) {
                return true;
            }
        return false;
    }
}
