package competition;

import api.agent.ActionController;
import dominion.card.Card;
import dominion.core.exception.IllegalMoveException;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerControllerImpl;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TopDeckRequest;
import dominion.core.rfa.request.TrashCardRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * The SecurePlayerController is a wrapper used in the competition to secure the code that students submit
 * It wraps the code in a timed thread to make sure the students can't achieve a blocking attack
 */
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
    protected Card handlePlayActionCard() {
        return secureAction(super::handlePlayActionCard, "handlePlayActionCard");
    }

    @Override
    protected Card handleBuyCard() {
        return secureAction(super::handleBuyCard, "handleBuyCard");
    }

    @Override
    protected Card handleDiscardFromHand(DiscardFromHandRequest request) {
        return secureAction(() -> super.handleDiscardFromHand(request), "handleDiscardFromHand");
    }

    @Override
    protected List<Card> handleDrawCard(int drawCount) {
        return secureAction(() -> super.handleDrawCard(drawCount), "handleDrawCard");
    }

    @Override
    protected Card handleGainCardRequest(GainCardRequest request) {
        return secureAction(() -> super.handleGainCardRequest(request), "handleGainCardRequest");
    }

    @Override
    protected Card handleTrashCard(TrashCardRequest request) {
        return secureAction(() -> super.handleTrashCard(request), "handleTrashCard");
    }

    @Override
    protected Card handleTopDeckRequest(TopDeckRequest request) {
        return secureAction(() -> super.handleTopDeckRequest(request), "handleTopDeckRequest");
    }


    protected <T> T secureAction(Supplier<T> supplier, String actionName) {
        CompletableFuture<T> future = CompletableFuture.supplyAsync(supplier);
        try {
            return future.get(250, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new CompetitionException("Thread was interrupted", 3);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof IllegalMoveException) {
                throw new CompetitionException(String.format("Player %s made an illegal move while %s", player.getId(), actionName), 32 + player.getId());
            } else if (e.getCause() instanceof OutOfMemoryError || e.getCause() instanceof StackOverflowError) {
                throw new CompetitionException(String.format("Player %s ran out of memory while %s", player.getId(), actionName), 36 + player.getId());
            } else {
                throw new CompetitionException(String.format("Player %s triggered an unexpected error %s while %s", player.getId(), e.getCause().getMessage(), actionName), 3);
            }
        } catch (TimeoutException e) {
            throw new CompetitionException(String.format("Player %s timed out while %s", player.getId(), actionName), 28 + player.getId());
        }
    }

}
