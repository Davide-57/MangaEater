package it.ispw.mangaeater.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static Connection conn = null;

    //aggiunto perché suggerito da SonarCloud in quanto è una Utility Class
    private DbConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static synchronized Connection getConnection() {
        if (conn == null) {
            BufferedReader in = null;
            try{
                in = new BufferedReader(new FileReader("src/main/resources/utenze/db.txt"));
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
            catch (ClassNotFoundException | SQLException | IOException e) {
                Logger logger = Logger.getLogger(DbConnection.class.getName());
                logger.log(Level.WARNING, "Errore durante la connessione al DB");
            }
            finally {
                try{
                    assert in != null;
                    in.close();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(DbConnection.class.getName());
                    logger.log(Level.WARNING, "Errore durante la chiusura dello stream di input");
                }


            }
        }
        return conn;
    }

}
