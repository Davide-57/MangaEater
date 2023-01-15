package it.ispw.mangaeater.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {

    //aggiunto perché suggerito da SonarCloud in quanto è una Utility Class
    private Query() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectAll(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM annuncio;";
        return stmt.executeQuery(sql);
    }
}
