package api.ai;

import dominion.card.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Placeholder controller for the actions that a player can take on their turn
 */
public class ThoughtfulBuyerController implements ActionController {

    private final Random random = new Random();

    /**
     * Default Purchasing behaviour (This will buy a random card)
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    public Card buyCardHook(@NotNull List<Card> buyOptions) {
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
    public Card discardFromHandHook(@NotNull List<Card> discardOptions, boolean isRequired) {
        return discardOptions.isEmpty() || !isRequired ? null : getRandomCard(discardOptions);
    }

    /**
     * Default gain card behaviour (This will gain a random card)
     *
     * @param gainOptions The cards the play has to choose from
     * @return The card to gain
     */
    public Card gainCardHook(@NotNull List<Card> gainOptions) {
        return gainOptions.isEmpty() ? null : getRandomCard(gainOptions);
    }

    public Card trashCardHook(@NotNull List<Card> trashOptions, boolean isRequired) {
        return trashOptions.isEmpty() || !isRequired ? null : getRandomCard(trashOptions);
    }

    public Card chooseTopDeckHook(@NotNull List<Card> topDeckOptions, boolean required) {
        return topDeckOptions.isEmpty() ? null : getRandomCard(topDeckOptions);
    }

    /**
     * Default Choose Action behaviour (This will action a random card)
     *
     * @return It will return the first action card in the hand (array-based) or null if there is no card
     */
    public Card playActionCardHook(@NotNull List<Card> actionOptions) {
        return actionOptions.isEmpty() ? null : getRandomCard(actionOptions);
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

}
