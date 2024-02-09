package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TrashCardRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Mine"/>
 */
public class Mine extends Card {
    public Mine(Player player) {
        withCost(5);
        withCardType(CardType.ACTION);
        setPlayer(player);
        withName("Mine");
    }

    /**
     * Additional behaviour of playing the mine card.
     * Trash a treasure to gain a treasure worth up to 3 more
     */
    @Override
    protected void playCardHook() {
        CardSpecification trashable = new CardSpecification().withType(CardType.TREASURE);
        TrashCardRequest trashCardRequest = new TrashCardRequest(player, false, trashable, DeckPosition.HAND);
        Card trashed = trashCardRequest.execute().getResponse();
        if (trashed == null) {
            return;
        }
        CardSpecification gainable = new CardSpecification()
                .withMaxCost(trashed.getCost() + 3)
                .withType(CardType.TREASURE);
        GainCardRequest gainCardRequest = new GainCardRequest(player, gainable, DeckPosition.HAND);
        gainCardRequest.execute();
    }
}
