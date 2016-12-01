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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MSB
 */
public class Login_Forgot_Password_Or_Username implements Initializable {
    @FXML
    private TextField TxtEmail;
    @FXML
    private Label ErrMail;      
    
    @FXML
    private void BtnConfirm(ActionEvent event) throws IOException 
    {
  //validação do e-mail(função enviar ainda não implementada.
   
       String Email;
        boolean result;
        PrjIdBO b = new PrjIdBO();
        Email = TxtEmail.getText();
   
          if(Email != null){
         result = b.VerifyEmail(Email);
            if(result = true){
                ErrMail.setText("Esse email já está em uso!");
            }
            if(result = false){
                result = b.validateEmail(Email);
                if(result = false){
                    ErrMail.setVisible(true);
                    ErrMail.setText("Esse email é invalido");
                }
                else{
                    ErrMail.setVisible(false);
                }
       
    }
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ErrMail.setVisible(false);
    }    
    
}
