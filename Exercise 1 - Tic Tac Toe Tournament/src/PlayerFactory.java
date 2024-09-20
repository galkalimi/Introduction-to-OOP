/**
 * Represents a Player Factory that creates players.
 *
 * @author Gal Kalimi
 */
public class PlayerFactory {
    /**
     * Creates a Player.
     *
     * @param playerName    Name of the player to create.
     * @return              Player if created a player successfully; Null otherwise.
     */
    public Player buildPlayer(String playerName){
        Player player = null;

        switch (playerName.toLowerCase()){
            case "human":
                player = new HumanPlayer();
                break;
            case "whatever":
                player = new WhateverPlayer();
                break;
            case "clever":
                player = new CleverPlayer();
                break;
            case "genius":
                player = new GeniusPlayer();
                break;
        }
        return player;
    }
}
