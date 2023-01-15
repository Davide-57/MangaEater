package it.ispw.mangaeater.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection conn = null;

    //aggiunto perché suggerito da SonarCloud in quanto è una Utility Class
    private DbConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized Connection getConnection() {
        if (conn == null) {
            try{
                String user = "user";
                String dbUrl = "jdbc:mysql://localhost:3306/mangaeater";
                String driverClassName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(dbUrl, user, "user");
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
