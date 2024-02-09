package dominion.core.geb.event;

import dominion.core.player.Entity.Player;

/**
 * Event to represent the reaction a player may have to an attack
 *
 * @see {@link dominion.card.base.Moat}
 */
public class AttackReactionEvent implements GameEvent {

    private final Player player;
    private boolean toContinue;

    public AttackReactionEvent(Player player) {
        this.player = player;
        this.toContinue = true;
    }

    public boolean isToContinue() {
        return toContinue;
    }

    public void setToContinue(boolean toContinue) {
        this.toContinue = toContinue;
    }

    public Player getPlayer() {
        return player;
    }
}
