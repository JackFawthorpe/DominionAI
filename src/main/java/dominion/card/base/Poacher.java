package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.state.KingdomManager;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Poacher"/>
 */
public class Poacher extends Card {

    public Poacher(Player player) {
        withCost(4);
        withSimpleDraw(1);
        withActions(1);
        withMoney(1);
        setPlayer(player);
        withName("Poacher");
        withCardType(CardType.ACTION);
    }

    /**
     * The player needs to discard a card for each supply pile that has emptied since the start of the game
     */
    @Override
    protected void playCardHook() {
        int toDiscard = KingdomManager.getInstance().getDepletedSupplyPileCount();
        for (int i = 0; i < toDiscard; i++) {
            DiscardFromHandRequest request = new DiscardFromHandRequest(player, true);
            request.execute();
        }
    }
}
