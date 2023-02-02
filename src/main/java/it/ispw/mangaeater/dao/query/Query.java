package it.ispw.mangaeater.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {

    //aggiunto perché suggerito da SonarCloud in quanto è una Utility Class
    private Query() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectAllOrderById(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM annuncio ORDER BY idAnnuncio;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAllOrderByTitolo(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM annuncio ORDER BY titolo;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAllOrderByCostCresc(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM annuncio ORDER BY costo;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAllOrderByCostDecresc(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM annuncio ORDER BY costo DESC;";
        return stmt.executeQuery(sql);
    }
}
