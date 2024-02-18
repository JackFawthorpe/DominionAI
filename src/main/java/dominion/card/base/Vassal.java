package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DrawCardRequest;
import dominion.core.rfa.request.MoveCardRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Vassal"/>
 */
public class Vassal extends Card {

    private static final Logger logger = LogManager.getLogger(Vassal.class);

    public Vassal(Player player) {
        setPlayer(player);
        withMoney(2);
        withCost(3);
        withCardType(CardType.ACTION);
        withName("Vassal");
    }

    /**
     * Discard a card from the top of the deck, if it's an action card you may play it.
     * <p>
     * This is treated as drawing to hand and if action play it else move to discard.
     */
    @Override
    protected void playCardHook() {
        DrawCardRequest drawCardRequest = new DrawCardRequest(player, 1);
        List<Card> draw = drawCardRequest.execute().getResponse();
        if (draw.size() != 1) {
            logger.error("Player {} was required to draw via Vassal and yet couldn't", player.getName());
            throw new RuntimeException("This shouldn't happen. Exiting game");
        }
        Card drawn = draw.get(0);
        if (drawn.isType(CardType.ACTION)) {
            drawn.playCard();
        } else {
            MoveCardRequest moveCardRequest = new MoveCardRequest(player, drawn, DeckPosition.HAND, DeckPosition.DISCARD);
            moveCardRequest.execute();
        }
    }
}
