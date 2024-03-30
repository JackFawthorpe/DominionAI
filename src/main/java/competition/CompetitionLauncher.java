package competition;

import dominion.core.initialisation.GameConfiguration;
import dominion.core.initialisation.GameLoader;

import java.util.Arrays;
import java.util.List;

/**
 * The launcher used within the competitionJar for the competition
 */
public class CompetitionLauncher {

    /**
     * The args of the function are first, the path of the agents etc /home/user/agent/
     * The next four are the ordered names of the agents
     * <p>
     * If the game ran successfully then a java list of the points is printed to stdout
     * If something goes wrong, then a -1 is printed to stdout
     * If something goes wrong, then a -1 is printed to stdout
     *
     * @args as above
     */
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("The first arg is the path to read agent's from, the following four are the agent names (without .java)");
            return;
        }
        String path = args[0];
        List<String> controllerNames = Arrays.asList(args[1], args[2], args[3], args[4]);
        CompetitionLauncher competitionLauncher = new CompetitionLauncher();
        List<Integer> results = competitionLauncher.run(controllerNames, path);
        System.out.println(results != null ? results : -1);
    }

    /**
     * Creates the initialization manager and starts the initialization process
     */
    private List<Integer> run(List<String> playerControllers, String path) {

        GameConfiguration configuration = new GameConfiguration();
        configuration.setGames(1);

        GameLoader gameLoader = new GameLoader(
                new CompetitionPlayerLoader(configuration,
                        path,
                        playerControllers));
        gameLoader.loadGame();
        return gameLoader.startGame();
    }

}
