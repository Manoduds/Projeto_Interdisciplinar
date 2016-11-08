/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableList;
import objects.Expense;
import objects.system_user;
import utilitarios.Conexao;


/**
 *
 * @author aluno
 */
public class PrjIdDAO {

   
 Connection conn;
    
    public  PrjIdDAO()
    {
        conn = new Conexao().conectar();
    }   
    
    public void SaveUser(system_user u) 
    {
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("INSERT INTO system_user(User_Name, User_password,Email, birthdate, Sex, U_name) values(?,?,?,?,?,?)");

            ppStmt.setString(1, u.getUser_name());
            ppStmt.setString(2, u.getUser_Password());          
            ppStmt.setString(3, u.getEmail()); 
            ppStmt.setDate(4, Date.valueOf(u.getBirthdate())); 
            ppStmt.setString(5, u.getSex()); 
            ppStmt.setString(6, u.getU_Name()); 
            ppStmt.execute();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public boolean compareUser(system_user u)
    {
        boolean rs = false;  
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement
            ("SELECT User_Name FROM system_user WHERE User_name = ? AND User_Password = ?");
            ppStmt.setString(1, u.getU_Name());
            ppStmt.setString(2, u.getUser_Password());          
            rs =   ppStmt.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
            return rs;
    }

    public void SaveExpense(Expense e) {
          try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("INSERT INTO Expense(Cod_User,Description,Price, Payment_Method, Frequency,Category, Expense_Date) values(?,?,?,?,?,?,?)");
      
            ppStmt.setInt(1, e.getCod_User());
            ppStmt.setString(2, e.getDescription());          
            ppStmt.setFloat(3, Float.valueOf(e.getPrice())); 
            ppStmt.setString(4, e.getPayment_Method()); 
            ppStmt.setString(5, e.getFrequency());
            ppStmt.setString(6, e.getCategory()); 
            ppStmt.setDate(7, e.getExpense_Date()); 
            ppStmt.execute();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

  

    public int SelectUser(system_user u) {
        int session = 0;
        try
        {
     
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE U_Name = ?");
            ppStmt.setString(1, u.getU_Name());
            ResultSet rs;
            rs = ppStmt.executeQuery();
           while(rs.next()){
            session = rs.getInt("Cod_User");
            System.out.println(session);
           }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return session;
    }
 
   
    private Expense buscarExp(ResultSet rs) throws SQLException
    {
        Expense e = new Expense();
        e.setDescription(rs.getString("Description"));
        e.setExpense_Date(rs.getDate("Expense_Date"));
        e.setCategory(rs.getString("Category"));
        e.setEstablishment_Name("Establishment");
        e.setPrice(String.valueOf(  rs.getFloat("Price")));
        return e;
    }

    public ObservableList<Expense> FillTable(int s) {
             ObservableList<Expense> data = null;
        try
        {
      
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM Expense WHERE Cod_User= ?");
            ppStmt.setInt(1, s);
            ResultSet rs;
            rs = ppStmt.executeQuery();
            while (rs.next()) {
                //get string from db,whichever way 
                data.add(buscarExp(rs));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return data;
    }
}
    

