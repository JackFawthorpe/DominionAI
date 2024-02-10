package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.card.supply.Curse;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.state.RoundRobinManager;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Witch"/>
 */
public class Witch extends Card {

    public Witch(Player player) {
        setPlayer(player);
        withCost(5);
        withSimpleDraw(2);
        withCardType(CardType.ACTION);
        withCardType(CardType.ATTACK);
        withName("Witch");
    }


    /**
     * Each other player gains a curse
     */
    @Override
    protected void playCardHook() {
        for (Player toAttack : RoundRobinManager.getInstance().getPlayers()) {
            if (!toAttack.equals(player)) handleAttack(toAttack);
        }
    }

    private void handleAttack(Player toAttack) {
        GainCardRequest request = new GainCardRequest(toAttack, new CardSpecification().withCard(Curse.class), DeckPosition.DISCARD);
        request.asAttack();
        request.execute();
    }
}
