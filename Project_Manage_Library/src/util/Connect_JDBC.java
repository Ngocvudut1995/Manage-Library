/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Dang
 */
public class Connect_JDBC {
    public static final String userName = "sa";
    public static final String password = "123456";
//    public static final String url = "jdbc:sqlserver://VUDANG:1433;databaseName = QuanLyCoffees";
      public static final String url = "jdbc:sqlserver://VANTU:1433;databaseName = JavaDemo";
    public Connection getConnection() {
        Connection cn = null;
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url,userName,password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connect_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connect_JDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }

}
