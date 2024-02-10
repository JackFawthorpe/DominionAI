package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardType;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.DrawCardRequest;
import dominion.core.state.RoundRobinManager;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/CouncilRoom"/>
 */
public class CouncilRoom extends Card {

    public CouncilRoom(Player player) {
        setPlayer(player);
        withCost(5);
        withSimpleDraw(4);
        withBuys(1);
        withCardType(CardType.ACTION);
        withName("Council Room");
    }

    /**
     * Each other player gains a curse
     */
    @Override
    protected void playCardHook() {
        for (Player toAttack : RoundRobinManager.getInstance().getPlayers()) {
            if (!toAttack.equals(player)) handleOpponentDraw(toAttack);
        }
    }

    private void handleOpponentDraw(Player opponent) {
        DrawCardRequest request = new DrawCardRequest(opponent, 1);
        request.execute();
    }
}
