/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controllers.Login;
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
          //Salva o Expense na tabela.
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
       //Retorna os valores selecionados, bseado no resultset enviado.
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
        //Preenche a tabela com todos os gastos.
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
    
     public ObservableList<Expense> FillTable(Date Dat) {
        //Preenche a tabela com todos os gastos.
        ObservableList<Expense> data = FXCollections.observableArrayList();
        try
        {
        String SQL = ("Select * FROM Expense WHERE Date between '"+ Dat + "' AND curdate() AND Cod_User ="+ Login.session);            
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
    
      public ObservableList<Expense> FillTable(Date Dat1, Date Dat2) {
        //Preenche a tabela com todos os gastos.
        ObservableList<Expense> data = FXCollections.observableArrayList();
        try
        {
        String SQL = ("Select * FROM Expense WHERE Date between '"+ Dat1 + "' AND '"+ Dat2 +"' AND Cod_User ="+ Login.session);            
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
       //Retorna todos os campos do Expense selecionado, baseado na primary key.
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
        //Deleta o Gasto com o valor Cod_Expense inserido.
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
        //Update na tabela 'Expense' com os valores inseridos, baseado no Cod_Expense.
            try
        {
            PreparedStatement ppStmt = conn.prepareStatement
           ("UPDATE Expense SET  Establishment_Name =?, Description =?,Price =?, Payment_Method =?, Frequency =?,Category =?, Date=?,Nature=?, State=?, City=? WHERE Cod_Expense = ?");
        
            ppStmt.setString(1, e.getEstablishment_Name());
            ppStmt.setString(2, e.getDescription());          
            ppStmt.setFloat(3, Float.valueOf(e.getPrice())); 
            ppStmt.setString(4, e.getPayment_Method()); 
            ppStmt.setString(5, e.getFrequency());
            ppStmt.setString(6, e.getCategory()); 
            ppStmt.setDate(7, e.getDate()); 
            ppStmt.setString(8, e.getEstablishment_Nature()); 
            ppStmt.setString(9, e.getState());
            ppStmt.setString(10, e.getCity());
            ppStmt.setInt(11, e.getCod_Expense());  
            
            ppStmt.executeUpdate();            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
     /*
     Countexpense utilizou o método de polimorphismo, para conseguir enviar sem data, com uma data, e com duas datas.
     */
    public float countexpense(String cat) {
        //Retorna a soma dos gastos em todos os periodos.
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
    
      public float countexpense(String cat, Date Dat) {
          //Retorna a soma dos gastos entre a data selecionada e a data atual.
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
   public float countexpense(String cat, Date Dat, Date Dat2) {
       //Retorna a soma dos gastos do período selecionado.
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
        //Obtém RSS de acordo com a categoria enviada.
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



