/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controllers.Login;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objects.system_user;
import utilitarios.conexao;

/**
 *
 * @author paulo
 */
public class UserDAO {
     Connection conn;
    
    public  UserDAO()
    {
        conn = new conexao().conectar();
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
        
    
        
    public void UpdateUser(system_user u) {
      
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("UPDATE system_user SET (User_Name, User_password,Email, birthdate, Sex, U_name) VALUES(?,?,?,?,?,?) WHERE Cod_User= ?");

            ppStmt.setString(1, u.getUser_name());
            ppStmt.setString(2, u.getUser_Password());          
            ppStmt.setString(3, u.getEmail()); 
            ppStmt.setDate(4, Date.valueOf(u.getBirthdate())); 
            ppStmt.setString(5, u.getSex()); 
            ppStmt.setString(6, u.getU_Name()); 
            ppStmt.setInt(7, Login.session);
            ppStmt.execute();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void DeleteUser() {
      try{
            PreparedStatement ppStmt = conn.prepareStatement
           ("DELETE Expense WHERE Cod_User = ?");
            ppStmt.setInt(1,Login.session);
            ppStmt.executeQuery();
            
             ppStmt = conn.prepareStatement
            ("DELETE system_user WHERE Cod_User = ?");
             ppStmt.setInt(1, Login.session);
             ppStmt.executeQuery();
            
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public boolean VerifyName(String Name) {
        boolean result = false;
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE User_Name = ?");
            ppStmt.setString(1, Name);
            ResultSet rs;
         
            rs = ppStmt.executeQuery();
            if(rs == null){
       
            result = false;
            } else {
            result = true;
              }
           
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }  
    return result;
    }
    
    public boolean VerifyEmail(String Email) {
        boolean result = false;
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE Email = ?");
            ppStmt.setString(1, Email);
            ResultSet rs;
         
            rs = ppStmt.executeQuery();
            if(rs == null){
       
            result = false;
            } else {
            result = true;
              }
           
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }  
        return result;
    }
    
    public boolean VerifyUser(String Username) {
          boolean result = false;
      try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE U_Name = ?");
            ppStmt.setString(1, Username);
            ResultSet rs;
         
            rs = ppStmt.executeQuery();
            if(rs == null){
       
            result = false;
            } else {
            result = true;
              }
           
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }  
  return result;
    }
}
