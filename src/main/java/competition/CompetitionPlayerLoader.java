package competition;

import api.agent.ActionController;
import dominion.core.initialisation.GameConfiguration;
import dominion.core.player.Entity.Player;
import dominion.core.player.controller.PlayerController;
import dominion.core.player.loader.PlayerLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration Loader for the competition
 */
public class CompetitionPlayerLoader implements PlayerLoader {

    GameConfiguration configuration;

    String path;

    List<String> controllerNames;

    /**
     * Default Constructor
     *
     * @param configuration   The configuration file of the game
     * @param path            The storage path of the agents
     * @param controllerNames The names of the controllers to compile
     */
    public CompetitionPlayerLoader(GameConfiguration configuration, String path, List<String> controllerNames) {
        this.path = path;
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
            Player player = new Player(controllerNames.get(i));
            new PlayerController(player, loadController(controllerNames.get(i)));
            players.add(player);
        }
        return players;
    }

    /**
     * Compiles the controllers of the players
     *
     * @param controllerName The name of the controller to compile
     * @return The Compiled ActionController
     */
    public ActionController loadController(String controllerName) {
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int result = compiler.run(null, null, null, String.format("%s%s.java", path, controllerName));
            if (result != 0) {
                throw new RuntimeException(String.format("Failed to load Player Controller %s", controllerName));
            }
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(path).toURI().toURL()});
            Class<?> clazz = Class.forName("api.agent.DefaultController", true, classLoader);
            return (ActionController) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to load Player Controller %s: %s", controllerName, e.getMessage()));
        }
    }
}
