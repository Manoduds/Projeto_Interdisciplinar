/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author paulo
 */
public class Expense {
    private String Description;
    private String Price;
    private Date Expense_Date;
    private String Establishment_Name;
    private String City;
    private String Category;
    private String Establishment_Nature;
    private String State;
    private String Frequency;
    private String Payment_Method;   
    private int Cod_User;
   

    
    
    
    public String getEstablishment_Name() {
        return Establishment_Name;
    }

    public void setEstablishment_Name(String Establishment_Name) {
        this.Establishment_Name = Establishment_Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public Date getExpense_Date() {
        return Expense_Date;
    }

    public void setExpense_Date(Date Expense_Date) {
        this.Expense_Date = Expense_Date;
    }


    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getEstablishment_Nature() {
        return Establishment_Nature;
    }

    public void setEstablishment_Nature(String Establishment_Nature) {
        this.Establishment_Nature = Establishment_Nature;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String Frequency) {
        this.Frequency = Frequency;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String Payment_Method) {
        this.Payment_Method = Payment_Method;
    }

    public int getCod_User() {
        return Cod_User;
    }

    public void setCod_User(int Cod_User) {
        this.Cod_User = Cod_User;
    }



    
 
 
 
 
}
