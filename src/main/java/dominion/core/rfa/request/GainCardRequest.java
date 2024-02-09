package dominion.core.rfa.request;

import dominion.card.Card;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.player.Entity.Player;
import dominion.core.rfa.ControllerActionRequest;

import java.util.List;

/**
 * Action to represent asking the player to gain a card
 */
public class GainCardRequest extends ControllerActionRequest<Card> {

    private final List<Card> gainOptions;
    private final DeckPosition gainPosition;

    public GainCardRequest(Player player, List<Card> gainOptions, DeckPosition gainPosition) {
        super(player, true);
        this.gainOptions = gainOptions;
        this.gainPosition = gainPosition;
    }

    public List<Card> getGainOptions() {
        return gainOptions;
    }

    public DeckPosition getGainPosition() {
        return gainPosition;
    }
}
