/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Qayyum
 */
public class MyConnection {
    private static final String username = "root";
    private static final String password = "admin";
    private static final String dataConn = "jdbc:mysql://localhost:3306/university_portal";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dataConn, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return con;
    }
}

