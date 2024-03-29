package statistics.controller;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import statistics.collectors.PointsDatasetCollector;
import statistics.collectors.WinDatasetCollector;


public class BaseController {

    @FXML
    private Tab winsTab;

    @FXML
    private Tab pointsTab;

    public void initialize() {
        initializeWinsTab();
        initializePointsTab();
    }

    private void initializeWinsTab() {
        JFreeChart chart = ChartFactory.createBarChart(
                "Wins",
                "Player",
                "Wins", WinDatasetCollector.getInstance().getDataset());
        insertChartIntoPane(chart, winsTab);
    }

    private void initializePointsTab() {
        JFreeChart chart = ChartFactory.createBarChart(
                "Average Points per Game",
                "Player",
                "Points", PointsDatasetCollector.getInstance().getDataset());
        insertChartIntoPane(chart, pointsTab);
    }

    private void insertChartIntoPane(JFreeChart chart, Tab tab) {
        // Create a ChartPanel from the provided chart
        ChartPanel chartPanel = new ChartPanel(chart);

        // Embed the ChartPanel within a SwingNode
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);

        // Set the content of the Tab to the SwingNode
        tab.setContent(swingNode);
    }
}