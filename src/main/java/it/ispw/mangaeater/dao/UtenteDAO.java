package it.ispw.mangaeater.dao;

import com.opencsv.exceptions.CsvException;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.SQLUtenteException;

import java.io.IOException;
import java.util.List;

public interface UtenteDAO {
    List<Utente> selectUtentiTot() throws IOException;

    void updateSaldo(Utente utenteLoggato, double nuovoSaldo) throws SQLUtenteException, IOException, CsvException;

}
