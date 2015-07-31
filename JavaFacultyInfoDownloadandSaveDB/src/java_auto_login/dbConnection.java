package java_auto_login;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dilipjain
 */
public class dbConnection {
    
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
        static final String DB_URL = "jdbc:mysql://localhost/faculty";
        static final String USER = "root"; 
        static final String PASS = "YOUR_PASSWORD_GOES_HERE";
        Connection conn;
        public Connection connToDb()
        {
            
            try
            { 
                //STEP 2: Register JDBC driver 
                Class.forName("com.mysql.jdbc.Driver"); 
                //STEP 3: Open a connection 
                System.out.println("Connecting to database..."); 
                conn = DriverManager.getConnection("jdbc:mysql://localhost/faculty?"+ "user=root&password="+PASS);
                
                return conn;
            }
            catch(SQLException se)
            { 
                 //Handle errors for JDBC 
                 se.printStackTrace();
                 return null;
            }
            catch(Exception e)
            { 
                //Handle errors for Class.forName 
                e.printStackTrace();
                return null;
            }
        }
        public void close()
        {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
}