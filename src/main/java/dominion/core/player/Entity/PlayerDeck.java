package dominion.core.player.Entity;

import dominion.card.Card;
import dominion.card.CardSpecification;
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
    private final Player owner;
    /**
     * The cards that are next in line to be drawn
     */
    private List<Card> draw;
    /**
     * Cards that are in the discard pile
     */
    private List<Card> discard;

    public PlayerDeck(Player owner) {
        this.owner = owner;
        draw = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            draw.add(new Copper(owner));
        }
        for (int i = 0; i < 3; i++) {
            draw.add(new Estate(owner));
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
        count = Math.min(count, draw.size() + discard.size());
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

    /**
     * Gives public access to getting the cards within a position of the hand
     *
     * @param position The position of cards to return
     * @return The cards in said position
     */
    public List<Card> getCards(DeckPosition position) {
        return mapPosition(position);
    }

    /**
     * Maps the enum describing deck position to the lists that they represent
     *
     * @param position The position to map
     * @return The list of cards that is represented by the enum
     */
    private List<Card> mapPosition(DeckPosition position) {
        return switch (position) {
            case DRAW -> this.draw;
            case HAND -> this.hand;
            case PLAYED -> this.played;
            case DISCARD -> this.discard;
        };
    }

    /**
     * Gives public access to getting the cards within a position of the hand
     *
     * @param position The position of cards to return
     * @return The cards in said position
     */
    public List<Card> getCards(DeckPosition position, CardSpecification cardSpecification) {
        return cardSpecification.filterCards(mapPosition(position));
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
     * Adds a card to the players deck at the given position
     *
     * @param card     The card to add to the deck
     * @param position The position in the deck for the card to reside
     */
    public void addCard(Card card, DeckPosition position) {
        card.setPlayer(owner);
        mapPosition(position).add(0, card);
    }

    /**
     * Takes a card that is in the from pile of cards and puts it into the to pile
     *
     * @param card The card to move
     * @param from The pile to remove the card from
     * @param to   The pile to put the card in
     */
    public boolean moveCard(Card card, DeckPosition from, DeckPosition to) {
        mapPosition(to).add(card);
        return mapPosition(from).remove(card);
    }

    /**
     * Removes a card from the list of cards at the given position
     *
     * @param toRemove The card to remove
     * @param position The position to remove it from
     * @return If the removal of the card was successful
     */
    public boolean trashCard(Card toRemove, DeckPosition position) {
        return mapPosition(position).remove(toRemove);
    }
}
