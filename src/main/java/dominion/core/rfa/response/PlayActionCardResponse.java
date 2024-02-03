package dominion.core.rfa.response;

import dominion.card.Card;

public class PlayActionCardResponse implements PlayerActionResponse<Card> {

    private final Card card;

    public PlayActionCardResponse(Card card) {
        this.card = card;
    }

    @Override
    public Card getResult() {
        return card;
    }
}
