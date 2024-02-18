package testing.utilities;

import dominion.core.geb.GameEventBus;
import dominion.core.player.Entity.Player;
import dominion.core.player.Entity.PlayerDeck;
import dominion.core.player.controller.PlayerController;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.DrawCardRequest;
import dominion.core.state.EndGameObserver;
import dominion.core.state.KingdomManager;
import dominion.core.state.RoundRobinManager;
import dominion.core.state.TurnManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class TestSuite {

    public static GameEventBus mockGameEventBus;
    public static RequestForActionRouter mockRequestForActionRouter;
    public static EndGameObserver mockEndGameObserver;
    public static KingdomManager mockKingdomManager;
    public static RoundRobinManager mockRoundRobinManager;
    public static TurnManager mockTurnManager;
    public Player mockPlayer;
    public PlayerDeck mockPlayerDeck;

    public PlayerController mockPlayerController;

    public DrawCardRequest mockDrawCardRequest;

    public MockedStatic<GameEventBus> gameEventBusMockedStatic;
    public MockedStatic<RequestForActionRouter> requestForActionRouterMockedStatic;
    public MockedStatic<EndGameObserver> endGameObserverMockedStatic;
    public MockedStatic<KingdomManager> kingdomManagerMockedStatic;
    public MockedStatic<RoundRobinManager> roundRobinManagerMockedStatic;
    public MockedStatic<TurnManager> turnManagerMockedStatic;

    @BeforeEach
    void setupSingletonMocks() {
        gameEventBusMockedStatic = mockStatic(GameEventBus.class);
        gameEventBusMockedStatic.when(GameEventBus::getInstance).thenReturn(mockGameEventBus);
        requestForActionRouterMockedStatic = mockStatic(RequestForActionRouter.class);
        requestForActionRouterMockedStatic.when(RequestForActionRouter::getInstance).thenReturn(mockRequestForActionRouter);
        endGameObserverMockedStatic = mockStatic(EndGameObserver.class);
        endGameObserverMockedStatic.when(EndGameObserver::getInstance).thenReturn(mockEndGameObserver);
        kingdomManagerMockedStatic = mockStatic(KingdomManager.class);
        kingdomManagerMockedStatic.when(KingdomManager::getInstance).thenReturn(mockKingdomManager);
        roundRobinManagerMockedStatic = mockStatic(RoundRobinManager.class);
        roundRobinManagerMockedStatic.when(RoundRobinManager::getInstance).thenReturn(mockRoundRobinManager);
        turnManagerMockedStatic = mockStatic(TurnManager.class);
        turnManagerMockedStatic.when(TurnManager::getInstance).thenReturn(mockTurnManager);
    }

    @AfterEach
    void tearDown() {
        closeMock(gameEventBusMockedStatic);
        closeMock(requestForActionRouterMockedStatic);
        closeMock(endGameObserverMockedStatic);
        closeMock(kingdomManagerMockedStatic);
        closeMock(roundRobinManagerMockedStatic);
        closeMock(turnManagerMockedStatic);
    }

    private void closeMock(MockedStatic<?> mock) {
        if (mock != null && !mock.isClosed()) {
            mock.close();
        }
    }

    @BeforeEach
    void suite_reset() {
        mockGameEventBus = mock(GameEventBus.class);
        mockRequestForActionRouter = mock(RequestForActionRouter.class);
        mockEndGameObserver = mock(EndGameObserver.class);
        mockKingdomManager = mock(KingdomManager.class);
        mockRoundRobinManager = mock(RoundRobinManager.class);
        mockTurnManager = mock(TurnManager.class);

        mockPlayer = mock(Player.class);
        mockPlayerDeck = mock(PlayerDeck.class);
        mockPlayerController = mock(PlayerController.class);
        mockDrawCardRequest = mock(DrawCardRequest.class);

        when(mockPlayer.getDeck()).thenReturn(mockPlayerDeck);

        when(mockDrawCardRequest.getPlayer()).thenReturn(mockPlayer);
        when(mockDrawCardRequest.getDrawCount()).thenReturn(1);
        when(mockDrawCardRequest.isAttack()).thenReturn(false);
    }

}
