package dominion.card.base;

import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.DiscardFromHandRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import testing.utilities.BaseTestFixture;

import java.util.List;

import static org.mockito.Mockito.*;

public class MilitiaTest extends BaseTestFixture {


    Militia militia;

    @BeforeEach
    void reset() {
        militia = new Militia(mockPlayer);
    }

    @Test
    void playCardHook_StopsFiringEventWhenNullDiscardIsReceived() {

        doReturn(List.of(mockPlayer, mockPlayer2)).when(mockRoundRobinManager).getPlayers();

        // Make hand have a size of four
        when(mockPlayer2.getDeck()).thenReturn(mockPlayerDeck);
        when(mockPlayerDeck.getHand()).thenReturn(List.of(new Cellar(null), new Cellar(null), new Cellar(null), new Cellar(null)));

        doAnswer(invocation -> {
            DiscardFromHandRequest request = invocation.getArgument(0);
            request.setResponse(null);
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(DiscardFromHandRequest.class));

        militia.playCardHook();

        ArgumentCaptor<ControllerActionRequest> actionRequests = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(1)).requestAction(actionRequests.capture());
        Assertions.assertEquals(mockPlayer2, actionRequests.getValue().getPlayer());
        Assertions.assertTrue(actionRequests.getValue().isRequired());
        Assertions.assertTrue(actionRequests.getValue().isAttack());
    }

    @Test
    void playCardHook_DoesntFireIfPlayerDoesntHaveEnoughCards() {

        doReturn(List.of(mockPlayer, mockPlayer2)).when(mockRoundRobinManager).getPlayers();

        // Make hand have a size of 3
        when(mockPlayer2.getDeck()).thenReturn(mockPlayerDeck);
        when(mockPlayerDeck.getHand()).thenReturn(List.of(new Cellar(null), new Cellar(null), new Cellar(null)));

        militia.playCardHook();

        verify(mockRequestForActionRouter, times(0)).requestAction(any());
    }

}
