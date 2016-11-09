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
           ("INSERT INTO Expense(Cod_User,Establishment_Name,Description,Price, Payment_Method, Frequency,Category, Date,Nature, State, City, Price) values(?,?,?,?,?,?,?,?,?,?,?,?)");
      
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
                ppStmt.setString(12, e.getPrice());
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
   
         
}

    

