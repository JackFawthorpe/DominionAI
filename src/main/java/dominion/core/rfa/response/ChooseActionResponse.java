package dominion.core.rfa.response;

import dominion.card.Card;

public record ChooseActionResponse(Card card) implements PlayerActionResponse {

}
