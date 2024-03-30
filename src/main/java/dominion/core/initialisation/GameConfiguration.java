package dominion.core.initialisation;

/**
 * POJO for the Configuration required to set up the game
 */
public class GameConfiguration {
    private boolean statisticsEnabled;
    private int games;

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
