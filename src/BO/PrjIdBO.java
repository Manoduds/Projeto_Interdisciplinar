/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.PrjIdDAO;
import javafx.collections.ObservableList;

import objects.Expense;
import objects.system_user;

/**
 *
 * @author paulo
 */
public class PrjIdBO {
    DAO.PrjIdDAO d = new PrjIdDAO();
    public void SaveUser(system_user u) 
    {   
       d.SaveUser(u);
    }

    public boolean compareUser(system_user u) 
    {      
        boolean rs = d.compareUser(u);
        return rs;
    }

    public void saveExpense(Expense e) 
    {
        d.SaveExpense(e);   
    }

    public int selectUser(system_user u) {
        
        return d.SelectUser(u);
    }

    public ObservableList<Expense> buscarExpense() {
       return d.FillTable();
    }

    public Expense selectExpense(Expense e) {
        return d.getExpense(e);
    }

    public void updateExpense(Expense e) {
        d.UpdateExpense(e);
    }

    public void DeleteExpense(Expense e) {
       d.DeleteExpense(e);
    }

    public void UpdateUser(system_user u) {
       d.UpdateUser(u);
    }

    public void DeleteUser() {
        d.DeleteUser();
    }

}
