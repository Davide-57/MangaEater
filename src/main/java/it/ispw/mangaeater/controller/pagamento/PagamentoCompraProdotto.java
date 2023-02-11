package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.controller.Login;
import it.ispw.mangaeater.dao.UtenteDAO;
import it.ispw.mangaeater.dao.UtenteDAOCSV;
import it.ispw.mangaeater.dao.UtenteDAOJDBC;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

import java.io.IOException;

public class PagamentoCompraProdotto implements Pagamento {

    private final ComprareProdotto cp;

    private int idAnnuncio;

    private String titoloAnnuncio;

    private double costo;

    private Utente acquirente;

    private String emailVenditore;

    private double saldoAcquirente;

    public PagamentoCompraProdotto(ComprareProdotto cp) {
        this.cp = cp;
    }

    @Override
    public InfoPagamentoBean estraiInfoPagamento() throws UserNotLoggedException {
        estraiInfoAnnuncio();
        estraiInfoAcquirente();
        return new InfoPagamentoBean(idAnnuncio, titoloAnnuncio, emailVenditore, costo, saldoAcquirente);
    }

    private void estraiInfoAcquirente() throws UserNotLoggedException {
        if(cp.isLogged()){
            acquirente = cp.getUtenteLoggato();
            saldoAcquirente = acquirente.getSaldo();
        }
        else{
            throw new UserNotLoggedException("L'utente non si è autenticato. Per procedere al pagamento occorre autenticarsi.");
        }
    }

    private void estraiInfoAnnuncio() {
        Annuncio annuncio = cp.getAnnuncioInDettaglio();
        idAnnuncio = annuncio.getId();
        titoloAnnuncio = annuncio.getTitolo();
        costo = annuncio.getCosto();
        emailVenditore = annuncio.getVenditoreEmail();
    }

    @Override
    public void finalizzaPagamento() throws InsufficientCreditException, SQLUtenteException, EmailNotFoundException, IOException {

        if(saldoAcquirente < costo){
            throw new InsufficientCreditException("L'utente non ha saldo sufficiente.");
        }

        UtenteDAO utenteDAO = new UtenteDAOJDBC();
        double nuovoSaldo = calcolaNuovoSaldo();

        utenteDAO.updateSaldo(acquirente, nuovoSaldo);

        //le seguenti due istruzioni sono effettuate solo per mantenere la consistenza tra la persistenza nel DB e quella in file system
        utenteDAO = new UtenteDAOCSV();
        utenteDAO.updateSaldo(acquirente, nuovoSaldo);


        acquirente.setSaldo(nuovoSaldo);
        inviaEmail();

    }

    public double calcolaNuovoSaldo() {
        return saldoAcquirente - costo;
    }

    @Override
    public Login creaLoginController() {
        return cp.creaControllerLogin();
    }

    @Override
    public UtenteBeanFromController getUtenteLoggatoBean() {
        return UtenteBeanFromController.createBean(acquirente);
    }

    private void inviaEmail() throws EmailNotFoundException {
        // il controller applicativo dell'acquisto si occuperà d'inviare una email (funzionalità dummy) al venditore e al corriere
        String emailAcquirente = acquirente.getEmail();
        cp.concludiAcquisto(titoloAnnuncio, emailVenditore, emailAcquirente);
    }


}
