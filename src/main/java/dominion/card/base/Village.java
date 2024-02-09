package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DrawCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Village"/>
 */
public class Village extends Card {

    public Village(Player player) {
        withActions(2);
        withCost(3);
        setOwner(player);
        withCardType(CardType.ACTION);
        withName("Village");
    }

    /**
     * Draw card
     */
    @Override
    protected void playCardHook() {
        DrawCardRequest request = new DrawCardRequest(owner, 1);
        request.execute();
    }
}
