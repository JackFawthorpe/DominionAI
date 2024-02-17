package statistics.collectors;

import dominion.core.geb.GameEventBus;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.ListenScope;
import dominion.core.geb.event.GameCompleteEvent;
import dominion.core.player.Entity.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointsDatasetCollector implements DatasetCollector<DefaultCategoryDataset>, GameEventListener<GameCompleteEvent> {

    private static final Logger logger = LogManager.getLogger(PointsDatasetCollector.class);

    private static PointsDatasetCollector instance;
    private final Map<String, Integer> playerPointAccumulator;
    private int gameCounter;

    private PointsDatasetCollector() {
        this.playerPointAccumulator = new HashMap<>();
        this.gameCounter = 0;
    }

    public static PointsDatasetCollector getInstance() {
        if (instance == null) {
            instance = new PointsDatasetCollector();
        }
        return instance;
    }

    public void enable() {
        GameEventBus.getInstance().addListener(GameCompleteEvent.class, this);
    }

    public DefaultCategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : playerPointAccumulator.entrySet()) {
            String player = entry.getKey();
            int points = entry.getValue() / gameCounter;
            dataset.addValue(points, "Wins", player);
        }

        return dataset;
    }

    @Override
    public void handleEvent(GameCompleteEvent event) {
        gameCounter++;

        List<Player> players = event.getPlayers();

        for (Player player : players) {
            playerPointAccumulator.put(
                    player.getName(),
                    playerPointAccumulator.getOrDefault(player.getName(), 0) + player.getPoints());
        }
    }

    @Override
    public String getIdentifier() {
        return "Win Chart";
    }

    @Override
    public ListenScope getScope() {
        return ListenScope.SIMULATION;
    }
}
