package competition;

import dominion.core.initialisation.GameConfiguration;
import dominion.core.initialisation.GameConfigurationManager;
import dominion.core.initialisation.GameLoader;

import java.util.List;

public class CompetitionLauncher {

    public static void main(String[] args) {
        CompetitionLauncher competitionLauncher = new CompetitionLauncher();
        List<Integer> results = competitionLauncher.run(List.of("DefaultController", "DefaultController", "DefaultController", "DefaultController"));
        System.out.println(results != null ? results : -1);
    }

    /**
     * Creates the initialization manager and starts the initialization process
     */
    private List<Integer> run(List<String> playerControllers) {

        GameConfiguration configuration = new GameConfiguration();
        configuration.setPlayerCount(4);
        configuration.setPlayerControllers(playerControllers);
        configuration.setGames(1);

        GameConfigurationManager manager = new CompetitionConfigurationManager(configuration);
        GameLoader gameLoader = new GameLoader();
        gameLoader.loadGame(manager.getConfiguration());
        return gameLoader.startGame();
    }

}
