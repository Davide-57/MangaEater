package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.dao.UtenteDAO;
import it.ispw.mangaeater.dao.UtenteDAOJDBC;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;
import it.ispw.mangaeater.interfaces.Pagamento;
import it.ispw.mangaeater.sessione.Sessione;

public class PagamentoCP implements Pagamento {

    private ComprareProdotto cp;

    private double costo;

    private Utente acquirente;

    private String emailVenditore;

    public PagamentoCP(ComprareProdotto cp) {
        this.cp = cp;
    }

    @Override
    public void estraiInfoPagamento() throws UserNotLoggedException {
        estraiInfoAnnuncio();
        estraiInfoAcquirente();
    }

    private void estraiInfoAcquirente() throws UserNotLoggedException {
        Sessione sessione = cp.getSessione();
        if(sessione.isLogged()){
            throw new UserNotLoggedException("L'utente non si è autenticato. Per procedere al pagamento occorre autenticarsi.");
        }
        else{
            acquirente = sessione.getUtenteLoggato();
        }
    }

    private void estraiInfoAnnuncio() {
        Annuncio annuncio = cp.getAnnuncioInDettaglio();
        costo = annuncio.getCosto();
        emailVenditore = annuncio.getVenditoreEmail();
    }

    @Override
    public void paga() throws InsufficientCreditException, SQLUtenteException {

        if(acquirente.getSaldo() < costo){
            throw new InsufficientCreditException("L'utente non si è autenticato. Per procedere al pagamento occorre autenticarsi.");
        }

        UtenteDAO utenteDAO = new UtenteDAOJDBC();
        double nuovoSaldo = acquirente.getSaldo() - costo;

        utenteDAO.updateCosto(acquirente, nuovoSaldo);

    }

    @Override
    public void concludiPagamento() {
        // il controller aplicativo dell'acquisto si occuperà di inviare una email (funzionalità dummy) al venditore e al corriere
        cp.concludiAcquisto(emailVenditore);
    }
}
