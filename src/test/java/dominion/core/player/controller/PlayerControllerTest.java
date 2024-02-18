package dominion.core.player.controller;

import dominion.core.player.Entity.DeckPosition;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.MoveCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testing.utilities.TestSuite;

import static org.mockito.Mockito.*;

public class PlayerControllerTest extends TestSuite {

    PlayerController playerController;

    @BeforeEach
    void reset() {
        playerController = spy(new DefaultController(mockPlayer));
    }

    @Test
    void moveCardRequest_Bluesky() {
        when(mockPlayerDeck.moveCard(any(), any(), any())).thenReturn(true);
        ControllerActionRequest<Void> request = new MoveCardRequest(
                mockPlayer,
                mockCard,
                DeckPosition.HAND,
                DeckPosition.DISCARD);
        playerController.handleAction(request);

        verify(mockPlayerDeck, Mockito.times(1)).moveCard(mockCard, DeckPosition.HAND, DeckPosition.DISCARD);
    }

    @Test
    void moveCardRequest_CardNotInFrom_ThrowsRuntimeError() {
        when(mockPlayerDeck.moveCard(any(), any(), any())).thenReturn(false);
        ControllerActionRequest<Void> request = new MoveCardRequest(
                mockPlayer,
                mockCard,
                DeckPosition.HAND,
                DeckPosition.DISCARD);
        Assertions.assertThrows(RuntimeException.class, () -> playerController.handleAction(request));

        verify(mockPlayerDeck, Mockito.times(1)).moveCard(mockCard, DeckPosition.HAND, DeckPosition.DISCARD);
    }


}
