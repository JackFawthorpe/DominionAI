package dominion.core.state;

import dominion.card.supply.Estate;
import dominion.card.supply.Province;
import dominion.core.geb.event.SupplyPileDepletedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testing.utilities.TestSuite;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

class EndGameObserverTest extends TestSuite {


    public EndGameObserver endGameObserver;

    @BeforeEach
    void removeRFARMock() {
        endGameObserverMockedStatic.close();
        EndGameObserver.reset();
        endGameObserver = EndGameObserver.getInstance();
    }

    @Test
    void instantiation_AddsListenerToBus() {
        Mockito.verify(mockGameEventBus, times(1)).addListener(SupplyPileDepletedEvent.class, endGameObserver);
        Assertions.assertFalse(endGameObserver.isGameFinished());
    }

    @Test
    void receivesEvent_ProvinceDepletes_GameEnds() {
        endGameObserver.handleEvent(new SupplyPileDepletedEvent(new Province(mockPlayer)));

        Assertions.assertTrue(endGameObserver.isGameFinished());
    }

    @Test
    void receivesEvent_onlyFinishesAfter3() {
        endGameObserver = spy(endGameObserver);
        endGameObserver.handleEvent(new SupplyPileDepletedEvent(new Estate(mockPlayer)));
        endGameObserver.handleEvent(new SupplyPileDepletedEvent(new Estate(mockPlayer)));

        Assertions.assertFalse(endGameObserver.isGameFinished());

        endGameObserver.handleEvent(new SupplyPileDepletedEvent(new Estate(mockPlayer)));

        Assertions.assertTrue(endGameObserver.isGameFinished());

        Mockito.verify(endGameObserver, times(3)).handleEvent(any());
    }
}
