/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Review;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import sservice.ReviewCRUD;

/**
 *
 * @author asus
 */
public class StartPointChart extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ReviewCRUD pcr = new ReviewCRUD();
        // TODO
        int revcount1=0;
        int revcount2=0;
        int revcount3=0;
        int revcount4=0;
        int revcount5=0;
        
        
       
        
        

        ObservableList<Review> list = pcr.getReview();
        for ( Review x : list){
            switch (x.getRating()) {
                case 1:
                    revcount1+=1;
                    break;
                case 2:
                    revcount2+=1;
                    break;
                case 3:
                    revcount3+=1;
                    break;
                case 4:
                    revcount4+=1;
                    break;
                case 5:
                    revcount5+=1;
                    break;
                default:
                    break;
            }

        }
        //Create Pie chart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("1 star", revcount1),
                            new PieChart.Data("2 star", revcount2),
                            new PieChart.Data("3 star", revcount3),
                            new PieChart.Data("4 star", revcount4),
                            new PieChart.Data("5 star", revcount5)
        );
        //Create PieChart and assign the dataa ~~
        PieChart Chart1 = new PieChart(pieChartData);
        Group root = new Group(Chart1);
        Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("PieChart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
