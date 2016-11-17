/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;


/**
 * FXML Controller class
 *
 * @author aluno
 */
public class Reports implements Initializable {
    
    @FXML 
    private ComboBox TxtInterval;
    
    @FXML
    private DatePicker TxtInterval_Date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    TxtInterval.getItems().removeAll(TxtInterval.getItems());
    TxtInterval.getItems().addAll("Diário", "Mensal", "Anual");
    }      
}
