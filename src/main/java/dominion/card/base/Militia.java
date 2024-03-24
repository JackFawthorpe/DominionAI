package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.state.RoundRobinManager;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Militia"/>
 */
public class Militia extends Card {

    public Militia(Player player) {
        withMoney(2);
        withCost(4);
        setPlayer(player);
        withName("Militia");
        withCardType(CardType.ACTION);
        withCardType(CardType.ATTACK);
    }

    /**
     * Forces each other player to discard down to 3 cards unless blocked
     */
    @Override
    protected void playCardHook() {
        for (Player toAttack : RoundRobinManager.getInstance().getPlayers()) {
            if (!toAttack.equals(player)) handleAttack(toAttack);
        }
    }

    private void handleAttack(Player toAttack) {
        while (toAttack.getDeck().getHand().size() > 3) {
            DiscardFromHandRequest request = new DiscardFromHandRequest(toAttack, true);
            request.asAttack();
            Card discarded = request.execute().getResponse();

            // If discarded is null it's assumed that it was blocked
            if (discarded == null) {
                break;
            }
        }
    }
}
