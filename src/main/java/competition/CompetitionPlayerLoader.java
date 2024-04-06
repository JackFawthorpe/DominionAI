package competition;

import api.agent.ActionController;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.player.Entity.Player;
import dominion.core.player.loader.PlayerLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Loader for the competition
 */
public class CompetitionPlayerLoader implements PlayerLoader {

    GameConfiguration configuration;

    List<String> paths;

    List<String> controllerNames;

    /**
     * Default Constructor
     *
     * @param configuration   The configuration file of the game
     * @param paths           The storage paths of the agents
     * @param controllerNames The names of the controllers to compile
     */
    public CompetitionPlayerLoader(GameConfiguration configuration, List<String> paths, List<String> controllerNames) {
        this.paths = paths;
        this.configuration = configuration;
        this.controllerNames = controllerNames;
    }

    /**
     * Creates the players for the single game
     *
     * @return The list of player objects
     */
    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Player player = new Player(controllerNames.get(i), i);
            new SecurePlayerController(player, loadController(paths.get(i), controllerNames.get(i), i + 1));
            players.add(player);
        }
        return players;
    }

    /**
     * Compiles the controllers of the players
     *
     * @param pathString     The location of the class to load
     * @param controllerName The name of the controller to compile
     * @return The Compiled ActionController
     */
    public ActionController loadController(String pathString, String controllerName, int playerIndex) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationExitCode = compiler.run(null, null, null, pathString);

        if (compilationExitCode != 0) {
            throw new CompetitionException(String.format("Failed to load player %s", playerIndex), playerIndex + 4);
        }

        PlayerClassLoader classLoader = new PlayerClassLoader();
        String modifiedClassName = String.format("api.agent.%s", controllerName); // Modified package and class name
        String classPathString = pathString.replace(".java", ".class"); // Path to the modified .class file

        // Load the modified class
        try {
            Class<?> modifiedClass = classLoader.loadClassFromFile(modifiedClassName, classPathString);
            return (ActionController) modifiedClass.getDeclaredConstructor().newInstance();
        } catch (CompetitionException e) {
            throw e;
        } catch (ClassCastException e) {
            throw new CompetitionException(String.format("Player %s doesnt implement ActionController", playerIndex), 16 + playerIndex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CompetitionException(String.format("Failed to load class for player %s", playerIndex), playerIndex + 8);
        }
    }
}