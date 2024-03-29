package dominion.card.base;

import dominion.card.supply.Copper;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.rfa.request.DrawCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import testing.utilities.BaseTestFixture;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class CellarTest extends BaseTestFixture {

    Cellar cellar;

    @BeforeEach
    void reset() {
        cellar = new Cellar(mockPlayer);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5})
    void playCardHook(int discardCount) {

        final int[] count = {discardCount};

        doAnswer(invocation -> {
            if (count[0] > 0) {
                DiscardFromHandRequest request = invocation.getArgument(0);
                request.setResponse(new Copper(mockPlayer));
                count[0]--;
            } else {
                mockDrawCardRequest.setResponse(null);
            }
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(DiscardFromHandRequest.class));


        when(mockPlayer.isHandEmpty()).thenReturn(false);

        cellar.playCardHook();

        ArgumentCaptor<ControllerActionRequest> actionRequests = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(discardCount == 0 ? 2 : discardCount + 2)).requestAction(actionRequests.capture());
        if (discardCount != 0) {
            List<ControllerActionRequest> requests = actionRequests.getAllValues();
            DrawCardRequest request = (DrawCardRequest) requests.get(discardCount + 1);
            Assertions.assertEquals(discardCount, request.getDrawCount());
        }
    }
}
