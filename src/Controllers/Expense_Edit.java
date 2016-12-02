/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
  private Label TxtAte;
  @FXML
  private Label LabSuccess;
  @FXML
  private TableColumn<Expense, Integer> Cod_col;
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
  @FXML
  private DatePicker TxtDate1;
  @FXML
  private DatePicker TxtDate2;
  private ObservableList<Expense> data;
  
  private Expense e;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Preenche as tabelas, comboboxes e também esconde dois labels junto com o Datepicker2
    TxtCategory.getItems().removeAll(TxtCategory.getItems());
    TxtCategory.getItems().addAll("Alimentação", "Lazer", "Moradia", "Transporte", "Outros");
    TxtEstablishment_Nature.getItems().removeAll(TxtEstablishment_Nature.getItems());
    TxtEstablishment_Nature.getItems().addAll("Banco", "Mercado", "Restaurante", "Loja de Utilidades", "Outro");
    TxtState.getItems().removeAll(TxtState.getItems());
    TxtState.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP","SE", "TO");

    TxtFrequency.getItems().removeAll(TxtFrequency.getItems());
    TxtFrequency.getItems().addAll("Única", "Diária", "Semanal", "Mensal");
    TxtPayment_Method.getItems().removeAll(TxtPayment_Method.getItems());
    TxtPayment_Method.getItems().addAll("Crédito", "Débito", "Dinheiro");
    LabSuccess.setVisible(false);
    TxtDate2.setVisible(false);
    TxtAte.setVisible(false);

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
    @FXML
    private void BtnExcluir(ActionEvent event) throws IOException
    {
     /*
         Exclui a despesa selecionada,depois de verificar se tem uma despesa selecionada
        em primeiro lugar.
     */
        PrjIdBO b = new PrjIdBO();
        if( e != null){
        b.DeleteExpense(e);
            LabSuccess.setVisible(true);
            LabSuccess.setText("Despesa excluida com sucesso!");

         preencherTable();
        }
    }
        
    
    @FXML
    private void CheckDate(){
        if(TxtDate1.getValue() == null){
            TxtDate2.setVisible(false);
            TxtDate2.setValue(null);
            TxtAte.setVisible(false);
            preencherTable();
        }
        else{
            TxtDate2.setVisible(true);
            TxtAte.setVisible(true);
            preencherTable();
        }
    }
    @FXML
    private void BtnSave(ActionEvent event) throws IOException{
        //Validação básica ao dar update no expense.
        Expense e = new Expense();
        PrjIdBO b = new PrjIdBO();
        boolean check = true;
        e.setCod_User(Login.session);
        if(TxtDescription.getText() != null){
            e.setDescription(TxtDescription.getText()); 
        }
        else{ 
            check = false;
        } 
        if(TxtCity.getText() != null){
        e.setCity(TxtCity.getText());
        } else { 
            check = false;
        }        
        if(TxtEstablishment_Name.getText()!= null){   
            e.setEstablishment_Name(TxtEstablishment_Name.getText());
        }else{
            check = false;
        }
        if(TxtExpense_Date.getValue() != null){
            e.setDate(Date.valueOf( TxtExpense_Date.getValue()));
        }else{
            check = false;
        }
        if(TxtState.getSelectionModel().getSelectedItem().toString() != "Estado"){
        e.setState(TxtState.getSelectionModel().getSelectedItem().toString());  
        }else{
            check = false;
            
        }
        if(TxtCategory.getSelectionModel().getSelectedItem().toString() != "Categoria"){
        e.setCategory(TxtCategory.getSelectionModel().getSelectedItem().toString());
        }
        else{
            check = false;
        }
        if(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString() != "Natureza"){   
            e.setEstablishment_Nature(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString());
        }
        else{
            check = false;
        }
        if(TxtFrequency.getSelectionModel().getSelectedItem().toString() != "Frequencia"){
            e.setFrequency(TxtFrequency.getSelectionModel().getSelectedItem().toString());
            
        } else{
            check = false;
        }
        if(TxtPayment_Method.getSelectionModel().getSelectedItem().toString() != "Forma de Pagamento"){
        e.setPayment_Method(TxtPayment_Method.getSelectionModel().getSelectedItem().toString());
        } else{
           check = false;
        }
        if(TxtPrice.getText() != null){
        e.setPrice(TxtPrice.getText());
        }else{
            check = false;
        }
        if(check = true){
            b.updateExpense(e);
            LabSuccess.setVisible(true);
            LabSuccess.setText("Despesa realizada com sucesso!");
            preencherTable();
        }
        else{
            check = true;
              LabSuccess.setVisible(true);
            LabSuccess.setText("Houve um erro ao atualizar a despesa");
        }
        
    }
     

    
    @FXML
    public void preencherTable()
    {
    /*
         Procura no banco de dados todas as expenses relacionadas ao usuário, e retorna 
        uma lista, que é inserida e exibida na tabela. Não só isso, mas também
        adiciona a função 'onclick' em cada linha da tabela, e ao clicá-la, 
        preenche os campos com o valores da expense selecionado.
        
         Também esconde o valor Id, que é necessário para identificar os gastos,
        mas não pode ser exibido ao usuário.
    */
        data = FXCollections.observableArrayList();
           if(TxtDate1.getValue() == null && TxtDate2.getValue() == null){
           data =  new PrjIdBO().buscarExpense();
          }
          else{
              if(TxtDate1.getValue() != null && TxtDate2.getValue() != null){
             
               data =  new PrjIdBO().buscarExpense(Date.valueOf(TxtDate1.getValue()),Date.valueOf( TxtDate2.getValue()));
              }
              else{
                  if(TxtDate1.getValue()!= null){
                       data =  new PrjIdBO().buscarExpense(Date.valueOf(TxtDate1.getValue()));
                  }
                
              }
          }
        
        Cod_col.setCellValueFactory(new PropertyValueFactory<>("Cod_Expense"));
        Cod_col.setVisible(false);
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
                Expense e2 = row.getItem();
             e = new PrjIdBO().selectExpense(e2);
             e.setCod_Expense(e2.getCod_Expense());
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
