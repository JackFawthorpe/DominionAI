package dominion.card.base;

import dominion.card.CardType;
import dominion.core.rfa.request.DiscardFromHandRequest;
import dominion.core.rfa.request.DrawCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testing.utilities.BaseTestFixture;

import java.util.List;

import static org.mockito.Mockito.*;

public class VassalTest extends BaseTestFixture {

    Vassal vassal;

    @BeforeEach
    void reset() {
        vassal = new Vassal(mockPlayer);
    }

    @Test
    void playCardHook_drawsAction() {

        doAnswer(invocation -> {
            DrawCardRequest request = invocation.getArgument(0);
            request.setResponse(List.of(mockCard));
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(DrawCardRequest.class));

        when(mockCard.isType(CardType.ACTION)).thenReturn(true);

        vassal.playCardHook();

        verify(mockCard, Mockito.times(1)).playCard();
        verify(mockRequestForActionRouter, times(0)).requestAction(isA(DiscardFromHandRequest.class));
        verify(mockRequestForActionRouter, times(1)).requestAction(isA(DrawCardRequest.class));
    }

    @Test
    void playCardHook_drawsNonAction() {

        doAnswer(invocation -> {
            DrawCardRequest request = invocation.getArgument(0);
            request.setResponse(List.of(mockCard));
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(DrawCardRequest.class));

        when(mockCard.isType(CardType.ACTION)).thenReturn(false);

        vassal.playCardHook();

        verify(mockCard, Mockito.times(0)).playCard();
        verify(mockRequestForActionRouter, times(2)).requestAction(any());
    }

    @Test
    void playCardHook_failsToDraw() {

        doAnswer(invocation -> {
            DrawCardRequest request = invocation.getArgument(0);
            request.setResponse(List.of());
            return null;
        }).when(mockRequestForActionRouter).requestAction(isA(DrawCardRequest.class));

        Assertions.assertThrows(RuntimeException.class, () -> vassal.playCardHook());

        verify(mockCard, Mockito.times(0)).playCard();
        verify(mockRequestForActionRouter, times(1)).requestAction(any());
    }
}
