package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.dao.AnnuncioDAO;
import it.ispw.mangaeater.dao.AnnuncioDAOJDBC;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.jikan.JikanBoundary;

import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    public List<AnnuncioBean> visualizzaAnnunci() {
        AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC();
        listaAnnunci = annuncioDAO.selectAnnunciTot();

        //DA CANCELLARE TUTTO TRANNE IL RETURN
        JikanBoundary jb = new JikanBoundary();
        String desc = jb.estraiDescrizioneManga("one piece");
        System.out.println(desc);


        return AnnuncioBean.creaBeans(listaAnnunci);
    }
}
