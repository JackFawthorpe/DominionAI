package dominion.core.initialisation;

import java.util.List;

/**
 * POJO for the Configuration required to set up the game
 */
public class GameConfiguration {
    private int playerCount;
    private List<String> kingdomCards;
    private boolean statisticsEnabled;

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<String> getKingdomCards() {
        return kingdomCards;
    }

    public void setKingdomCards(List<String> cards) {
        this.kingdomCards = cards;
    }

    public boolean isStatisticsEnabled() {
        return statisticsEnabled;
    }

    public void setStatisticsEnabled(boolean statisticsEnabled) {
        this.statisticsEnabled = statisticsEnabled;
    }
}
