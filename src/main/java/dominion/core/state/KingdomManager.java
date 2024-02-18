package dominion.core.state;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.base.*;
import dominion.card.supply.*;
import dominion.core.exception.IllegalMoveException;
import dominion.core.geb.GameEventBus;
import dominion.core.geb.event.SupplyPileDepletedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the collection of cards that are owned by no players. (This is where players will buy / gain cards from
 */
public class KingdomManager {

    private static final Logger logger = LogManager.getLogger(KingdomManager.class);

    private static KingdomManager instance;

    private Map<String, Integer> supply;
    private List<Card> cardReferences;

    private KingdomManager() {
        initSupply();
        initCardReferences();
    }

    /**
     * Initialises the initial supply of cards.
     * Currently, this is the base set of cards that is recommended to learn the game with
     *
     * @see <a href="https://wiki.dominionstrategy.com/index.php/First_Game"/>
     */
    private void initSupply() {
        supply = new HashMap<>();
        supply.put("Copper", 60 - 7 * RoundRobinManager.getInstance().getPlayerCount());
        supply.put("Curse", (RoundRobinManager.getInstance().getPlayerCount() - 1) * 10);
        supply.put("Silver", 40);
        supply.put("Gold", 30);
        supply.put("Estate", 24);
        supply.put("Duchy", 12);
        supply.put("Province", 12);
        supply.put("Cellar", 10);
        supply.put("Market", 10);
        supply.put("Merchant", 10);
        supply.put("Militia", 10);
        supply.put("Mine", 10);
        supply.put("Moat", 10);
        supply.put("Remodel", 10);
        supply.put("Smithy", 10);
        supply.put("Village", 10);
        supply.put("Workshop", 10);
    }

    /**
     * This initialises a reference list of cards that are within the card supply.
     * Currently, this is the base set of cards that is recommended to learn the game with
     *
     * @see <a href="https://wiki.dominionstrategy.com/index.php/First_Game"/>
     */
    private void initCardReferences() {
        cardReferences = new ArrayList<>();
        cardReferences.add(new Copper(null));
        cardReferences.add(new Silver(null));
        cardReferences.add(new Gold(null));
        cardReferences.add(new Estate(null));
        cardReferences.add(new Duchy(null));
        cardReferences.add(new Province(null));
        cardReferences.add(new Curse(null));
        cardReferences.add(new Cellar(null));
        cardReferences.add(new Market(null));
        cardReferences.add(new Merchant(null));
        cardReferences.add(new Militia(null));
        cardReferences.add(new Mine(null));
        cardReferences.add(new Moat(null));
        cardReferences.add(new Remodel(null));
        cardReferences.add(new Smithy(null));
        cardReferences.add(new Village(null));
        cardReferences.add(new Workshop(null));
    }

    public static void gameReset() {
        instance = new KingdomManager();
    }

    public static KingdomManager getInstance() {
        if (instance == null) {
            instance = new KingdomManager();
        }
        return instance;
    }

    /**
     * Returns all the cards that match the given specification and are still available to buy / gain
     *
     * @param cardSpecification The specification to test against
     * @return The list of cards that match the specification
     */
    public List<Card> getAvailableCards(CardSpecification cardSpecification) {
        return cardSpecification.filterCards(cardReferences.stream()
                .filter(card -> supply.get(card.getName()) > 0)
                .map(Card::clone)
                .toList());
    }

    /**
     * Removes a card of the given type from the supply
     *
     * @param card the card to be taken from the supply
     */
    public void removeCard(Card card) {
        logger.info("Attempting to remove a {} from the card supply", card.getName());
        Integer cardCount = supply.get(card.getName());
        if (cardCount == null || cardCount <= 0) {
            throw new IllegalMoveException("Illegal move detected. Exiting game");
        }
        supply.put(card.getName(), cardCount - 1);
        // If pile is depleted fire event
        if (cardCount - 1 == 0) {
            GameEventBus.getInstance().notifyListeners(new SupplyPileDepletedEvent(card));
        }
    }

    /**
     * Returns the amount of supply piles that have ran out since the start of the game
     *
     * @return The count
     */
    public int getDepletedSupplyPileCount() {
        return (int) cardReferences.stream().filter(card -> supply.get(card.getName()) == 0).count();
    }

}
