package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.entity.Utente;

import java.io.IOException;
import java.util.List;

public interface UtenteDAO {
    List<Utente> selectUtentiTot() throws IOException;
}
