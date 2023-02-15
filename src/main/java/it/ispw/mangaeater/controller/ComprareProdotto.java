package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.boundary.CorriereBoundaryCP;
import it.ispw.mangaeater.boundary.VenditoreBoundaryCP;
import it.ispw.mangaeater.controller.pagamento.FactoryPagamento;
import it.ispw.mangaeater.controller.pagamento.PagamentoCompraProdotto;
import it.ispw.mangaeater.decorator_pattern.FiltroAnnunci;
import it.ispw.mangaeater.decorator_pattern.FiltroPerCategoriaDecorator;
import it.ispw.mangaeater.decorator_pattern.FiltroPerTitoloDecorator;
import it.ispw.mangaeater.decorator_pattern.FiltroStandard;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import it.ispw.mangaeater.observer_pattern.Observer;
import it.ispw.mangaeater.observer_pattern.Subject;
import it.ispw.mangaeater.sessione.Sessione;

import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    // la classe Sessione ha la responsabilità di registrare se un utente è attualmente loggato
    private final Sessione sessione;

    private FiltroAnnunci filtroAnnunci = null;

    private Annuncio annuncioInDettaglio;

    public ComprareProdotto() {
        sessione = new Sessione();
    }

    public ComprareProdotto(Annuncio annuncio){
        sessione = new Sessione();
        annuncioInDettaglio = annuncio;
    }

    public List<AnnuncioBean> estraiAnnunciTot() {

        //se filtro annunci è diverso da null utilizzo la lista generata precedentemente in modo da non resettare il filtro
        if(filtroAnnunci == null){
            filtroAnnunci = new FiltroStandard();
            listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        }
        int a;
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public List<AnnuncioBean> rimuoviFiltri() {
        filtroAnnunci = new FiltroStandard();
        listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public List<AnnuncioBean> cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci nuovoOrdine) {
        filtroAnnunci.setOrdineAnnunci(nuovoOrdine);
        listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public List<AnnuncioBean> estraiAnnunciPerCategoria(CategoriaAnnuncio categoria) {
        FiltroAnnunci filtroAnnunciWrapped = filtroAnnunci;
        filtroAnnunci = new FiltroPerCategoriaDecorator(filtroAnnunciWrapped, categoria);
        listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        if(listaAnnunci.isEmpty()){
            //reimposto il filtro precedente in quanto la ricerca non ha dato risultati
            filtroAnnunci = filtroAnnunciWrapped;
        }
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public List<AnnuncioBean> estraiAnnunciPerTitolo(String titolo) {
        FiltroAnnunci filtroAnnunciWrapped = filtroAnnunci;
        filtroAnnunci = new FiltroPerTitoloDecorator(filtroAnnunciWrapped, titolo);
        listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        return AnnuncioBean.creaBeans(listaAnnunci);
    }


    public void mostraDettaglioAnnuncio(AnnuncioBean bean) {
        annuncioInDettaglio = new Annuncio(bean.getId(), bean.getTitolo(), bean.getDescrizione(), bean.getCosto(), bean.getVenditoreEmail(), bean.getCategoria());
    }

    public AnnuncioBean getBeanDettaglioAnnuncio() {
        return new AnnuncioBean(annuncioInDettaglio.getId(), annuncioInDettaglio.getTitolo(), annuncioInDettaglio.getDescrizione(), annuncioInDettaglio.getCosto(),
                annuncioInDettaglio.getVenditoreEmail(), annuncioInDettaglio.getCategoria());
    }

    public boolean isLogged() {
        return sessione.isLogged();
    }

    public void setUtenteLoggato(Utente utente){
        sessione.setUtenteLoggato(utente);
    }

    public void effettuaLogout() {
        setUtenteLoggato((Utente) null);
    }

    public void setUtenteLoggato(UtenteBeanFromController utenteBean){
        sessione.setUtenteLoggato(new Utente(utenteBean.getId(), utenteBean.getNome(), utenteBean.getCognome(), utenteBean.getEmail(), utenteBean.getTipo(), utenteBean.getPsw(), utenteBean.getSaldo()));
    }

    public void setObserverSessione(Subject sessioneBean) {
        sessione.register((Observer) sessioneBean);
        ((Observer) sessioneBean).setSubject(sessione);
    }

    public void resetObserverSessione() {
        sessione.resetObserver();
    }

    public Annuncio getAnnuncioInDettaglio() {
        return annuncioInDettaglio;
    }

    public Utente getUtenteLoggato() {
        return sessione.getUtenteLoggato();
    }

    public void concludiAcquisto(String titoloAnnuncio, String emailVenditore, String emailAcquirente) throws EmailNotFoundException {
        VenditoreBoundaryCP vb = new VenditoreBoundaryCP(titoloAnnuncio, emailVenditore, emailAcquirente);
        CorriereBoundaryCP cb = new CorriereBoundaryCP(emailVenditore, emailAcquirente);

        vb.inviaEmail();
        cb.inviaEmail();

    }

    public PagamentoCompraProdotto creaControllerPagamento() {
        FactoryPagamento factoryPagamento = FactoryPagamento.getFactoryPagamento();
        return factoryPagamento.getPagamentoCompraProdotto(this);
    }

    public Login creaControllerLogin() {
        return new Login(this);
    }
}
