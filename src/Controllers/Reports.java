/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Label;
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
    private Label TxtWarn;
    @FXML
    private DatePicker TxtDate1;
    @FXML
    private DatePicker TxtDate2;
    @FXML
    private Label TxtAte;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        TxtWarn.setVisible(false);
        TxtDate2.setVisible(false);
        TxtAte.setVisible(false);
    FillBar();
    }      
    
    @FXML
    private void Datecheck(){
        if(TxtDate1.getValue() == null){
            TxtDate2.setVisible(false);
            TxtDate2.setValue(null);
            TxtAte.setVisible(false);
        }
        else{
            TxtDate2.setVisible(true);
            TxtAte.setVisible(false);
        }
    }
    @FXML
      private void FillBar() {
          
          //Dependendo se o TxtDate2 e TxtDate1 estão 
          PrjIdBO b = new PrjIdBO();
      
            ObservableList<XYChart.Series<String, Number>> BarData = null;
          if(TxtDate1.getValue() == null && TxtDate2.getValue() == null){
           BarData = b.AddBarData();
          }
          else{
              if(TxtDate1.getValue() != null && TxtDate2.getValue() != null){
                BarData = b.AddBarData(Date.valueOf( TxtDate1.getValue()),Date.valueOf( TxtDate2.getValue()));
              }
              else{
                  if(TxtDate1.getValue()!= null){
                       BarData = b.AddBarData(Date.valueOf( TxtDate1.getValue()));
                       TxtWarn.setVisible(false);
                  }
                  else{
                      TxtWarn.setVisible(true);
                     
                  }
              }
          }
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