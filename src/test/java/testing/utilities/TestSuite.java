package testing.utilities;

import dominion.core.geb.GameEventBus;
import dominion.core.player.Entity.Player;
import dominion.core.player.Entity.PlayerDeck;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.state.EndGameObserver;
import dominion.core.state.KingdomManager;
import dominion.core.state.RoundRobinManager;
import dominion.core.state.TurnManager;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    static void setupSingletonMocks() {

        MockedStatic<GameEventBus> gameEventBusMockedStatic = mockStatic(GameEventBus.class);
        gameEventBusMockedStatic.when(GameEventBus::getInstance).thenReturn(mockGameEventBus);
        MockedStatic<RequestForActionRouter> requestForActionRouterMockedStatic = mockStatic(RequestForActionRouter.class);
        requestForActionRouterMockedStatic.when(RequestForActionRouter::getInstance).thenReturn(mockRequestForActionRouter);
        MockedStatic<EndGameObserver> endGameObserverMockedStatic = mockStatic(EndGameObserver.class);
        endGameObserverMockedStatic.when(EndGameObserver::getInstance).thenReturn(mockEndGameObserver);
        MockedStatic<KingdomManager> kingdomManagerMockedStatic = mockStatic(KingdomManager.class);
        kingdomManagerMockedStatic.when(KingdomManager::getInstance).thenReturn(mockKingdomManager);
        MockedStatic<RoundRobinManager> roundRobinManagerMockedStatic = mockStatic(RoundRobinManager.class);
        roundRobinManagerMockedStatic.when(RoundRobinManager::getInstance).thenReturn(mockRoundRobinManager);
        MockedStatic<TurnManager> turnManagerMockedStatic = mockStatic(TurnManager.class);
        turnManagerMockedStatic.when(TurnManager::getInstance).thenReturn(mockTurnManager);
    }

    @BeforeEach
    void reset() {
        mockGameEventBus = mock(GameEventBus.class);
        mockRequestForActionRouter = mock(RequestForActionRouter.class);
        mockEndGameObserver = mock(EndGameObserver.class);
        mockKingdomManager = mock(KingdomManager.class);
        mockRoundRobinManager = mock(RoundRobinManager.class);
        mockTurnManager = mock(TurnManager.class);
        mockPlayer = mock(Player.class);
        mockPlayerDeck = mock(PlayerDeck.class);

        when(mockPlayer.getDeck()).thenReturn(mockPlayerDeck);
    }

}
