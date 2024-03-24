package dominion.card;

import dominion.card.supply.Copper;
import dominion.core.geb.event.PlayCardEvent;
import dominion.core.rfa.request.DrawCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import testing.utilities.BaseTestFixture;

import static org.mockito.Mockito.*;

class CardTest extends BaseTestFixture {

    Card spiedCard;

    @BeforeEach
    void reset() {
        spiedCard = spy(new Copper(mockPlayer));
    }

    @Test
    void playCard_updatesPlayerResources_TakesFromCard() {
        spiedCard.withMoney(10);
        spiedCard.withActions(10);
        spiedCard.withBuys(10);
        spiedCard.playCard();

        verify(mockPlayer, times(1)).updateTurnResources(10, 10, 10);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void playCard_withSimpleDraws_AsksPlayerToDrawCard(boolean toDraw) {
        spiedCard.withSimpleDraw(toDraw ? 1 : 0);
        spiedCard.playCard();

        verify(mockRequestForActionRouter, times(toDraw ? 1 : 0)).requestAction(isA(DrawCardRequest.class));
    }

    @Test
    void playCard_firesPlayedCardEventForCard() {
        ArgumentCaptor<PlayCardEvent> captor = ArgumentCaptor.forClass(PlayCardEvent.class);

        spiedCard.playCard();

        verify(mockGameEventBus, times(1)).notifyListeners(captor.capture());

        Assertions.assertEquals(captor.getValue().getCard(), spiedCard);
    }

    @Test
    void cloneCard_clonesCard_ModificationOfEitherHasNoEffectOnTheOther() {
        spiedCard.withMoney(100);

        Card clonedCard = spiedCard.clone();
        spiedCard.withMoney(101);
        Assertions.assertNotEquals(clonedCard.getMoney(), spiedCard.getMoney());
        clonedCard.withMoney(102);
        Assertions.assertNotEquals(clonedCard.getMoney(), spiedCard.getMoney());
    }

    @Test
    void cloneCard_newCardHasNoOwnerByDefault() {
        Card clonedCard = spiedCard.clone();
        Assertions.assertNull(clonedCard.getPlayer());
    }
}
