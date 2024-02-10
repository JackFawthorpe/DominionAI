package testing.utilities;

import dominion.core.player.Entity.Player;
import dominion.core.player.Entity.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class TestSuite {

    public Player mockPlayer;

    public PlayerDeck mockPlayerDeck;

    @BeforeEach
    void reset() {
        mockPlayer = Mockito.mock(Player.class);
        mockPlayerDeck = Mockito.mock(PlayerDeck.class);
        when(mockPlayer.getDeck()).thenReturn(mockPlayerDeck);
    }

}
