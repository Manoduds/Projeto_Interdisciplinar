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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import javafx.scene.control.Label;
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
private Label LabSuc;
@FXML
private Label ErrDesc;
@FXML
private Label ErrPrec;
@FXML
private Label ErrEstab;
@FXML
private Label ErrNature;
@FXML
private Label ErrForm;
@FXML
private Label ErrFreq;
@FXML
private Label ErrCat;
@FXML
private Label ErrDat;
@FXML
private Label ErrCity;
@FXML
private Label ErrState;
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
    
    
    ErrDesc.setVisible(false);
    ErrPrec.setVisible(false);
    ErrEstab.setVisible(false);
    ErrNature.setVisible(false);
    ErrForm.setVisible(false);
    ErrFreq.setVisible(false);
    ErrCat.setVisible(false);
    ErrDat.setVisible(false);
    ErrCity.setVisible(false);
    ErrState.setVisible(false);
    LabSuc.setVisible(false);     
    }   
    @FXML
    private void BtnSave(ActionEvent event) throws IOException{
        Expense e = new Expense();
        PrjIdBO b = new PrjIdBO();
        boolean check = true;
        
        
        e.setCod_User(Login.session);
        if(TxtDescription.getText() != null){
            e.setDescription(TxtDescription.getText());
            ErrDesc.setVisible(false);
        }
        else{ 
            check = false;
            ErrDesc.setVisible(true);
            ErrDesc.setText("A descrição é obrigatória!");
        } 
        
        if(TxtCity.getText() != null){
             ErrCity.setVisible(false);
        e.setCity(TxtCity.getText());
        } else { 
            check = false;
            ErrCity.setVisible(true);
            ErrCity.setText("A cidade é obrigatória!");
        }
        
        if(TxtEstablishment_Name.getText()!= null){
            
            ErrEstab.setVisible(false);
            e.setEstablishment_Name(TxtEstablishment_Name.getText());
        }else{
            check = false;
            ErrEstab.setVisible(true);
            ErrEstab.setText("O Estabelecimento é obrigatório!");
        }
        
        if(TxtExpense_Date.getValue() != null){
                    ErrDat.setVisible(false);
                    e.setDate(Date.valueOf( TxtExpense_Date.getValue()));
        }else{
            check = false;
            ErrDat.setVisible(true);
            ErrDat.setText("A data é obrigatória!");
        }
        if(TxtState.getSelectionModel().getSelectedItem().toString() != "Estado"){
        e.setState(TxtState.getSelectionModel().getSelectedItem().toString());
        ErrState.setVisible(false);
        }else{
            check = false;
            ErrState.setVisible(true);
            ErrState.setText("O estado é obrigatório!");
        }
        if(TxtCategory.getSelectionModel().getSelectedItem().toString() != "Categoria"){
        e.setCategory(TxtCategory.getSelectionModel().getSelectedItem().toString());
        ErrCat.setVisible(false);
        }
        else{
            check = false;
            ErrCat.setVisible(true);
            ErrCat.setText("A categoria é obrigatória!");
        }
        if(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString() != "Natureza"){   
            e.setEstablishment_Nature(TxtEstablishment_Nature.getSelectionModel().getSelectedItem().toString());
            ErrNature.setVisible(false);
        }
        else{
            check = false;
            ErrNature.setVisible(true);
            ErrNature.setText("A natureza é obrigatória!");
        }
        if(TxtFrequency.getSelectionModel().getSelectedItem().toString() != "Frequencia"){
            e.setFrequency(TxtFrequency.getSelectionModel().getSelectedItem().toString());
            ErrNature.setVisible(false);
        } else{
            check = false;
            ErrFreq.setVisible(true);
            ErrFreq.setText("A frequencia é obrigatória!");
        }
        if(TxtPayment_Method.getSelectionModel().getSelectedItem().toString() != "Forma de Pagamento"){
        e.setPayment_Method(TxtPayment_Method.getSelectionModel().getSelectedItem().toString());
           ErrForm.setVisible(false);
        } else{
           check = false;
           ErrForm.setVisible(true);
           ErrForm.setText("A forma de pagamento é obrigatória!");
        }
        if(TxtPrice.getText() == null){
        e.setPrice(TxtPrice.getText());
        }else{
            check = false;
            ErrPrec.setVisible(true);
            ErrPrec.setText("O preço é obrigatório!");
        }
        if(check = true){
            b.saveExpense(e);
            LabSuc.setVisible(true);
            LabSuc.setText("Despesa realizada com sucesso!");
        } 
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
