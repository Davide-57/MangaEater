package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.decorator.FiltroAnnunci;
import it.ispw.mangaeater.decorator.FiltroStandard;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;

import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    //vedere se creare una classe Sessione
    private Utente utenteLoggato = null;

    private FiltroAnnunci filtroAnnunci;

    private AnnuncioBean beanDettaglioAnnuncio;


    public List<AnnuncioBean> estraiAnnunciTot() {

        filtroAnnunci = new FiltroStandard();
        listaAnnunci = filtroAnnunci.visualizzaAnnunci();
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public List<AnnuncioBean> cambiaOrdinamento(FiltroStandard.OrdineAnnunci nuovoOrdine) {

        filtroAnnunci.setOrdineAnnunci(nuovoOrdine);
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
        return utenteLoggato != null;
    }
}
