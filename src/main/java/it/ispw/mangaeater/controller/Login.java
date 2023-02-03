package it.ispw.mangaeater.controller;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.bean.UtenteBeanFromView;
import it.ispw.mangaeater.dao.UtenteDAO;
import it.ispw.mangaeater.dao.UtenteDAOCSV;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.sessione.Sessione;

import java.io.IOException;
import java.util.List;

public class Login {

    ComprareProdotto cp;        // controller applicativo dell'acquisto di un prodotto
    List<Utente> listaUtenti;


    public Login(ComprareProdotto cp) {
        // questo costruttore viene richiamato solo quando è in corso il caso d'uso ComprareProdotto
        this.cp = cp;
    }

    public Login() {
    }


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

    public boolean autenticaUtente(UtenteBeanFromView bean) {
        String email = bean.getEmail();
        String psw = bean.getPsw();

        boolean utenteVerificato = false;

        for(Utente u: listaUtenti){
            if(u.getEmail().equals(email)){
                // una volta che viene matchata un'email allora viene confrontata anche la password
                if(u.getPsw().equals(psw)){
                    utenteVerificato = true;
                    cp.setSessione(new Sessione(u));
                    break;
                }
                // se la password non viene verificata allora termina il for perché l'email è univoca
                break;
            }
        }

        return utenteVerificato;
    }
}
