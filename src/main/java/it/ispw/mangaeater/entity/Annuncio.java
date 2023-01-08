package it.ispw.mangaeater.entity;

public class Annuncio {
    private String titolo;
    private String descrizione;
    private double costo;
    private String venditoreEmail;
    private String urlImmagine;

    public Annuncio(String titolo, String descrizione, double costo, String venditoreEmail, String urlImmagine) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditoreEmail = venditoreEmail;
        this.urlImmagine = urlImmagine;
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

    public void setVenditoreEmail(String venditore) {
        this.venditoreEmail = venditoreEmail;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}
