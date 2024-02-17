package statistics.collectors;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class WinDatasetCollector implements DatasetCollector<DefaultCategoryDataset> {

    private static final Logger logger = LogManager.getLogger(WinDatasetCollector.class);

    private static WinDatasetCollector instance;
    List<Pair<String, Integer>> winsPerPlayer;

    private WinDatasetCollector() {
        this.winsPerPlayer = List.of(
                new Pair<String, Integer>("Jack", 10),
                new Pair<String, Integer>("Lara", 30),
                new Pair<String, Integer>("Matt", 45));
    }

    public static WinDatasetCollector getInstance() {
        if (instance == null) {
            instance = new WinDatasetCollector();
        }
        return instance;
    }

    public DefaultCategoryDataset getDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Pair<String, Integer> player : winsPerPlayer) {
            dataset.addValue(player.getValue(), "Wins", player.getKey());
        }

        return dataset;
    }
}
