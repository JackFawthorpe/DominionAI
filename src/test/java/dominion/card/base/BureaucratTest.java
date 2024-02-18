package dominion.card.base;

import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TopDeckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import testing.utilities.TestSuite;

import static org.mockito.Mockito.*;

public class BureaucratTest extends TestSuite {

    Bureaucrat bureaucrat;

    @BeforeEach
    void reset() {
        bureaucrat = spy(new Bureaucrat(mockPlayer));
    }

    @Test
    void playCardHook() {
        bureaucrat.playCard();

        ArgumentCaptor<ControllerActionRequest> captor = ArgumentCaptor.forClass(ControllerActionRequest.class);
        verify(mockRequestForActionRouter, times(2)).requestAction(captor.capture());

        Assertions.assertTrue(captor.getAllValues().get(0) instanceof GainCardRequest);
        Assertions.assertEquals(mockPlayer2, captor.getAllValues().get(1).getPlayer());
        Assertions.assertTrue(captor.getAllValues().get(1) instanceof TopDeckRequest);
    }
}
