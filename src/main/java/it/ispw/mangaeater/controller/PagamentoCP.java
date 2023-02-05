package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
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
    public void paga() {

    }

    @Override
    public void concludiPagamento() {

    }
}
