package connection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConnectionFactory {
    
    
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASS;
    
    public static void obterDadosBD() throws IOException{
        try {
            FileReader conf = new FileReader("configLabQuest.txt");
            BufferedReader lerArq = new BufferedReader(conf);
            ConnectionFactory.DRIVER = lerArq.readLine();
            ConnectionFactory.URL = lerArq.readLine();
            ConnectionFactory.USER = lerArq.readLine();
            ConnectionFactory.PASS = lerArq.readLine();
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao abrir o arquivo config.txt");
        }
        
    }
    
    public static Connection getConnection(){ 

        try {
            try {
                obterDadosBD();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL,USER,PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ",ex);
        }     
    }
    
    public static void closeConnection(Connection con){
        
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
            
            closeConnection(con);
            
            try {
                
                if(stmt!=null){
                    stmt.close();
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
            
            closeConnection(con,stmt);
            
            try {
                
               if(rs!=null){
                   rs.close();
               }
            
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
}
