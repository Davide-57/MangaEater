package it.ispw.mangaeater.bean;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;

import java.util.ArrayList;
import java.util.List;

public class AnnuncioBean {

    private final int id;
    private final String titolo;
    private final String descrizione;
    private final double costo;
    private final String venditoreEmail;
    private final CategoriaAnnuncio categoria;

    public AnnuncioBean(int id, String titolo, String descrizione, double costo, String venditoreEmail, CategoriaAnnuncio categoria) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditoreEmail = venditoreEmail;
        this.categoria = categoria;
    }

    //questa classe ha responsabilità di creator per sé stessa
    public static List<AnnuncioBean> creaBeans(List<Annuncio> listaAnnunci) {
        AnnuncioBean bean;
        List<AnnuncioBean> beans = new ArrayList<>();
        for (Annuncio annuncio: listaAnnunci) {
            bean = new AnnuncioBean(annuncio.getId(), annuncio.getTitolo(), annuncio.getDescrizione(), annuncio.getCosto(), annuncio.getVenditoreEmail(), annuncio.getCategoria());
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

    public int getId() {
        return id;
    }

    public CategoriaAnnuncio getCategoria() {
        return categoria;
    }
}
