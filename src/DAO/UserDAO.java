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
        //Insere o novo usuário no banco de dados, validação feita nas outras funções.
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
        //Pesquisa se tem uma combinação de senha e username enviada, e retorna valor 'false' e 'true' baseado no resultado.
        ResultSet rs;  
        boolean result = false;
        String SQL;
        try
        {   
            SQL = ("SELECT * FROM system_user WHERE User_name ='"+ u.getU_Name() + "' AND User_Password = '"+u.getUser_Password()+"'");
            rs = conn.createStatement().executeQuery(SQL);  

          
            if(rs.next()){
               result = true; 
            }
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        System.out.println(result);
            return result;
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
      //Dá update ao Usuário com o mesmo Cod_User que a sessão.
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
    
    public void DeleteUser() 
    {
//Deleta o Usuário, junto com todos os gastos relacionado a ele.
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
        //Verifica se já existe um usuário com o mesmo nome no banco de dados.
        boolean result = false;
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE User_Name = ?");
            ppStmt.setString(1, Name);
            ResultSet rs;
         
            rs = ppStmt.executeQuery();
            if(rs != null){
       
            result = true;
            } else {
            result = false;
              }
           
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }  
    return result;
    }
    
    public boolean VerifyEmail(String Email) {
        //Verfica se o E-mail inserido já está sendo usado no banco de dados.
        boolean result = false;
        try{
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM system_user WHERE Email = ?");
            ppStmt.setString(1, Email);
            ResultSet rs;
         
            rs = ppStmt.executeQuery();
            if(rs == null){
            result = true;
            } else {
            result = false;
              }
           
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }  
        return result;
    }
    
    public boolean VerifyUser(String Username) {
        //Verifica se já existe um Username no banco de dados com o mesmo UserName inserido.
          boolean result = false;
          String SQL;
          ResultSet rs = null;
          try{
            SQL = ("SELECT * FROM system_user WHERE U_Name ='"+ Username + "'");
            rs = conn.createStatement().executeQuery(SQL);  
            if(!rs.next()){
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
