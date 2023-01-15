package it.ispw.mangaeater.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                BufferedReader in = new BufferedReader(new FileReader("src/main/resources/utenze/db.txt"));
                String user;
                String password;
                String line;
                String[] userPasswordArray;
                line = in.readLine();
                userPasswordArray = line.split(" ");
                user = userPasswordArray[0];
                password = userPasswordArray[1];
                String dbUrl = "jdbc:mysql://localhost:3306/mangaeater";
                String driverClassName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(dbUrl, user, password);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

}
