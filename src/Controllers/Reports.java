/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author aluno
 */
public class Reports implements Initializable {
    
    @FXML
    private BarChart<String, Number> RepBar;

    @FXML 
    private ComboBox TxtInterval;

    @FXML
    private DatePicker TxtInterval_Date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    TxtInterval.getItems().removeAll(TxtInterval.getItems());
    TxtInterval.getItems().addAll("Diário", "Mensal", "Anual");
    FillBar();
    }      
    
    @FXML
      private void FillBar() {
          PrjIdBO b = new PrjIdBO();
              ObservableList<XYChart.Series<String, Number>> BarData = b.AddBarData();
           RepBar.setData(BarData);
           RepBar.setTitle("Reportagem de gastos");
    } 
     
    
     @FXML
    private void BtnMain(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();        
    }
    
 
    
    
    
    
    
}