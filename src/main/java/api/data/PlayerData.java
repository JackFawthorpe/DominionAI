package api.data;

import dominion.core.state.RoundRobinManager;

/**
 * This class represents all the data that is accessible for a player as an agent
 */
public class PlayerData {
    private final int order;

    private final DeckData deckData;

    public PlayerData(int order, DeckData deckData) {
        this.order = order;
        this.deckData = deckData;
    }

    /**
     * This is the amount of points the player currently has
     */
    public int getPoints() {
        return RoundRobinManager.getInstance().getPlayers().get(order).getPoints();
    }


    /**
     * This is what position on the table the player is sitting. For instance the first to
     * go will be 0, then 1, 2 and so on
     */
    public int getOrder() {
        return order;
    }

    /**
     * This is a read only version of the players deck so that you know what is inside it
     */
    public DeckData getDeckData() {
        return deckData;
    }
}
