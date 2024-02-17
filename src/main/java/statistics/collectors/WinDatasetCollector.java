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

public class WinDatasetCollector implements DatasetCollector<DefaultCategoryDataset>, GameEventListener<GameCompleteEvent> {

    private static final Logger logger = LogManager.getLogger(WinDatasetCollector.class);

    private static WinDatasetCollector instance;
    private final Map<String, Integer> winsPerPlayer;

    private WinDatasetCollector() {
        this.winsPerPlayer = new HashMap<>();
    }

    public static WinDatasetCollector getInstance() {
        if (instance == null) {
            instance = new WinDatasetCollector();
        }
        return instance;
    }

    public void enable() {
        GameEventBus.getInstance().addListener(GameCompleteEvent.class, this);
    }

    public DefaultCategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : winsPerPlayer.entrySet()) {
            String player = entry.getKey();
            int wins = entry.getValue();
            dataset.addValue(wins, "Wins", player);
        }

        return dataset;
    }

    @Override
    public void handleEvent(GameCompleteEvent event) {
        List<Player> players = event.getPlayers();

        players.stream().map(Player::getName).forEach(name -> {
            if (!winsPerPlayer.containsKey(name)) {
                winsPerPlayer.put(name, 0);
            }
        });

        int maxPoints = players.stream()
                .mapToInt(Player::getPoints)
                .max()
                .orElse(0);

        List<String> winners = players.stream()
                .filter(player -> player.getPoints() == maxPoints)
                .map(Player::getName)
                .toList();

        for (String winner : winners) {
            winsPerPlayer.put(winner, winsPerPlayer.getOrDefault(winner, 0) + 1);
            logger.info("Added win to player {}, they now have {} wins", winner, winsPerPlayer.get(winner));
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
