/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import objects.Expense;
import objects.system_user;

/**
 * FXML Controller class
 *
 * @author MSB
 */
public class Home implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    @Override
    public void initialize(URL location, ResourceBundle resources) {    
        
    }
  
  @FXML
    private void BtnLogin(ActionEvent event) throws IOException
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
    
    @FXML
    private void BtnSignUp(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }       
    
    @FXML
    private void BtnAbout(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/About.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}