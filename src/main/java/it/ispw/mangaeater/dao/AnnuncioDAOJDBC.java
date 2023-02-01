package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.dao.query.Query;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAOJDBC implements AnnuncioDAO{
    @Override
    public List<Annuncio> selectAnnunciTot() {
        List<Annuncio> listaAnnunci = new ArrayList<>();
        Statement stmt = null;
        Connection conn;

        try {
            conn = DbConnection.getConnection();

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = Query.selectAll(stmt);

            rs.first();
            do{
                int id = rs.getInt("idAnnuncio");
                String titolo = rs.getString("titolo");
                String desc = rs.getString("descrizione");
                double costo = rs.getDouble("costo");
                String venditoreEmail = rs.getString("venditore");
                CategoriaAnnuncio categoria = CategoriaAnnuncio.valueOf(rs.getString("categoria"));

                Annuncio a = new Annuncio(id, titolo, desc, costo, venditoreEmail, categoria);

                listaAnnunci.add(a);

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
        return listaAnnunci;
    }
}
