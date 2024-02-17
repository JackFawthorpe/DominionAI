package statistics;

import dominion.core.geb.GameEventBus;
import dominion.core.geb.GameEventListener;
import dominion.core.geb.ListenScope;
import dominion.core.geb.event.SimulationCompleteEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import statistics.collectors.PointsDatasetCollector;
import statistics.collectors.WinDatasetCollector;

import java.util.Objects;


public class StatisticsApp extends Application implements GameEventListener<SimulationCompleteEvent> {

    private static final Logger logger = LogManager.getLogger(StatisticsApp.class);

    private static StatisticsApp instance;

    public static StatisticsApp getInstance() {
        if (instance == null) {
            instance = new StatisticsApp();
        }
        return instance;
    }

    /**
     * Function to register this class to display statistics when the simulation is complete
     */
    public void enable() {
        GameEventBus.getInstance().addListener(SimulationCompleteEvent.class, this);
        WinDatasetCollector.getInstance().enable();
        PointsDatasetCollector.getInstance().enable();
    }

    @Override
    public void handleEvent(SimulationCompleteEvent event) {
        logger.info("Starting Statistics View");
        launch();
    }

    @Override
    public String getIdentifier() {
        return "Statistics Module Controller";
    }

    @Override
    public ListenScope getScope() {
        return ListenScope.SIMULATION;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/statistics/Statistics.fxml")));

        // Set up the stage
        primaryStage.setTitle("DominionAI");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
    }

}
