package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.SQLUtenteException;

import java.io.IOException;
import java.util.List;

public interface UtenteDAO {
    List<Utente> selectUtentiTot() throws IOException;

    void updateCosto(Utente utenteLoggato, double nuovoCosto) throws SQLUtenteException;

}
