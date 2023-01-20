package it.ispw.mangaeater.controller;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.dao.AnnuncioDAO;
import it.ispw.mangaeater.dao.AnnuncioDAOCSV;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;

import java.io.IOException;
import java.util.List;

public class ComprareProdotto {

    private List<Annuncio> listaAnnunci;

    private Utente utenteLoggato = null;

    private AnnuncioBean beanDettaglioAnnuncio;

    public List<AnnuncioBean> visualizzaAnnunci() {
        try{
            //può essere scelto il tipo di DAO (CSV o DBMS). Da mettere una scelta randomica dei due
            AnnuncioDAO annuncioDAO = new AnnuncioDAOCSV();
            listaAnnunci = annuncioDAO.selectAnnunciTot();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


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
