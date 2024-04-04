package competition;

import dominion.core.initialisation.GameConfiguration;
import dominion.core.initialisation.GameLoader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * The launcher used within the competitionJar for the competition
 */
public class CompetitionLauncher {

    /**
     * Configuration for running the competition from a JAR
     * The expected input is the paths of the 4 agents
     * Assumptions:
     * - The paths exist
     * - The agent has the same name as the filename - the extension
     * - It is safe to execute
     * <p>
     * If the game ran successfully then the results of the game are written as line seperated values
     * If something goes wrong, then a -1 is printed to stdout
     *
     * @args as above
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Provide the paths of the 4 agents that are playing the game");
            return;
        }

        List<String> paths = List.of(args[0], args[1], args[2], args[3]);
        List<String> controllerNames = paths.stream().map(CompetitionLauncher::extractFileName).toList();

        CompetitionLauncher competitionLauncher = new CompetitionLauncher();
        try {
            List<Integer> results = competitionLauncher.run(paths, controllerNames);
            for (Integer points : results) {
                System.out.println(points);
            }
        } catch (CompetitionException e) {
            System.exit(e.getExitCode());
        } catch (Exception e) {
            System.exit(3);
        }
    }

    /**
     * Gets the filename / classname from the agent from the path that is provided
     * Uses the same assumptions as {@link CompetitionLauncher#main}
     *
     * @param filePath The path of the agent
     * @return The expected name of the agent
     */
    private static String extractFileName(String filePath) {
        Path path = Paths.get(filePath);
        Path fileNamePath = path.getFileName();
        String fileName = fileNamePath.toString();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileName.substring(0, lastDotIndex);
        } else {
            return fileName;
        }
    }

    /**
     * Creates the initialization manager and starts the initialization process
     */
    private List<Integer> run(List<String> paths, List<String> playerControllers) {

        GameConfiguration configuration = new GameConfiguration();
        configuration.setGames(1);

        GameLoader gameLoader = new GameLoader(
                new CompetitionPlayerLoader(configuration,
                        paths,
                        playerControllers));
        gameLoader.loadGame();
        return gameLoader.startGame();
    }

}
