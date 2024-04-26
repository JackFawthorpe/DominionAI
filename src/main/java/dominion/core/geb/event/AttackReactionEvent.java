package dominion.core.geb.event;

import dominion.core.player.Entity.Player;
import lombok.Getter;
import lombok.Setter;

/**
 * Event to represent the reaction a player may have to an attack
 *
 * @see {@link dominion.card.base.Moat}
 */
@Getter
public class AttackReactionEvent implements GameEvent {

    /**
     * The player that is being attacked
     */
    private final Player player;

    /**
     * A boolean to represent if the attack should continue after the player has reacted to the attack
     */
    @Setter
    private boolean toContinue;

    public AttackReactionEvent(Player player) {
        this.player = player;
        this.toContinue = true;
    }
}
