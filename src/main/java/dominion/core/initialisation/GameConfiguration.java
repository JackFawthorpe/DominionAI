package dominion.core.initialisation;

import java.util.List;

/**
 * POJO for the Configuration required to set up the game
 */
public class GameConfiguration {
    private int playerCount;
    private boolean statisticsEnabled;
    private int games;
    private List<String> playerControllers;

    public List<String> getPlayerControllers() {
        return playerControllers;
    }

    public void setPlayerControllers(List<String> playerControllers) {
        this.playerControllers = playerControllers;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public boolean isStatisticsEnabled() {
        return statisticsEnabled;
    }

    public void setStatisticsEnabled(boolean statisticsEnabled) {
        this.statisticsEnabled = statisticsEnabled;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}
