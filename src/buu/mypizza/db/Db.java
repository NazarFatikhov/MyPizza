package buu.mypizza.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nazar
 */
public class Db {
    private final static String LOGIN = "my_pizza_admin";
  private final static String PASSWORD = "123";
  private final static String URI = "jdbc:postgresql://localhost:5432/my_pizza";
  private final static String DRIVER = "org.postgresql.Driver";
  
  private static Connection conn;

  public static Connection getConnection(){
    if(conn == null){
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URI, LOGIN, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, "Can't find driver class", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, "Incorrect sql request", ex);
        }
    }
    return conn;
  }

    
}
