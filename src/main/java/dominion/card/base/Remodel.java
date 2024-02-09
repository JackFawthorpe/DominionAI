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
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Remodel"/>
 */
public class Remodel extends Card {

    public Remodel(Player player) {
        withCost(4);
        withCardType(CardType.ACTION);
        setPlayer(player);
        withName("Remodel");
    }

    /**
     * Trash a card from your hand and gain a card costing up to 2 more than it
     */
    @Override
    protected void playCardHook() {
        TrashCardRequest trashCardRequest = new TrashCardRequest(player, true, new CardSpecification(), DeckPosition.HAND);
        Card card = trashCardRequest.execute().getResponse();
        if (card == null) {
            return;
        }
        GainCardRequest gainCardRequest = new GainCardRequest(player, new CardSpecification().withMaxCost(card.getCost() + 2), DeckPosition.DISCARD);
        gainCardRequest.execute();
    }
}
