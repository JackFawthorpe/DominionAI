package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.GainCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Workshop"/>
 */
public class Workshop extends Card {

    public Workshop(Player player) {
        withCost(3);
        withCardType(CardType.ACTION);
        setOwner(player);
        withName("Workshop");
    }

    /**
     * Gain a card costing up to 4
     */
    @Override
    protected void playCardHook() {
        GainCardRequest request = new GainCardRequest(owner, new CardSpecification().withMaxCost(4), DeckPosition.DISCARD);
        request.execute();
    }
}
