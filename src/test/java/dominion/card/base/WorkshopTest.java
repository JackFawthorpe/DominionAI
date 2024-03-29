package dominion.card.base;

import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.GainCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import testing.utilities.BaseTestFixture;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WorkshopTest extends BaseTestFixture {

    Workshop workshop;

    @BeforeEach
    void reset() {
        workshop = new Workshop(mockPlayer);
    }

    @Test
    void playCardHook() {

        workshop.playCardHook();

        ArgumentCaptor<ControllerActionRequest> actionRequests = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(1)).requestAction(actionRequests.capture());
        List<ControllerActionRequest> requests = actionRequests.getAllValues();
        Assertions.assertEquals(1, requests.size());
        Assertions.assertEquals(mockPlayer, requests.get(0).getPlayer());
        Assertions.assertTrue(requests.get(0) instanceof GainCardRequest);
        GainCardRequest request = (GainCardRequest) requests.get(0);
        Assertions.assertTrue(request.isRequired());

        CardSpecification cardSpec = request.getCardSpecification();
        Card affordable = new Remodel(mockPlayer); // costs 4
        Card tooexpensive = new Festival(mockPlayer); // costs 5
        List<Card> toTest = List.of(affordable, tooexpensive);
        List<Card> expected = List.of(affordable);
        Assertions.assertEquals(expected, cardSpec.filterCards(toTest));
    }
}
