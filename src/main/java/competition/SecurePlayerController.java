package competition;

import api.agent.ActionController;
import dominion.card.Card;
import dominion.core.exception.IllegalMoveException;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerControllerImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SecurePlayerController extends PlayerControllerImpl {

    /**
     * Registers the player for within the RequestForActionRouter to receive actions
     * for the player
     *
     * @param player           The player that this controller is responsible for controlling
     * @param actionController The agent that will be controlling this controller
     */
    public SecurePlayerController(Player player, ActionController actionController) {
        super(player, actionController);
    }

    @Override
    protected Card handleBuyCard() {

        CompletableFuture<Card> card = CompletableFuture.supplyAsync(super::handleBuyCard);
        try {
            return card.get(250, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof IllegalMoveException) {
                throw new CompetitionException(String.format("Player %s made an illegal move while buying a card", player.getId()), 32 + player.getId());
            } else if (e.getCause() instanceof OutOfMemoryError) {
                throw new CompetitionException(String.format("Memory ran out during Player %s's turn", player.getId()), 32 + player.getId());
            } else {
                throw new CompetitionException("Error occurred during players turn", 3);
            }
        } catch (InterruptedException e) {
            throw new CompetitionException("Handle buy card thread was interrupted", 40);
        } catch (TimeoutException e) {
            throw new CompetitionException(String.format("Player %s timed out on handleBuyCard", player.getId()), 28 + player.getId());
        }
    }


}
