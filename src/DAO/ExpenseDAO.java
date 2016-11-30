/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controllers.Login;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Expense;
import objects.RSSFeed;
import utilitarios.conexao;

/**
 *
 * @author paulo
 */
public class ExpenseDAO {
    
 Connection conn;
    
    public  ExpenseDAO()
    {
        conn = new conexao().conectar();
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
      
    
      
    private Expense buscarExp(ResultSet rs) throws SQLException
    {
          
        Expense e = new Expense();
        e.setCod_Expense(rs.getInt("Cod_Expense"));
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
        e.setCod_Expense(rs.getInt("Cod_Expense"));
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
    public void DeleteExpense(Expense e) {
       try{
            PreparedStatement ppStmt = conn.prepareStatement
           ("DELETE FROM Expense WHERE Cod_Expense = ?");
            ppStmt.setInt(1, e.getCod_Expense());
            ppStmt.executeUpdate();
            
            
       }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
     public void UpdateExpense(Expense e) {
        
            try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("UPDATE Expense SET Cod_User =?, Establishment_Name =?, Description =?,Price =?, Payment_Method =?, Frequency =?,Category =?, Date=?,Nature=?, State=?, City=? WHERE Cod_Expense = ?");
        
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
            
            ppStmt.executeUpdate();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
     
    public float countexpense(String cat) {
        float gasto = 0;
        String SQL;
        try
        {
            if(cat != "*"){
               SQL = ("Select PRICE  FROM Expense WHERE Cod_User = " + Login.session +" AND Category = '"+ cat +"'"); 
            }
            else{
               SQL = ("Select PRICE FROM Expense WHERE Cod_User ="+ Login.session); 

            }
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
              while(rs.next()){
           gasto = gasto + rs.getFloat("Price");
            }  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return gasto;
    }
    
      public float countexpense(String cat, String Dat) {
        float gasto = 0;
        String SQL;

        try
        {
            if(cat != "*"){
               SQL = ("Select PRICE  FROM Expense WHERE Date between '"+ Dat + "' AND curdate() AND Cod_User = " + Login.session +" AND Category = '"+ cat +"'");
            }
            else{
               SQL = ("Select PRICE FROM Expense WHERE Cod_User ="+ Login.session  +" AND Date between '"+ Dat +"' AND curdate()"); 

            }
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
              while(rs.next()){
           gasto = gasto + rs.getFloat("Price");
            }  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return gasto;
    }
   public float countexpense(String cat, String Dat, String Dat2) {
        float gasto = 0;
        String SQL;

        try
        {
            if(cat != "*"){
               SQL = ("Select PRICE  FROM Expense WHERE Date between '"+ Dat + "' AND '"+ Dat2 +"' AND Cod_User = " + Login.session +" AND Category = '"+ cat +"'");
            }
            else{
               SQL = ("Select PRICE FROM Expense WHERE Cod_User ="+ Login.session  +" AND Date between '"+ Dat +"' AND'"+ Dat2 +"'"); 

            }
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
              while(rs.next()){
           gasto = gasto + rs.getFloat("Price");
            }  
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return gasto;
    }
   
    public RSSFeed getRSS(String Cat) {
        RSSFeed r = new RSSFeed();
        try{
        String SQL = ("Select * FROM RSS WHERE Category ='"+ Cat +"'");
        ResultSet rs = conn.createStatement().executeQuery(SQL);  
        while(rs.next()){
        r.setRSS_Name(rs.getString("RSS_Name"));
        r.setURL(rs.getString("URL"));
        }
         }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return r;
    }
  }



