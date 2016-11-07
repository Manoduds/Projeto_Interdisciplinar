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
           ("INSERT INTO system_user(User_name, User_password,Email, birthdate, Sex, Username) values(?,?)");

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

    public void SaveExpense(Expense e) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }      
}
    

