package it.ispw.mangaeater.bean;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;

import java.util.List;

public class AnnuncioBean {
    private String titolo;
    private String descrizione;
    private double costo;
    private Utente venditore;
    private String urlImmagine;

    public static List<AnnuncioBean> creaBean(List<Annuncio> listaAnnunci) {
        return null;
    }
}
