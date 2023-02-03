package it.ispw.mangaeater.sessione;

import it.ispw.mangaeater.entity.Utente;

public class Sessione {

    private Utente utenteLoggato = null;

    public Sessione() {
    }

    public Sessione(Utente utenteLoggato) {
        this.utenteLoggato = utenteLoggato;
    }

    public boolean isLogged() {
        return utenteLoggato != null;
    }

}
