package dominion.core.player;

import dominion.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the deck that a player will have within a game
 */
public class PlayerDeck {

    /**
     * The cards that are next in line to be drawn
     */
    private List<Card> toDraw;

    /**
     * The players hand
     */
    private List<Card> hand;

    /**
     * Cards that aren't yet in the discard pile but have been played
     */
    private List<Card> played;

    /**
     * Cards that are in the discard pile
     */
    private List<Card> discardPile;

    public PlayerDeck() {
        this.toDraw = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.played = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    public List<Card> getToDraw() {
        return toDraw;
    }

    public void setToDraw(List<Card> toDraw) {
        this.toDraw = toDraw;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(List<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public List<Card> getPlayed() {
        return played;
    }

    public void setPlayed(List<Card> played) {
        this.played = played;
    }
}
