package it.ispw.mangaeater.bean;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;

import java.util.ArrayList;
import java.util.List;

public class AnnuncioBean {

    private String titolo;
    private String descrizione;
    private double costo;
    private String venditoreEmail;
    private String urlImmagine;

    public AnnuncioBean(String titolo, String descrizione, double costo, String venditoreEmail, String urlImmagine) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditoreEmail = venditoreEmail;
        this.urlImmagine = urlImmagine;
    }

    //questa classe ha responsabilità di creator per sé stessa
    public static List<AnnuncioBean> creaBeans(List<Annuncio> listaAnnunci) {
        int cont=0;
        AnnuncioBean bean;
        List<AnnuncioBean> beans = new ArrayList<>();
        for (Annuncio annuncio: listaAnnunci) {
            bean = new AnnuncioBean(annuncio.getTitolo(), annuncio.getDescrizione(), annuncio.getCosto(), annuncio.getVenditoreEmail(), annuncio.getUrlImmagine());
            beans.add(bean);
        }
        return beans;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getCosto() {
        return costo;
    }

    public String getVenditoreEmail() {
        return venditoreEmail;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }
}
