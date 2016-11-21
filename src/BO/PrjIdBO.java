/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.ExpenseDAO;
import DAO.PrjIdDAO;
import DAO.UserDAO;
import javafx.collections.ObservableList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import objects.Expense;
import objects.system_user;

/**
 *
 * @author paulo
 */
public class PrjIdBO {
    DAO.ExpenseDAO de = new ExpenseDAO();
    DAO.UserDAO du = new UserDAO();
    
    public void SaveUser(system_user u) 
    {   
       du.SaveUser(u);
    }

    public boolean compareUser(system_user u) 
    {      
        boolean rs = du.compareUser(u);
        return rs;
    }
    
    public int selectUser(system_user u) {
        return du.SelectUser(u);
    }
    
    public void UpdateUser(system_user u) {
       du.UpdateUser(u);
    }

    public void DeleteUser() {
        du.DeleteUser();
    }

    public boolean VerifyName(String Username) {
        return du.VerifyName(Username);
        
    }
    public boolean validateEmail(String Email) {
      
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(Email);
      emailAddr.validate();
    } catch (AddressException ex) {
      result = false;
    }
    return result;
    }

    public boolean VerifyEmail(String Email) {
        return du.VerifyEmail(Email);
    }

    public boolean VerifyUser(String User) {
        return du.VerifyUser(User);
    }


  
    public void saveExpense(Expense e) 
    {
        de.SaveExpense(e);   
    }

    public ObservableList<Expense> buscarExpense() {
       return de.FillTable();
    }

    public Expense selectExpense(Expense e) {
        return de.getExpense(e);
    }

    public void updateExpense(Expense e) {
        de.UpdateExpense(e);
    }

    public void DeleteExpense(Expense e) {
       de.DeleteExpense(e);
    }

  
}

