package dominion.core.rfa.request;

import dominion.core.player.Player;
import dominion.core.rfa.PlayerActionRequestCode;

/**
 * Represents the action of a player picking an action card out of th
 */
public class ChooseActionRequest extends PlayerActionRequest {

    public ChooseActionRequest(Player player) {
        super(PlayerActionRequestCode.PLAY_ACTION_CARD, player, null);
    }
}
