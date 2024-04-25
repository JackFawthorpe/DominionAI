package api;

import dominion.core.state.RoundRobinManager;

/**
 * API for determining what round it is
 */
public class RoundAPI {


    /**
     * Usage: TurnAPI.getRoundNumber()
     * Returns the round number of the current round.
     * <p>
     * A round is everyone having a single turn
     */
    public static int getRoundNumber() {
        return RoundRobinManager.getInstance().getRoundNumber();
    }
}
