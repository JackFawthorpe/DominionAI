package dominion.core.player;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.card.supply.Copper;
import dominion.card.supply.Estate;
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
     * The players hand
     */
    private final List<Card> hand;
    /**
     * Cards that aren't yet in the discard pile but have been played
     */
    private final List<Card> played;
    /**
     * The cards that are next in line to be drawn
     */
    private List<Card> draw;
    /**
     * Cards that are in the discard pile
     */
    private List<Card> discard;

    public PlayerDeck(Player player) {
        draw = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            draw.add(new Copper(player));
        }
        for (int i = 0; i < 3; i++) {
            draw.add(new Estate(player));
        }
        Collections.shuffle(draw);
        hand = new ArrayList<>();
        played = new ArrayList<>();
        discard = new ArrayList<>();
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

    public List<Card> getDraw() {
        return Collections.unmodifiableList(draw);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public List<Card> getDiscard() {
        return Collections.unmodifiableList(discard);
    }

    public List<Card> getPlayed() {
        return Collections.unmodifiableList(played);
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
     * Handles the moving of cards from the hand to the played pile
     *
     * @param card The card that is played
     * @return true if the operation was successful
     */
    public boolean playCard(Card card) {
        boolean removed = hand.remove(card);
        played.add(card);
        return removed;
    }

    /**
     * Finds all the cards in the players hand that are actions
     *
     * @return An unmodifiable list of the action cards in the players hand
     */
    public List<Card> getHandActionCards() {
        return hand.stream()
                .filter(card -> card.getCardType() == CardType.ACTION)
                .toList();
    }
}
