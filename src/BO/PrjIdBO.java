/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.ExpenseDAO;
import DAO.UserDAO;
import java.text.DateFormat.Field;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import objects.Expense;
import objects.RSSFeed;
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

    public ObservableList<PieChart.Data> getPie(String dat) {
       
        float total = de.countexpense("*",dat);
        float al = (de.countexpense("Alimentaçao",dat)*100/total);
        float laz = (de.countexpense("Lazer",dat)*100/total);
        float Mor = (de.countexpense("Moradia",dat)*100/total);
        float Tran = (de.countexpense("Transporte",dat)*100/total);
        float Outros = (de.countexpense("Outros",dat)*100/total);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
               
                new PieChart.Data("Alimentação", al),
                new PieChart.Data("Lazer", laz),
                new PieChart.Data("Moradia",Mor),
                new PieChart.Data("Transporte",Tran),
                new PieChart.Data("Outros",Outros));

                return pieChartData;
    }
    
      public ObservableList<Series<String,Number>>AddBarData() {
         
 ObservableList<Series<String,Number>> Chart = FXCollections.observableArrayList();
                
               Series<String,Number> SeriesA = new Series<>();
         
        float al = (de.countexpense("Alimentaçao"));
        float laz = (de.countexpense("Lazer"));
        float Mor = (de.countexpense("Moradia"));
        float Tran = (de.countexpense("Transporte"));
        float Outros = (de.countexpense("Outros"));
                SeriesA.getData().add(new XYChart.Data("Alimentação", al));
                SeriesA.getData().add(new XYChart.Data("Lazer", laz));
                SeriesA.getData().add(new XYChart.Data("Moradia", Mor));
                SeriesA.getData().add(new XYChart.Data("Transporte", Tran));
                SeriesA.getData().add(new XYChart.Data("Outros", Outros));
                Chart.addAll(SeriesA);
                return Chart;
      }

    public RSSFeed getRSS() {
      float al = (de.countexpense("Alimentaçao"));
      float laz = (de.countexpense("Lazer"));
      float Mor = (de.countexpense("Moradia"));
      float Tran = (de.countexpense("Transporte"));
      float Outros = (de.countexpense("Outros"));  
      String largestField ="al";
      float largestValue = al;
      if(laz>largestValue){
        largestField ="laz";
        largestValue = laz;
      }
        if(Mor>largestValue){
        largestField ="Mor";
        largestValue = Mor;
      }
        if(Tran>largestValue){
        largestField ="Tran";
        largestValue = Tran;
      }
        if(Outros>largestValue){
        largestField ="Outros";
        largestValue = Outros;
      }
    return de.getRSS(largestField);
    }

}