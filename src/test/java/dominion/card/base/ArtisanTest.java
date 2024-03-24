package dominion.card.base;

import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.GainCardRequest;
import dominion.core.rfa.request.TopDeckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import testing.utilities.BaseTestFixture;

import static org.mockito.Mockito.times;

class ArtisanTest extends BaseTestFixture {

    Artisan artisan;

    @BeforeEach
    void reset() {
        artisan = new Artisan(mockPlayer);
    }

    @Test
    void playCardHook_CallsRequests() {

        ArgumentCaptor<ControllerActionRequest> captor = ArgumentCaptor.forClass(ControllerActionRequest.class);

        artisan.playCard();

        Mockito.verify(mockRequestForActionRouter, times(2)).requestAction(captor.capture());

        Assertions.assertEquals(2, captor.getAllValues().size());
        Assertions.assertTrue(captor.getAllValues().get(0) instanceof GainCardRequest);
        Assertions.assertTrue(captor.getAllValues().get(1) instanceof TopDeckRequest);
    }
}
