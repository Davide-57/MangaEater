package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.dao.query.QueryUtente;
import it.ispw.mangaeater.entity.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOJDBC implements UtenteDAO{
    @Override
    public List<Utente> selectUtentiTot() throws IOException {
        List<Utente> listaUtenti = new ArrayList<>();
        Statement stmt = null;
        Connection conn;

        try {
            conn = DbConnection.getConnection();

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryUtente.selectAll(stmt);

            rs.first();
            do{
                int utenteId = rs.getInt("idUtente");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String email = rs.getString("email");
                String tipo = rs.getString("tipo");
                String psw = rs.getString("password");
                double saldo = rs.getDouble("saldo");

                Utente u = new Utente(utenteId, nome, cognome, email, tipo, psw, saldo);

                listaUtenti.add(u);

            }while(rs.next());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaUtenti;
    }
}
