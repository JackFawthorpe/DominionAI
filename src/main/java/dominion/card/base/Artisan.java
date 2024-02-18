package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TopDeckRequest;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Artisan"/>
 */
public class Artisan extends Card {
    public Artisan(Player player) {
        setPlayer(player);
        withCost(6);
        withCardType(CardType.ACTION);
        withName("Artisan");
    }

    /**
     * Gain a card costing up to 5 into your hand
     * Put a card from your hand onto the top of your deck
     */
    @Override
    protected void playCardHook() {
        GainCardRequest gainCardRequest = new GainCardRequest(player, new CardSpecification().withMaxCost(5), DeckPosition.HAND);
        gainCardRequest.execute();

        TopDeckRequest topDeckRequest = new TopDeckRequest(player, true, DeckPosition.HAND, new CardSpecification());
        topDeckRequest.execute();
    }
}
