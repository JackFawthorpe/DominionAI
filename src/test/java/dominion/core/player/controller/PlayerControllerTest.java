package dominion.core.player.controller;

import dominion.card.Card;
import dominion.core.exception.IllegalMoveException;
import dominion.core.player.Entity.DeckPosition;
import dominion.core.rfa.ControllerActionRequest;
import dominion.core.rfa.request.MoveCardRequest;
import dominion.core.rfa.request.TopDeckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testing.utilities.TestSuite;

import java.util.List;

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

    @Test
    void topDeckRequest_FiltersCardsAccordingToSpecification() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                true,
                DeckPosition.HAND,
                mockCardSpecification
                );

        playerController.handleAction(request);

        verify(mockPlayerDeck, Mockito.times(1)).getCards(DeckPosition.HAND, mockCardSpecification);
    }

    @Test
    void topDeckRequest_PlayerChooseNullWhenNotRequired() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                false,
                DeckPosition.HAND,
                mockCardSpecification
        );
        doReturn(null).when(playerController).chooseTopDeckHook(any(), anyBoolean());
        playerController.handleAction(request);
        Assertions.assertNull(request.getResponse());
    }

    @Test
    void topDeckRequest_NonRequiredNoOptionsThereforeNull_NoError() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                true,
                DeckPosition.HAND,
                mockCardSpecification
        );
        doReturn(null).when(playerController).chooseTopDeckHook(any(), anyBoolean());
        Assertions.assertDoesNotThrow(() -> playerController.handleAction(request));
    }

    @Test
    void topDeckRequest_NonRequiredNoWithOptions_Error() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                true,
                DeckPosition.HAND,
                mockCardSpecification
        );
        doReturn(List.of(mockCard)).when(mockPlayerDeck).getCards(any(), any());
        doReturn(null).when(playerController).chooseTopDeckHook(any(), anyBoolean());
        Assertions.assertThrows(IllegalMoveException.class, () -> playerController.handleAction(request));
    }

    @Test
    void topDeckRequest_SavesCardResponse() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                true,
                DeckPosition.HAND,
                mockCardSpecification
        );
        doReturn(List.of(mockCard)).when(mockPlayerDeck).getCards(any(), any());
        doReturn(mockCard).when(playerController).chooseTopDeckHook(any(), anyBoolean());
        doReturn(true).when(mockPlayerDeck).moveCard(any(), any(), any());
        playerController.handleAction(request);
        Assertions.assertEquals(mockCard, request.getResponse());
        verify(mockPlayerDeck, Mockito.times(1)).moveCard(mockCard, DeckPosition.HAND, DeckPosition.DRAW);
    }

    @Test
    void topDeckRequest_ThrowsIllegalMoveIfCardWasntInPile() {
        ControllerActionRequest<Card> request = new TopDeckRequest(
                mockPlayer,
                true,
                DeckPosition.HAND,
                mockCardSpecification
        );
        doReturn(List.of(mockCard)).when(mockPlayerDeck).getCards(any(), any());
        doReturn(mockCard).when(playerController).chooseTopDeckHook(any(), anyBoolean());
        doReturn(false).when(mockPlayerDeck).moveCard(any(), any(), any());
        Assertions.assertThrows(IllegalMoveException.class, () -> playerController.handleAction(request));
        verify(mockPlayerDeck, Mockito.times(1)).moveCard(mockCard, DeckPosition.HAND, DeckPosition.DRAW);
    }
}
