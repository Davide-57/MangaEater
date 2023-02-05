package it.ispw.mangaeater.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryUtente {

    public QueryUtente() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectAll(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM utente;";
        return stmt.executeQuery(sql);
    }
}
