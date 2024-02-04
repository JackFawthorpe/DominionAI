package dominion.core.player;

import dominion.card.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the deck that a player will have within a game
 */
public class PlayerDeck {

    private static final Logger logger = LogManager.getLogger(PlayerDeck.class);

    /**
     * The cards that are next in line to be drawn
     */
    private List<Card> draw;

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
    private List<Card> discard;

    public PlayerDeck() {
        this.draw = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.played = new ArrayList<>();
        this.discard = new ArrayList<>();
    }

    public List<Card> getDraw() {
        return draw;
    }

    public void setDraw(List<Card> draw) {
        this.draw = draw;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getDiscard() {
        return discard;
    }

    public void setDiscard(List<Card> discard) {
        this.discard = discard;
    }

    public List<Card> getPlayed() {
        return played;
    }

    public void setPlayed(List<Card> played) {
        this.played = played;
    }

    /**
     * Removes all the cards from play and in hand and places them into the discard pile
     * It then draws 5 cards
     */
    public void cleanUp() {
        discard.addAll(played);
        discard.addAll(hand);
        played.clear();
        hand.clear();
        draw(5);
    }

    /**
     * Draw will take count cards from the pile and place's them into the players hand
     * <p>
     * In the event that the player doesn't have enough cards to draw then the discard will be shuffled and
     * replace the draw pile
     */
    public void draw(int count) {
        int firstDraw = Math.min(count, draw.size());
        hand.addAll(draw.subList(0, firstDraw));
        draw.removeAll(draw.subList(0, firstDraw));
        count -= firstDraw;
        if (count == 0) {
            return;
        }
        newDraw();
        hand.addAll(draw.subList(0, count));
        draw.removeAll(draw.subList(0, count));
    }

    /**
     * Responsible for shifting the cards from the discard pile, shuffling them and placing them into the draw pile
     */
    private void newDraw() {
        logger.info("Shuffling discard into draw pile");
        draw = discard;
        Collections.shuffle(draw);
        discard = new ArrayList<>();
    }
}
