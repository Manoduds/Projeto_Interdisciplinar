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
import java.time.LocalDate;
import java.util.Calendar;
import static java.util.Optional.empty;
import static java.util.OptionalDouble.empty;
import static java.util.OptionalInt.empty;
import static java.util.OptionalLong.empty;
import static java.util.stream.DoubleStream.empty;
import static java.util.stream.IntStream.empty;
import javafx.collections.FXCollections;
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
           ("INSERT INTO Expense(Cod_User,Establishment_Name,Description,Price, Payment_Method, Frequency,Category, Date,Nature, State, City) values(?,?,?,?,?,?,?,?,?,?,?)");
      
            ppStmt.setInt(1, e.getCod_User());
            ppStmt.setString(2, e.getEstablishment_Name());
            ppStmt.setString(3, e.getDescription());          
            ppStmt.setFloat(4, Float.valueOf(e.getPrice())); 
            ppStmt.setString(5, e.getPayment_Method()); 
            ppStmt.setString(6, e.getFrequency());
            ppStmt.setString(7, e.getCategory()); 
            ppStmt.setDate(8, e.getDate()); 
            ppStmt.setString(9, e.getEstablishment_Nature()); 
            ppStmt.setString(10, e.getState());
              ppStmt.setString(11, e.getCity());
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
        e.setDate(rs.getDate("Date"));
       e.setCategory(rs.getString("Category"));
        e.setEstablishment_Name(rs.getString("Establishment_Name"));
        e.setPrice(String.valueOf(  rs.getFloat("Price")));
   
        return e;

    }
    public ObservableList<Expense> FillTable() {
             ObservableList<Expense> data = FXCollections.observableArrayList();
        try
        {
        String SQL = ("Select * FROM Expense WHERE Cod_User ="+ Login.session);            
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
           while(rs.next()){
             data.add(buscarExp(rs));   
            }  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return data;
    }

    

    public Expense getExpense(Expense e) {
       
     try
        {
        String SQL = ("Select * FROM Expense WHERE Cod_Expense ="+ e.getCod_Expense());            
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
           while(rs.next()){
        e.setDescription(rs.getString("Description"));
        e.setPrice(String.valueOf(  rs.getFloat("Price")));
        e.setPayment_Method(rs.getString("Payment_Method"));
        e.setEstablishment_Name(rs.getString("Establishment_Name"));
        e.setEstablishment_Nature(rs.getString("Nature"));
        e.setDate(rs.getDate("Date"));
        e.setCategory(rs.getString("Category"));
        e.setCity(rs.getString("City"));
        e.setState(rs.getString("State"));
        e.setFrequency(rs.getString("Frequency"));
       
   
                 
            }  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        
        return e;
    }

    public void UpdateExpense(Expense e) {
        
            try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("UPDATE Expense SET (Cod_User,Establishment_Name,Description,Price, Payment_Method, Frequency,Category, Date,Nature, State, City) values(?,?,?,?,?,?,?,?,?,?,?) WHERE Cod_Expense = ?");
      
            ppStmt.setInt(1, e.getCod_User());
            ppStmt.setString(2, e.getEstablishment_Name());
            ppStmt.setString(3, e.getDescription());          
            ppStmt.setFloat(4, Float.valueOf(e.getPrice())); 
            ppStmt.setString(5, e.getPayment_Method()); 
            ppStmt.setString(6, e.getFrequency());
            ppStmt.setString(7, e.getCategory()); 
            ppStmt.setDate(8, e.getDate()); 
            ppStmt.setString(9, e.getEstablishment_Nature()); 
            ppStmt.setString(10, e.getState());
            ppStmt.setString(11, e.getCity());
            ppStmt.setInt(12, e.getCod_Expense());  
            
            ppStmt.execute();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public void DeleteExpense(Expense e) {
       try{
            PreparedStatement ppStmt = conn.prepareStatement
           ("DELETE Expense WHERE Cod_Expense = ?");
            ppStmt.setInt(1, e.getCod_Expense());
            ppStmt.executeQuery();
            
            
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
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

   
         
}

    

