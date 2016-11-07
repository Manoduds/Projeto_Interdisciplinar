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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import objects.system_user;


/**   
   


 * FXML Controller class
 *
 * @author MSB
 */
public class SignUp implements Initializable {

    @FXML
    private TextField TxtName;
    @FXML
    private TextField TxtEmail;
    @FXML
    private DatePicker TxtBirthDate;
    @FXML
    private TextField TxtUser;
    @FXML
    private TextField TxtPassword;
    @FXML
    private TextField TxtRepeatPassword;
    @FXML
    private  RadioButton TxtM;
    @FXML
        private RadioButton TxtF;
    /**
     * Initializes the controller class.
     */
 
    public void initialize(URL url, ResourceBundle rb) {

    }    
        /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */

    @FXML
    private void BtnLogin(ActionEvent event) throws IOException 
    {
        system_user u = new system_user();
        PrjIdBO b = new PrjIdBO();          
        u.setUser_name(TxtName.getText()); 
    
        u.setEmail(TxtEmail.getText());
        u.setU_Name(TxtUser.getText());
        u.setBirthdate( TxtBirthDate.getValue());
      
        if( TxtRepeatPassword.getText() == null ? TxtPassword.getText() == null : TxtRepeatPassword.getText().equals(TxtPassword.getText()))
        {       
            u.setUser_Password(TxtPassword.getText());
            if(TxtM != null || TxtF != null)
            {
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
        app_stage.show();
            }
            else{
                System.out.println("Retardado, errou o if");
            }
       }
        else{
        System.out.println("Retardado, errou o primeiro if");
        }
    }
    
    @FXML
    private void BtnHome(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
}
