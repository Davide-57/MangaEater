package it.ispw.mangaeater.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.entity.Utente;

import java.io.IOException;
import java.util.List;

public interface UtenteDAO {
    List<Utente> selectUtentiTot() throws CsvValidationException, IOException;
}
