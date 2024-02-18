package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.CardType;
import dominion.card.supply.Silver;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TopDeckRequest;
import dominion.core.state.RoundRobinManager;

/**
 * Card from Game
 *
 * @see <a href="https://wiki.dominionstrategy.com/index.php/Bureaucrat"/>
 */
public class Bureaucrat extends Card {

    public Bureaucrat(Player player) {
        setPlayer(player);
        withName("Bureaucrat");
        withCardType(CardType.ACTION);
        withCardType(CardType.ATTACK);
        withCost(4);
    }

    /**
     * Gain a silver onto the top of your deck and then force each other player to dop deck a victory card from their hand
     */
    @Override
    protected void playCardHook() {
        GainCardRequest request = new GainCardRequest(player, new CardSpecification().withCard(Silver.class), DeckPosition.DRAW);
        request.execute();
        for (Player toAttack : RoundRobinManager.getInstance().getPlayers()) {
            if (!toAttack.equals(player)) handleAttack(toAttack);
        }
    }

    private void handleAttack(Player toAttack) {
        TopDeckRequest request = new TopDeckRequest(toAttack, true, DeckPosition.HAND, new CardSpecification().withType(CardType.VICTORY));
        request.asAttack();
        request.execute();
    }
}
