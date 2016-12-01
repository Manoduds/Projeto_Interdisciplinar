/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import BO.PrjIdBO;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import objects.Expense;
import objects.RSSFeed;

/**
 * FXML Controller class
 *
 * @author MSB
 */
public class Main implements Initializable {
  @FXML
  Hyperlink[] RSSLinks;
  @FXML
  private Hyperlink RSSLink;
  @FXML
  private Hyperlink RSSLink1;
  @FXML
  private Hyperlink RSSLink2;
  @FXML
  private Hyperlink RSSLink3;
  @FXML
  private Hyperlink RSSLink4;
  @FXML
  private Hyperlink RSSLink5;
  @FXML
  private TableColumn<Expense, String> columnDesc;
  @FXML
  private TableColumn<Expense, String> columnPrice;
  @FXML
  private TableColumn<Expense, String> columnDate;
  @FXML
  private TableColumn<Expense, String> columnEstablishment;
  @FXML
  private TableColumn<Expense, String> columnCategory;
  @FXML
  private TableView<Expense> TableExpenses;
  @FXML
  private PieChart PieMonthReport;
  @FXML
  private PieChart PieYearReport;
  
  private ObservableList<Expense> data;
  final WebView browser = new WebView();
  final WebEngine webEngine = browser.getEngine();
  RSSFeed r = new RSSFeed();

    public Main() {
      
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                FillYearPie();
                FillMonthPie();
                preencherTable();
                FillLink();
    }   
    
    
    @FXML
    private void BtnAddExpense(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Expense_Add.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();        
    }
    
    @FXML
    private void BtnEditExpense(ActionEvent event) throws IOException 
    {
       Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Expense_Edit.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
    }
    
    @FXML
    private void BtnPersonalFinance(ActionEvent event) throws IOException 
    {
       Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Personal_Finance.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
    }
    
    @FXML
    private void BtnEconomyNews(ActionEvent event) throws IOException 
    {
       Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Economy_News.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
    }
    
    @FXML
    private void BtnBudgetTips(ActionEvent event) throws IOException 
    {
       Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Budget_Tips.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
    }
    
    @FXML
    private void BtnEditAccount(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Account_Details.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();        
    }
    
    @FXML
    private void BtnReports(ActionEvent event) throws IOException 
    {
       Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Reports.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();   
    }

    public void preencherTable()
    {
        data = FXCollections.observableArrayList();
        data =  new PrjIdBO().buscarExpense();
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        columnEstablishment.setCellValueFactory(new PropertyValueFactory<>("Establishment_Name"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        
        TableExpenses.setItems(null);
        TableExpenses.setItems( data);
    }
    
    
    
     
    private void FillYearPie() {
        PrjIdBO b = new PrjIdBO();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  
    Date date = new Date();
    date.setYear(date.getYear()-1);
        ObservableList<PieChart.Data> pieChartData = b.getPie(date);
    System.out.println(dateFormat.format(date));

        PieYearReport.setData(pieChartData);
        PieYearReport.setTitle("Gastos anuais");

        
    }
      private void FillMonthPie() {
        PrjIdBO b = new PrjIdBO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date.setMonth(date.getMonth()-1);
        ObservableList<PieChart.Data> pieChartData = b.getPie(date);
        System.out.println(dateFormat.format(date));
        PieMonthReport.setData(pieChartData);
        PieMonthReport.setTitle("Gastos Mensais");   
      }
      
     
    private void FillLink(){
        
    /* Obtém o código RSS baseado no valor de categorias, e preenche cada um dos
    hyperlinks com o titulo ou link. Caso acontecer um erro(Rede desconnectada)
    Os labels não aparecerão, e mostrará um aviso ao usuário.
    */
        try {
            this.RSSLinks = new Hyperlink[]{
                RSSLink,
                RSSLink1,
                RSSLink2,
                RSSLink3,
                RSSLink4,
                RSSLink5
            };
            for(int i = 0; i<6; i++){
                RSSLinks[i].setVisible(false);
            }
            PrjIdBO b = new PrjIdBO();
            r = b.getRSS();
            final String title[] = readRSS(r.getURL(),"title>");
            final String Link[] = readRSS(r.getURL(),"link>");

            for (int i = 0; i < 6; i++){
                String l = Link[i];
                Hyperlink RSS = RSSLinks[i];
                RSS.setText(title[i]);
                RSS.setOnAction(new EventHandler<ActionEvent>() {         
                    @Override
                    public void handle(ActionEvent e) {
                        try {
                            Desktop.getDesktop().browse(new URI(l));
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (URISyntaxException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                
                });
              RSS.setVisible(true);
            }
        } catch (IOException ex) {
          RSSLinks[0].setVisible(true);
          RSSLinks[0].setText("Você está desconnectado, e portanto, não poderá receber feeds até se reconnectar");
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
      
    private String[] readRSS(String urlAddress, String Line) throws IOException{
    //Utilizando o endereço do RSS, essa função lê as ultimas 6 notícias, retornando o título e o link.
    URL rssUrl = new URL(urlAddress);
    BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
    int limit = 0;
    int r = 0;

    final String result[] = new String[20];
    String line;
    while((line = in.readLine())!= null && limit <11){

        if(line.contains("<"+Line)){
            int firstPos = line.indexOf("<"+Line);
            String temp = line.substring(firstPos);
            temp = temp.replace("<"+Line, "");
            int lastPos = temp.indexOf("</"+Line);
            temp = temp.substring(0, lastPos);
            result[r] = temp;
            limit++;
            r++;
        }
    }
   
    return result;
    }
   
      
     @FXML
    private void  BtnLogoff(ActionEvent event) throws IOException 
    {
        Parent Login_Parent = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));        
        Scene scene = new Scene(Login_Parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.centerOnScreen();
        app_stage.show();
    }
 }


    

