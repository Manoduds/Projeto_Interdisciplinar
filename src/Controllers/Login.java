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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.system_user;

/**
 *
 * @author MSB
 */
public class Login implements Initializable {
@FXML
    private Label LabelWarn;
@FXML
    private TextField TxtUser;
@FXML
    private TextField TxtPassword;

    public static int session;
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        LabelWarn.setVisible(false);
    }    
    @FXML
    private void BtnLogin(ActionEvent event) throws IOException 
    {
     /*
        Eu utilizei um 'if' para verificarse tem um usuário com a mesma senha e 
        Username no banco de dados, e se a combinação for válida, salva o Cod_User
        e também muda a tela para a principal.
        */
        system_user u = new system_user();
        PrjIdBO b = new PrjIdBO();
        u.setU_Name(TxtUser.getText());
          u.setUser_Password(TxtPassword.getText());
         boolean rs = b.compareUser(u);
        
        if( rs = true){
       
        session =  b.selectUser(u);
        if(session != 0){
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
        }
        else{
            LabelWarn.setVisible(true);
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
    
    @FXML
    private void HLForgot(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Login_Forgot_Password_Or_Username.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
 
}
