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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.Expense;

/**
 * FXML Controller class
 *
 * @author MSB
 */
public class Expense_Add implements Initializable {

@FXML
 //   private TextField TxtDescription; No FXML, Description é um TextArea, não TextField.
    private TextArea TxtDescription;
@FXML
    private TextField TxtPrice;
@FXML
private DatePicker TxtExpense_Date;
  @FXML
  private TextField TxtEstablishment_Name;
  @FXML
  private TextField TxtCity;
  @FXML 
  private ComboBox TxtCategory;
 @FXML 
  private ComboBox TxtEstablishment_Nature;
 @FXML 
  private ComboBox TxtState;
 @FXML
 private ComboBox TxtFrequency;
 @FXML
 private ComboBox TxtPayment_Method;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void BtnSave(ActionEvent event) throws IOException{
        Expense e = new Expense();
        PrjIdBO b = new PrjIdBO();

        
        
        e.setCod_User(Login.session);
        e.setDescription(TxtDescription.getText());
        e.setCity(TxtCity.getText());
        e.setEstablishment_Name(TxtEstablishment_Name.getText());
        e.setExpense_Date(Date.valueOf( TxtExpense_Date.getValue()));
        e.setState(TxtState.getSelectionModel().getSelectedItem().toString());
        e.setCategory(TxtCategory.getSelectionModel().getSelectedItem().toString());
        e.setEstablishment_Nature(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString());
        e.setFrequency(TxtFrequency.getSelectionModel().getSelectedItem().toString());
        e.setPayment_Method(TxtPayment_Method.getSelectionModel().getSelectedItem().toString());
        
        
        b.saveExpense(e);
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
