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
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author aluno
 */
public class Reports implements Initializable {
    
    @FXML 
    private ComboBox TxtInterval;
    @FXML
    private PieChart PieReport;
    @FXML
    private DatePicker TxtInterval_Date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    TxtInterval.getItems().removeAll(TxtInterval.getItems());
    TxtInterval.getItems().addAll("Diário", "Mensal", "Anual");
    FillPie();
    }      
    
    
     
    private void FillPie() {
        PrjIdBO b = new PrjIdBO();
                ObservableList<PieChart.Data> pieChartData = b.getPie();
                        
        PieReport.setData(pieChartData);
        PieReport.setTitle("Gastos");

        
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
