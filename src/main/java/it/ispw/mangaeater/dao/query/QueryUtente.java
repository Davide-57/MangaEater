package it.ispw.mangaeater.dao.query;

import it.ispw.mangaeater.entity.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryUtente {

    //aggiunto perché suggerito da SonarCloud in quanto è una Utility Class
    private QueryUtente() {
        throw new IllegalStateException("Utility class");
    }

    public static ResultSet selectAll(Statement stmt) throws SQLException {
        String sql = "SELECT * FROM utente;";
        return stmt.executeQuery(sql);
    }

    public static int updateSaldo(Statement stmt, Utente acquirente, double nuovoSaldo) throws SQLException  {
        String updateStatement = String.format("UPDATE utente set saldo='%s' WHERE idUtente = %s", acquirente.getSaldo(), acquirente.getId());
        return stmt.executeUpdate(updateStatement);
    }
}
