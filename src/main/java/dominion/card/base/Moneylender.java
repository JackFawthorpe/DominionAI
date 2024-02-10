package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.card.supply.Copper;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.TrashCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/MoneyLender"/>
 */
public class Moneylender extends Card {

    public Moneylender(Player player) {
        setPlayer(player);
        withName("Moneylender");
        withCost(4);
        withCardType(CardType.ACTION);
    }

    /**
     * Lets the player trash a copper for +3 money for the turn
     */
    @Override
    protected void playCardHook() {
        TrashCardRequest request = new TrashCardRequest(player, false, new CardSpecification().withCard(Copper.class), DeckPosition.HAND);
        Card trashed = request.execute().getResponse();
        if (trashed != null) {
            player.updateTurnResources(0, 0, 3);
        }
    }
}
