package it.ispw.mangaeater.controller;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.dao.UtenteDAO;
import it.ispw.mangaeater.dao.UtenteDAOCSV;
import it.ispw.mangaeater.entity.Utente;

import java.io.IOException;
import java.util.List;

public class Login {
    List<Utente> listaUtenti;
    public void estraiUtenti() {

        try {
            UtenteDAO annuncioDAO = new UtenteDAOCSV();
            listaUtenti = annuncioDAO.selectUtentiTot();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (CsvValidationException e) {
            e.printStackTrace();
        }

    }
}
