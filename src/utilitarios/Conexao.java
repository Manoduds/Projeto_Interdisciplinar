/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class Conexao 
{
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/dbexpenses";
    final private String usuario = "root";
    final private String senha = "";
    
    public Connection conectar()
    {
        Connection conn = null;
        
        try
        {
            /*
            Irá carregar para memória as classes da biblioteca do driver de conexão que gerencia a conexão com o banco de dados.
            */
            Class.forName(driver);
            /*
            A partir das classes do driver que estão na memória a aplicação estabelece a conexão com banco através dos parâmetros url, usuário e senha. Uma instância da conexão é mantida na memória até que o usuário faça a remoção ou finalize a aplicação. 
            */
            conn = DriverManager.getConnection(url,usuario,senha);
        }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
            
        return conn;
        
    }
}
