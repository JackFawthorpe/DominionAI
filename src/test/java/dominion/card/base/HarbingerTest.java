package dominion.card.base;

import dominion.core.player.Entity.DeckPosition;
import dominion.core.rfa.request.TopDeckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import testing.utilities.TestSuite;

import static org.mockito.Mockito.verify;

class HarbingerTest extends TestSuite {

    Harbinger harbinger;

    @BeforeEach
    void reset() {
        harbinger = new Harbinger(mockPlayer);
    }

    @Test
    void playCardHook_FiresRequest() {
        ArgumentCaptor<TopDeckRequest> captor = ArgumentCaptor.forClass(TopDeckRequest.class);

        harbinger.playCardHook();

        verify(mockRequestForActionRouter, Mockito.times(1)).requestAction(captor.capture());

        TopDeckRequest result = captor.getValue();

        Assertions.assertEquals(mockPlayer, result.getPlayer());
        Assertions.assertEquals(DeckPosition.DISCARD, result.getPosition());
        Assertions.assertFalse(result.isRequired());
    }
}
