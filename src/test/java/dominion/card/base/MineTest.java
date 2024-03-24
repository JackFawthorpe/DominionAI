package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.supply.Copper;
import dominion.card.supply.Duchy;
import dominion.card.supply.Gold;
import dominion.card.supply.Silver;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TrashCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import testing.utilities.BaseTestFixture;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class MineTest extends BaseTestFixture {
    Mine mine;

    @BeforeEach
    void reset() {
        mine = new Mine(mockPlayer);
    }


    @Test
    void playCardHook_StopsFiringEventWhenNullDiscardIsReceived() {

        // Make hand have a size of four
        when(mockPlayerDeck.getHand()).thenReturn(List.of(new Cellar(null), new Cellar(null), new Cellar(null), new Cellar(null)));

        Card toTrash = new Copper(mockPlayer);

        doAnswer(invocation -> {
            TrashCardRequest request = invocation.getArgument(0);
            request.setResponse(toTrash);
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(TrashCardRequest.class));

        mine.playCardHook();

        ArgumentCaptor<ControllerActionRequest> actionRequests = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(2)).requestAction(actionRequests.capture());
        List<ControllerActionRequest> requests = actionRequests.getAllValues();
        Assertions.assertEquals(2, requests.size());
        Assertions.assertEquals(mockPlayer, requests.get(0).getPlayer());
        Assertions.assertEquals(toTrash, requests.get(0).getResponse());

        Assertions.assertTrue(requests.get(1) instanceof GainCardRequest);
        GainCardRequest request = (GainCardRequest) requests.get(1);
        CardSpecification silverCopperSpec = request.getCardSpecification();
        List<Card> toTest = List.of(new Copper(mockPlayer), new Silver(mockPlayer), new Gold(mockPlayer), new Duchy(mockPlayer));
        List<Card> expected = List.of(new Copper(mockPlayer), new Silver(mockPlayer));
        Assertions.assertEquals(expected, silverCopperSpec.filterCards(toTest));
    }

}
