package testing.utilities;

import dominion.core.player.Entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class TestSuite {

    public Player mockPlayer;

    @BeforeEach
    void reset() {
        mockPlayer = Mockito.mock(Player.class);
    }

}
