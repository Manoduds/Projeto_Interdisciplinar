/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import objects.system_user;


/**   
   


 * FXML Controller class
 *
 * @author MSB
 */
public class SignUp implements Initializable {
    //Labels
    @FXML
    private Label ErrName;
    @FXML
    private Label ErrEmail;
    @FXML
    private Label ErrUser;
    @FXML
    private Label ErrPass;
    @FXML
    private Label ErrSexo;
    @FXML
    private Label ErrRepPass;
    @FXML
    private Label ErrDate;
    //TextFields
    @FXML
    private TextField TxtName;
    @FXML
    private TextField TxtEmail;
     @FXML
    private TextField TxtUser;
    @FXML
    private TextField TxtPassword;
    @FXML
    private TextField TxtRepeatPassword;
    //Datepicker
    @FXML
    private DatePicker TxtBirthDate;
   //RadioButtons
    @FXML
    private  RadioButton TxtM;
    @FXML
    private RadioButton TxtF;
    
    final ToggleGroup group = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
 
    public void initialize(URL url, ResourceBundle rb) {
        TxtM.setToggleGroup(group);
        TxtF.setToggleGroup(group);
        ErrName.setVisible(false);
    }    
        /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    @FXML
    private void NameCheck(ActionEvent event) throws IOException{
        String Name;
        boolean result;
        PrjIdBO b = new PrjIdBO();
        Name = TxtName.getText();
   
        if(Name != null){
         result = b.VerifyName(Name);
            if(result = true){
                ErrName.setVisible(true);
                ErrName.setText("Esse nome já está em uso!");
            }
            else{
                ErrName.setVisible(false);
            }
        }
        else{
            ErrName.setVisible(true);
            ErrName.setText("Esse campo é obrigatório");
        }
  }
    
    
    @FXML
    private void EmailCheck(ActionEvent event) throws IOException{
        String Email;
        boolean result;
        PrjIdBO b = new PrjIdBO();
        Email = TxtEmail.getText();
   
        if(Email != null){
         result = b.VerifyEmail(Email);
            if(result = true){
                ErrEmail.setText("Esse email já está em uso!");
            }
            if(result = false){
                result = b.validateEmail(Email);
                if(result = false){
                    ErrEmail.setVisible(true);
                    ErrEmail.setText("Esse email é invalido");
                }
                else{
                    ErrEmail.setVisible(false);
                }
            }
        }
        else{
            ErrEmail.setVisible(true);
            ErrEmail.setText("Esse campo é obrigatório");
        }
  }
  @FXML
  private void UserCheck(ActionEvent event) throws IOException{
        String User;
        boolean result;
        PrjIdBO b = new PrjIdBO();
        User = TxtUser.getText();
   
        if(User != null){
         result = b.VerifyUser(User);
            if(result = true){
                ErrUser.setText("Esse usuário já está em uso!");
                 ErrUser.setVisible(true);
            }
            else{
                ErrUser.setVisible(false);
            }
        }
        else{
            ErrUser.setVisible(true);
            ErrUser.setText("Esse campo é obrigatório");
        }
      
  }
  
  @FXML
  private void PassCheck(ActionEvent event) throws IOException{
      String Pass = TxtPassword.getText();
      String RepPass = TxtRepeatPassword.getText();
      if(Pass.length()<3){
          ErrPass.setVisible(true);
          ErrPass.setText("A senha precisa ter no mínimo 4 caracteres");
      }
      else{
           ErrPass.setVisible(false);
          if(!Pass.equals(RepPass) && RepPass != null){     
             ErrRepPass.setVisible(true);
             ErrRepPass.setText("As senhas precisam ser iguais");
          }
          else{
              ErrRepPass.setVisible(false);
             
          }
      }
  }
  
  @FXML
  private void PassRepCheck(ActionEvent event) throws IOException{
       String Pass = TxtPassword.getText();
      String RepPass = TxtRepeatPassword.getText();
         if(!Pass.equals(RepPass) && RepPass != null){
             ErrRepPass.setVisible(true);
             ErrRepPass.setText("As senhas precisam ser iguais");
          }
      
  }
    @FXML
    private void BtnLogin(ActionEvent event) throws IOException 
    {
        system_user u = new system_user();
        PrjIdBO b = new PrjIdBO();          
        u.setUser_name(TxtName.getText()); 
    
        u.setEmail(TxtEmail.getText());
        u.setU_Name(TxtUser.getText());
        if(TxtBirthDate.getValue() != null){
        u.setBirthdate( TxtBirthDate.getValue());
        
            if( TxtRepeatPassword.getText() == null ? TxtPassword.getText() == null : TxtRepeatPassword.getText().equals(TxtPassword.getText()))
            {       
                u.setUser_Password(TxtPassword.getText());
                if(TxtF != null || TxtM != null){
                   if(TxtM != null)
                    {
                       u.setSex("M");
                    }
                    else
                    {
                        u.setSex("F");
                    }
                    b.SaveUser(u);
                    Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
                    Scene scene = new Scene(Login_Parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene);
                    app_stage.centerOnScreen();
                    app_stage.show();
                
                }
                else{
                ErrSexo.setVisible(true);
                ErrSexo.setText("Você precisa declarar seu sexo!");
                }
            }
        }
        else{
            ErrDate.setVisible(true);
            ErrDate.setText("Voce precisa escolher um dia!");
        }
     
    }
    
    

    @FXML
    private void BtnHome(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}
