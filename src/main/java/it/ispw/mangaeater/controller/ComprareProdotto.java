package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.decoratorPattern.FiltroAnnunci;
import it.ispw.mangaeater.decoratorPattern.FiltroPerCategoriaDecorator;
import it.ispw.mangaeater.decoratorPattern.FiltroPerTitoloDecorator;
import it.ispw.mangaeater.decoratorPattern.FiltroStandard;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import it.ispw.mangaeater.sessione.Sessione;

import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    // la classe Sessione ha la responsabilità di registrare se un utente è attualmente loggato
    private Sessione sessione;

    private FiltroAnnunci filtroAnnunci = null;

    private AnnuncioBean beanDettaglioAnnuncio;

    public ComprareProdotto() {
        sessione = new Sessione();
    }

    public List<AnnuncioBean> estraiAnnunciTot() {

        //se filtro annunci è diverso da null utilizzo la lista generata precedentemente in modo da non resettare il filtro
        if(filtroAnnunci == null){
            filtroAnnunci = new FiltroStandard();
            listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        }
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
        beanDettaglioAnnuncio = bean;
    }

    public AnnuncioBean getBeanDettaglioAnnuncio() {
        return beanDettaglioAnnuncio;
    }

    public boolean isLogged() {
        return sessione.isLogged();
    }

    public void setSessione(Sessione sessione) {
        this.sessione = sessione;
    }

    public void setUtenteLoggato(Utente utente){
        sessione.setUtenteLoggato(utente);
    }

    public Sessione getSessione() {
        return sessione;
    }

    public void effettuaLogout() {
        setUtenteLoggato(null);
    }
}
