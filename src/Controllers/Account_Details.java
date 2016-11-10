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
public class Account_Details implements Initializable {
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
    
    final ToggleGroup group = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TxtM.setToggleGroup(group);
        TxtF.setToggleGroup(group);
    }   
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
                b.UpdateUser(u);
                
             
            }
        }

     
    }
    
    @FXML
    private void BtnDelete(ActionEvent event) throws IOException{
         PrjIdBO b = new PrjIdBO();      
         b.DeleteUser();
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
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
