package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.rfa.request.DrawCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Cellar"/>
 */
public class Cellar extends Card {

    public Cellar(Player player) {
        setOwner(player);
        withName("Cellar");
        withCost(2);
        withActions(1);
        withCardType(CardType.ACTION);
    }

    /**
     * Card Description:
     * Discard any number of cards, then draw that many
     */
    @Override
    protected void playCardHook() {
        boolean canDiscard = !owner.isHandEmpty();
        int discardCount = 0;
        while (canDiscard) {
            DiscardFromHandRequest request = new DiscardFromHandRequest(owner, false);
            boolean discardedCard = request.getResponse() != null;
            if (discardedCard) {
                discardCount++;
            } else {
                canDiscard = false;
            }
            canDiscard = canDiscard && !owner.isHandEmpty();
        }

        DrawCardRequest request = new DrawCardRequest(owner, discardCount);
        request.execute();
    }
}
