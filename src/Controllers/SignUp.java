/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    
    final ToggleGroup sexo = new ToggleGroup();
    /**
     * Initializes the controller class.
     */
 
    public void initialize(URL url, ResourceBundle rb) {
        //Criar o grupo de botões, e esconder as menssagens de erro.
        TxtM.setToggleGroup(sexo);
        TxtF.setToggleGroup(sexo);
        ErrName.setVisible(false);
        ErrEmail.setVisible(false);
        ErrDate.setVisible(false);
        ErrUser.setVisible(false);
        ErrPass.setVisible(false);
        ErrSexo.setVisible(false);
    }    
        /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    @FXML
    private boolean NameCheck() throws IOException{
        //validação do nome do usuário, verificando se o nome está em uso ou não.
        String Name;
        boolean result;
        PrjIdBO b = new PrjIdBO();
        Name = TxtName.getText();
   
        if(!"".equals(TxtName.getText())){
         result = b.VerifyName(Name);
            if(result != true){
                ErrName.setVisible(true);
                ErrName.setText("Esse nome já está em uso!");
                result = false;
            }
            else{
                ErrName.setVisible(false);
                result = true;
            
        }
        }
        else{
            ErrName.setVisible(true);
            ErrName.setText("Esse campo é obrigatório");
            result = false;
        }
        return result;
        
    }
    
    @FXML
    private boolean EmailCheck() throws IOException{
        //Validação do email, verificando se não tem um no banco de dados ou se é válido.
        String Email;
        boolean Check = false;
        boolean result = false;
        PrjIdBO b = new PrjIdBO();
        Email = TxtEmail.getText();
   
        if(Email != null){
         Check = b.VerifyEmail(Email);
            if(Check == true){
                ErrEmail.setText("Esse email já está em uso!");
                result = false;
            }
            if(result == false){
                Check = b.validateEmail(Email);
                if(Check == false){
                    ErrEmail.setVisible(true);
                    ErrEmail.setText("Esse email é invalido");
                    result = false;
                }
                else{
                    ErrEmail.setVisible(false);
                    result = true;
                }
            }
           
        }
        else{
            ErrEmail.setVisible(true);
            ErrEmail.setText("Esse campo é obrigatório");
        }
        return result;
  }
    
  @FXML
  private boolean UserCheck() throws IOException{
      //Validação do usuário, verificando se não tem um igual no banco de dados.
        boolean result;
        PrjIdBO b = new PrjIdBO();
        if(!"".equals(TxtUser.getText())){
  
            if(b.VerifyUser(TxtUser.getText()) == true){
                 ErrUser.setText("Esse usuário já está em uso!");
                 ErrUser.setVisible(true);
                 result = false;
            }
            else{
                ErrUser.setVisible(false);
                result = true;
            }
        }
        else{
            ErrUser.setVisible(true);
            ErrUser.setText("Esse campo é obrigatório");
            result = false;
        }
      return result;
  }
  
  @FXML
  private boolean PassCheck() throws IOException{
      //validação do campo senha, verificando se a senha é curta demais, e se a senha é diferente do campo 'repetir senha'
      String Pass = TxtPassword.getText();
      String RepPass = TxtRepeatPassword.getText();
      boolean result;
      if(Pass.length()<3){
          ErrPass.setVisible(true);
          ErrPass.setText("A senha precisa ter no mínimo 4 caracteres");
          result = false;
      }
      else{
           ErrPass.setVisible(false);
          if(!Pass.equals(RepPass) && RepPass != null){     
             ErrPass.setVisible(true);
             ErrPass.setText("As senhas precisam ser iguais");
             result = false;
          }
          else{
              ErrPass.setVisible(false);
             result = true;
          }
      }
      return result;
  }
  
 
    @FXML
    private void BtnLogin(ActionEvent event) throws IOException 
    {
    /*
        Salva o usuário no banco de dados, após verificar se a senha repetida é igual a senha normal, e validação dos campos do sexo do usuário.
    */
        boolean exe = true;
        system_user u = new system_user();
        PrjIdBO b = new PrjIdBO(); 
        if(NameCheck() == true){
        u.setUser_name(TxtName.getText());
        }
        else{
            exe = false;
        }
        if(EmailCheck() == true ){
        u.setEmail(TxtEmail.getText());
        }
        else{
            exe = false;
        }
        if(UserCheck() == true){
            u.setU_Name(TxtUser.getText());
        }
        else{
            exe = false;
        }
        if(TxtBirthDate.getValue() != null){
        u.setBirthdate( TxtBirthDate.getValue());
        ErrDate.setVisible(false);
        }
        else{
         exe = false;
         ErrDate.setVisible(true);
         ErrDate.setText("Voce precisa escolher um dia!");
        }
        
        if(PassCheck() == true){    
            u.setUser_Password(TxtPassword.getText());
        }
        
        if(sexo.getSelectedToggle()!= null){
            if(sexo.getSelectedToggle() == TxtM)
            {
                u.setSex("M");
                ErrSexo.setVisible(false);
            }
            else               
            {
                u.setSex("F");
                ErrSexo.setVisible(false);

            }
        }
        else{
        ErrSexo.setVisible(true);
        ErrSexo.setText("Você precisa declarar seu sexo!");
        exe = false;
        }
        
        if(exe == true){
        b.SaveUser(u);
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
        }
    }
           
    @FXML
    private void BtnHome(ActionEvent event) throws IOException 
    {
        /*
        Função para mudar de tela.
        */
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
}
