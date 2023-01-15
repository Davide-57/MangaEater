package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.dao.AnnuncioDAO;
import it.ispw.mangaeater.dao.AnnuncioDAOJDBC;
import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    public static AnnuncioBean beanDettaglioAnnuncio;

    public List<AnnuncioBean> visualizzaAnnunci() {
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC();
        listaAnnunci = annuncioDAO.selectAnnunciTot();
        return AnnuncioBean.creaBeans(listaAnnunci);
    }

    public void mostraDettaglioAnnuncio(AnnuncioBean bean) {
        beanDettaglioAnnuncio = bean;
    }
}
