package dominion.core.rfa;

import dominion.core.geb.event.AttackReactionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import testing.utilities.BaseTestFixture;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class RequestForActionRouterTest extends BaseTestFixture {

    public RequestForActionRouter router;

    @BeforeEach
    void removeRFARMock() {
        requestForActionRouterMockedStatic.close();
        router = RequestForActionRouter.getInstance();
    }

    @Test
    void addHandler_RoutesFutureActionsToController() {
        router.addHandler(mockPlayerController, mockPlayer);
        router.requestAction(mockDrawCardRequest);

        Mockito.verify(mockPlayerController, times(1)).handleAction(mockDrawCardRequest);
    }

    @Test
    void requestAction_noController_ReturnsBeforeProcessing() {
        router.requestAction(mockDrawCardRequest);

        Mockito.verify(mockDrawCardRequest, times(0)).isAttack();
    }

    @Test
    void requestAction_SendsEventToController() {
        router.addHandler(mockPlayerController, mockPlayer);
        router.requestAction(mockDrawCardRequest);

        Mockito.verify(mockPlayerController, times(1)).handleAction(mockDrawCardRequest);
    }

    @Test
    void requestAction_withAttackTag_FiresEvent() {
        router.addHandler(mockPlayerController, mockPlayer);
        when(mockDrawCardRequest.isAttack()).thenReturn(true);

        router.requestAction(mockDrawCardRequest);

        Mockito.verify(mockDrawCardRequest, times(1)).isAttack();
        Mockito.verify(mockGameEventBus, times(1)).notifyListeners(isA(AttackReactionEvent.class));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void requestAction_withAttackTag_StopsAttackIfRequested(boolean toContinue) {
        router.addHandler(mockPlayerController, mockPlayer);
        when(mockDrawCardRequest.isAttack()).thenReturn(true);

        doAnswer(invocation -> {
            AttackReactionEvent event = invocation.getArgument(0);
            event.setToContinue(toContinue);
            return event;
        }).when(mockGameEventBus).notifyListeners(isA(AttackReactionEvent.class));

        router.requestAction(mockDrawCardRequest);

        Mockito.verify(mockDrawCardRequest, times(1)).isAttack();
        Mockito.verify(mockGameEventBus, times(1)).notifyListeners(isA(AttackReactionEvent.class));
        Mockito.verify(mockPlayerController, times(toContinue ? 1 : 0)).handleAction(any());
    }
}
