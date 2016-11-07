/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author paulo
 */
public class system_user {
    private String User_name;
    private String User_Password;
    private String U_Name;
    private String Email;
    private  LocalDate birthdate;
    private String Sex;
    private int Cod_User;

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    } 

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String User_Password) {
        this.User_Password = User_Password;
    }

    public String getU_Name() {
        return U_Name;
    }

    public void setU_Name(String U_Name) {
        this.U_Name = U_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public int getCod_User() {
        return Cod_User;
    }

    public void setCod_User(int Cod_User) {
        this.Cod_User = Cod_User;
    }    
}
