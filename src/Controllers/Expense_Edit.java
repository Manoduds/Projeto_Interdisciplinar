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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
  @FXML
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
  
  private ObservableList<Expense> data;
  
  private Expense e;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           TxtCategory.getItems().removeAll(TxtCategory.getItems());
    TxtCategory.getItems().addAll("Juros", "Despesa", "Salario");
    TxtEstablishment_Nature.getItems().removeAll(TxtEstablishment_Nature.getItems());
    TxtEstablishment_Nature.getItems().addAll("Moradia", "Empresa", "Publico");
    TxtState.getItems().removeAll(TxtState.getItems());
    TxtState.getItems().addAll("SP", "MG", "RJ");
    TxtFrequency.getItems().removeAll(TxtFrequency.getItems());
    TxtFrequency.getItems().addAll("Diaria", "Semanal", "Mensal");
    TxtPayment_Method.getItems().removeAll(TxtPayment_Method.getItems());
    TxtPayment_Method.getItems().addAll("Credito", "Debito", "Dinheiro");
    
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
    private void BtnExcluir(ActionEvent event) throws IOException
    {
        PrjIdBO b = new PrjIdBO();
        if( e != null){
        b.DeleteExpense(e);
         
        }
    }
    private void BtnSave(ActionEvent event) throws IOException
    {
        Expense e = new Expense();
        PrjIdBO b = new PrjIdBO();
        
        e.setCod_User(Login.session);
        e.setDescription(TxtDescription.getText());
        e.setCity(TxtCity.getText());
        e.setEstablishment_Name(TxtEstablishment_Name.getText());
        e.setDate(Date.valueOf( TxtExpense_Date.getValue()));
        e.setState(TxtState.getSelectionModel().getSelectedItem().toString());
        e.setCategory(TxtCategory.getSelectionModel().getSelectedItem().toString());
        e.setEstablishment_Nature(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString());
        e.setFrequency(TxtFrequency.getSelectionModel().getSelectedItem().toString());
        e.setPayment_Method(TxtPayment_Method.getSelectionModel().getSelectedItem().toString());
        e.setPrice(TxtPrice.getText());
        
        b.updateExpense(e);
        
    }

    
    
    public void preencherTable()
    {
        data = FXCollections.observableArrayList();
        data =  new PrjIdBO().buscarExpense();
        PropertyValueFactory<Object, Object> propertyValueFactory = new PropertyValueFactory<>("Cod_Expense");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnEstablishment.setCellValueFactory(new PropertyValueFactory<>("Establishment_Name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        TableExpenses.setItems(null);
      
        TableExpenses.setRowFactory(tv -> {
              TableRow<Expense> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
            if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                && event.getClickCount() == 1) {
                e = row.getItem();
                e = new PrjIdBO().selectExpense(e);
                
             TxtDescription.setText(e.getDescription());
             TxtPrice.setText(e.getPrice());
             TxtEstablishment_Name.setText(e.getEstablishment_Name());
             TxtCity.setText(e.getCity());
             TxtExpense_Date.setValue(LocalDateTime.ofInstant( Instant.ofEpochMilli(e.getDate().getTime()), ZoneId.systemDefault()).toLocalDate());
             TxtEstablishment_Nature.getSelectionModel().select(e.getEstablishment_Nature());
             TxtState.getSelectionModel().select(e.getState());
             TxtPayment_Method.getSelectionModel().select(e.getPayment_Method());
             TxtCategory.getSelectionModel().select(e.getCategory());
             TxtFrequency.getSelectionModel().select(e.getFrequency());
             
             
                                                }
                                            }); 
            return row;
            });
        TableExpenses.setItems( data);
       
         
    } 
    
}
