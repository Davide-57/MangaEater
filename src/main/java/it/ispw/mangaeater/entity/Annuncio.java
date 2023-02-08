package it.ispw.mangaeater.entity;

import it.ispw.mangaeater.myenum.CategoriaAnnuncio;

public class Annuncio {
    private int id;
    private String titolo;
    private String descrizione;
    private double costo;
    private String venditoreEmail;
    private CategoriaAnnuncio categoria;

    public Annuncio(int id, String titolo, String descrizione, double costo, String venditoreEmail, CategoriaAnnuncio categoria) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditoreEmail = venditoreEmail;
        this.categoria = categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getVenditoreEmail() {
        return venditoreEmail;
    }

    public void setVenditoreEmail(String venditoreEmail) {
        this.venditoreEmail = venditoreEmail;
    }

    public int getId() {
        return id;
    }

    public CategoriaAnnuncio getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAnnuncio categoria) {
        this.categoria = categoria;
    }
}
