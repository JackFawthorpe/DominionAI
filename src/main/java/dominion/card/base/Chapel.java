package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.TrashCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Chapel"/>
 */
public class Chapel extends Card {

    public Chapel(Player player) {
        setPlayer(player);
        withName("Chapel");
        withCost(2);
        withCardType(CardType.ACTION);
    }

    /**
     * Card Description:
     * Trash up to 4 cards in hand
     */
    @Override
    protected void playCardHook() {
        boolean canTrash = !player.isHandEmpty();
        int trashed = 0;
        while (trashed < 4 && canTrash) {
            TrashCardRequest request = new TrashCardRequest(player, false, new CardSpecification(), DeckPosition.HAND);
            Card trashedCard = request.execute().getResponse();
            if (trashedCard == null || player.isHandEmpty()) canTrash = false;
            trashed++;
        }
    }
}
