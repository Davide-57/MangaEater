package it.ispw.mangaeater.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection conn = null;
    public synchronized static Connection getConnection() {
        if (conn == null) {
            try{
                String user = "user";
                String pass = "user";
                String db_url = "jdbc:mysql://localhost:3306/mangaeater";
                String driver_class_name = "com.mysql.cj.jdbc.Driver";
                Class.forName(driver_class_name);
                conn = DriverManager.getConnection(db_url, user, pass);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
