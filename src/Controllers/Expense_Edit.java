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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import objects.Expense;

/**
 * FXML Controller class
 *
 * @author MSB
 */
public class Expense_Edit implements Initializable {
@FXML
  private TableColumn<Expense, String> columnDesc;
  @FXML
  private TableColumn<Expense, String> columnPrice;
  @FXML
  private TableColumn<Expense, String> columnDate;
  @FXML
  private TableColumn<Expense, String> columnEstablishment;
  @FXML
  private TableColumn<Expense, String> columnCategory;
  @FXML
  private TableView<Expense> TableExpenses;
  
  private ObservableList<Expense> data;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          preencherTable();
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
    
    

    public void preencherTable()
    {
        data = FXCollections.observableArrayList();
        data =  new PrjIdBO().buscarExpense();
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnEstablishment.setCellValueFactory(new PropertyValueFactory<>("Establishment_Name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        
        TableExpenses.setItems(null);
        TableExpenses.setItems( data);
    } 
    
}
