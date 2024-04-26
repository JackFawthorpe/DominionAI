package dominion.core.geb;

import dominion.core.geb.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.utilities.BaseTestFixture;

import static org.mockito.Mockito.*;

public class GameEventBusTest extends BaseTestFixture {


    public GameEventBus gameEventBus;

    @BeforeEach
    void removeGameEventBusMock() {
        gameEventBusMockedStatic.close();
        gameEventBus = GameEventBus.getInstance();
    }

    @Test
    void gameReset_onlyRemovesGameScopedListeners() {

        GameEventListener<CardDrawnEvent> gameListener = mock(GameEventListener.class);
        GameEventListener<CardDrawnEvent> simulationListener = mock(GameEventListener.class);

        when(gameListener.getScope()).thenReturn(ListenScope.GAME);
        when(simulationListener.getScope()).thenReturn(ListenScope.SIMULATION);
        gameEventBus.addListener(CardDrawnEvent.class,gameListener);
        gameEventBus.addListener(CardDrawnEvent.class,simulationListener);

        GameEventBus.gameReset();

        gameEventBus.notifyListeners(mock(CardDrawnEvent.class));

        verify(gameListener, times(0)).handleEvent(any());
        verify(simulationListener, times(1)).handleEvent(any());
    }


    @Test
    void addListener_addsListenerToList() {

        GameEventListener<AttackReactionEvent> gameListener = mock(GameEventListener.class);

        gameEventBus.addListener(AttackReactionEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(AttackReactionEvent.class));

        verify(gameListener, times(1)).handleEvent(isA(AttackReactionEvent.class));
    }

    @Test
    void addListener_noDuplicates() {

        GameEventListener<CardDiscardedEvent> gameListener = mock(GameEventListener.class);

        gameEventBus.addListener(CardDiscardedEvent.class, gameListener);
        gameEventBus.addListener(CardDiscardedEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(CardDiscardedEvent.class));

        verify(gameListener, times(1)).handleEvent(isA(CardDiscardedEvent.class));
    }

    @Test
    void removeListener_noLongerListens() {

        GameEventListener<GameCompleteEvent> gameListener = mock(GameEventListener.class);

        gameEventBus.addListener(GameCompleteEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(GameCompleteEvent.class));

        gameEventBus.removeListener(GameCompleteEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(GameCompleteEvent.class));

        verify(gameListener, times(1)).handleEvent(isA(GameCompleteEvent.class));
    }

    @Test
    void removeListener_silentlyFailsIfNotInList() {

        GameEventListener<PlayCardEvent> gameListener = mock(GameEventListener.class);

        gameEventBus.addListener(PlayCardEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(PlayCardEvent.class));

        gameEventBus.removeListener(PlayCardEvent.class, gameListener);
        gameEventBus.notifyListeners(mock(PlayCardEvent.class));

        verify(gameListener, times(1)).handleEvent(isA(PlayCardEvent.class));
    }



}
