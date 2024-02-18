package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.player.Entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Placeholder controller for the actions that a player can take on their turn
 */
public class ThoughtfulBuyerController extends PlayerController {

    private final Random random = new Random();

    /**
     * Registers the player for within the RequestForActionRouter to receive actions for the player
     *
     * @param player The player that this controller is responsible for controlling
     */
    public ThoughtfulBuyerController(Player player) {
        super(player);
    }

    /**
     * Default Choose Action behaviour (This will action a random card)
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    @Override
    protected Card playActionCardHook(@NotNull List<Card> actionOptions) {
        return actionOptions.isEmpty() ? null : getRandomCard(actionOptions);
    }

    /**
     * Default Purchasing behaviour (This will buy a random card)
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    @Override
    protected Card buyCardHook(@NotNull List<Card> buyOptions) {
        if (buyOptions.isEmpty()) {
            return null;
        }

        ArrayList<Card> cards = new ArrayList<>(buyOptions);

        cards.sort(Comparator.comparingInt(Card::getCost).reversed());
        return cards.get(0);
    }

    /**
     * Default discarding behaviour (This will discard a random card only when its necessary to discard)
     *
     * @param discardOptions The cards in their hand
     * @return The card to discard
     */
    @Override
    protected Card discardFromHandHook(@NotNull List<Card> discardOptions, boolean isRequired) {
        return discardOptions.isEmpty() || !isRequired ? null : getRandomCard(discardOptions);
    }

    /**
     * Default gain card behaviour (This will gain a random card)
     *
     * @param gainOptions The cards the play has to choose from
     * @return The card to gain
     */
    @Override
    protected Card gainCardHook(@NotNull List<Card> gainOptions) {
        return gainOptions.isEmpty() ? null : getRandomCard(gainOptions);
    }

    @Override
    protected Card trashCardHook(@NotNull List<Card> trashOptions, boolean isRequired) {
        return trashOptions.isEmpty() || !isRequired ? null : getRandomCard(trashOptions);
    }

    /**
     * Gets a random card from the list
     *
     * @param cards The cards to get from
     * @return A random card from the above list
     */
    private Card getRandomCard(List<Card> cards) {
        return cards.get(random.nextInt(cards.size()));
    }

    @Override
    protected Card chooseTopDeckHook(@NotNull List<Card> topDeckOptions, boolean required) {
        return topDeckOptions.isEmpty() ? null : getRandomCard(topDeckOptions);
    }
}
