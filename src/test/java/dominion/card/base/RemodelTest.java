package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.card.supply.Copper;
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

class RemodelTest extends BaseTestFixture {

    Remodel remodel;

    @BeforeEach
    void reset() {
        remodel = new Remodel(mockPlayer);
    }

    @Test
    void playCardHook() {

        Card toTrash = new Copper(mockPlayer);
        // Make hand have a size of four
        when(mockPlayerDeck.getHand()).thenReturn(List.of(toTrash));


        doAnswer(invocation -> {
            TrashCardRequest request = invocation.getArgument(0);
            request.setResponse(toTrash);
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(TrashCardRequest.class));

        remodel.playCardHook();

        ArgumentCaptor<ControllerActionRequest> actionRequests = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(2)).requestAction(actionRequests.capture());
        List<ControllerActionRequest> requests = actionRequests.getAllValues();
        Assertions.assertEquals(2, requests.size());
        Assertions.assertEquals(mockPlayer, requests.get(0).getPlayer());
        Assertions.assertEquals(toTrash, requests.get(0).getResponse());
        Assertions.assertTrue(requests.get(0) instanceof TrashCardRequest);
        Assertions.assertTrue(requests.get(0).isRequired());

        Assertions.assertTrue(requests.get(1) instanceof GainCardRequest);
        GainCardRequest request = (GainCardRequest) requests.get(1);
        CardSpecification cardSpec = request.getCardSpecification();
        Card affordable = new Cellar(mockPlayer); // costs 2
        Card tooexpensive = new Merchant(mockPlayer); // costs 3
        List<Card> toTest = List.of(affordable, tooexpensive);
        List<Card> expected = List.of(affordable);
        Assertions.assertEquals(expected, cardSpec.filterCards(toTest));
    }
}
