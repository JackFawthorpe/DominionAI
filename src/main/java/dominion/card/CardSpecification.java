package dominion.card;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Class to filter lists of cards based on certain characteristics
 */
public class CardSpecification {

    List<Predicate<Card>> filters;

    public CardSpecification() {
        this.filters = new ArrayList<>();
    }

    /**
     * Applies the filters that are in the CardSpecification on the list of cards provided
     *
     * @param cards The cards to filter against
     * @return The list of cards that pass all the predicates
     */
    public List<Card> filterCards(List<Card> cards) {
        Stream<Card> cardStream = cards.stream();
        for (Predicate<Card> filter : this.filters) {
            cardStream = cardStream.filter(filter);
        }

        return cardStream.toList();
    }

    /**
     * Checks if the given card matches all current filters
     *
     * @param card The card to test
     * @return True if the card fits within the filters
     */
    public boolean isValid(Card card) {
        for (Predicate<Card> filter : filters) {
            if (!filter.test(card)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Specifies the max cost of a filtered card
     *
     * @param cost The cost to filter against
     * @return this
     */
    public CardSpecification withMaxCost(int cost) {
        filters.add(card -> card.getCost() <= cost);
        return this;
    }

    /**
     * Specifies the card type of filtered card
     *
     * @param cardType The card to filter against
     * @return this
     */
    public CardSpecification withType(CardType cardType) {
        filters.add(card -> card.isType(cardType));
        return this;
    }

    /**
     * Adds a filter to force the card to be a certain card in the game
     *
     * @param cardClass The class of card to test against
     * @param <T>       The class of the card
     * @return this
     */
    public <T extends Card> CardSpecification withCard(Class<T> cardClass) {
        filters.add(card -> card.getClass() == cardClass);
        return this;
    }
}
