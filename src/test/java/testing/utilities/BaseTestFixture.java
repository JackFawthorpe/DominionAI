package testing.utilities;

import api.agent.ActionController;
import dominion.card.Card;
import dominion.card.CardSpecification;
import dominion.core.geb.GameEventBus;
import dominion.core.player.Entity.Player;
import dominion.core.player.Entity.PlayerDeck;
import dominion.core.player.controller.PlayerController;
import dominion.core.player.controller.PlayerControllerImpl;
import dominion.core.rfa.RequestForActionRouter;
import dominion.core.rfa.request.DrawCardRequest;
import dominion.core.state.EndGameObserver;
import dominion.core.state.KingdomManager;
import dominion.core.state.RoundRobinManager;
import dominion.core.state.TurnManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BaseTestFixture {

    public static GameEventBus mockGameEventBus;
    public static RequestForActionRouter mockRequestForActionRouter;
    public static EndGameObserver mockEndGameObserver;
    public static KingdomManager mockKingdomManager;
    public static RoundRobinManager mockRoundRobinManager;
    public static TurnManager mockTurnManager;
    public Player mockPlayer;
    public Player mockPlayer2;
    public PlayerDeck mockPlayerDeck;
    public Card mockCard;
    public ActionController mockActionController;
    public CardSpecification mockCardSpecification;

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

        mockCardSpecification = mock(CardSpecification.class);
        mockPlayer = mock(Player.class);
        mockPlayer2 = mock(Player.class);
        mockPlayerDeck = mock(PlayerDeck.class);
        mockPlayerController = mock(PlayerControllerImpl.class);
        mockActionController = mock(ActionController.class);
        mockDrawCardRequest = mock(DrawCardRequest.class);
        mockCard = mock(Card.class);

        when(mockPlayer.getDeck()).thenReturn(mockPlayerDeck);

        when(mockPlayerDeck.getCards(any(), any())).thenReturn(new ArrayList<>());

        when(mockDrawCardRequest.getPlayer()).thenReturn(mockPlayer);
        when(mockDrawCardRequest.getDrawCount()).thenReturn(1);
        when(mockDrawCardRequest.isAttack()).thenReturn(false);

        when(mockCardSpecification.filterCards(any())).thenAnswer(invocation -> invocation.getArgument(0));

        when(mockRoundRobinManager.getPlayers()).thenReturn(List.of(mockPlayer, mockPlayer2));
    }

}
