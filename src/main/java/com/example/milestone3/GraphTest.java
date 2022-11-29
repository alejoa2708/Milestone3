package com.example.milestone3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphTest extends Application {
    private BarChart<Number, String> assessedValueChart;
    Button plotAssessedValueButton;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chart Testing");

        VBox mainBox = new VBox(10);

        Scene scene = new Scene(mainBox, 1300, 850);
        stage.setScene(scene);

        ScrollPane scrollPane = new ScrollPane();


        plotAssessedValueButton = new Button("Plot");
        configureAssessedValueGraph();
        assessedValueChart.setMinHeight(22*40);
        assessedValueChart.setMinWidth(1300);
        //scrollPane.setContent(assessedValueChart);
        //scrollPane.prefHeightProperty().bind(scene.heightProperty());
        //assessedValueChart.setMinHeight(20000);

        plotAssessedValueGraph(575001, 750000);

        //plotAssessedValueButton.setOnAction(actionEvent -> plotAssessedValueGraph(125001, 225000));//20000000

        mainBox.getChildren().addAll(assessedValueChart);

        stage.show();

    }

    private void plotAssessedValueGraph(double minRange, double maxRange){ //min = 0, max = 49,525,000 or 19,624,000
        Neighbourhoods neighbourhoods = new Neighbourhoods(); // create the list of neighbourhoods
        int count = 0;
        XYChart.Series<Number, String> series = new XYChart.Series<>();
        for (Neighbourhood neighbourhood : neighbourhoods.getNeighbourhoodsList()) { //create the series with list of AccountEntry
            if (neighbourhood.getAverageAssessedValue() >= minRange && neighbourhood.getAverageAssessedValue() <= maxRange){
                series.getData().add(new XYChart.Data<>(neighbourhood.getAverageAssessedValue(), neighbourhood.getNeighbourhoodName()));
                count ++;
            }

        }
        assessedValueChart.getData().add(series); //add series to bar chart
        System.out.println(count);
    }

    private void configureAssessedValueGraph(){
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Neighbourhood");
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Average Assessed Value");

        assessedValueChart = new BarChart<>(xAxis, yAxis);
        assessedValueChart.setTitle("Average Assessed Value");
        assessedValueChart.setAnimated(false);
        assessedValueChart.setLegendVisible(false);
    }
}