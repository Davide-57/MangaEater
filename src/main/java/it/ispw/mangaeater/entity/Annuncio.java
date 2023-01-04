package it.ispw.mangaeater.entity;

public class Annuncio {
    private String titolo;
    private String descrizione;
    private double costo;
    private Utente venditore;
    private String urlImmagine;

    public Annuncio(String titolo, String descrizione, double costo, Utente venditore, String urlImmagine) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditore = venditore;
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

    public Utente getVenditore() {
        return venditore;
    }

    public void setVenditore(Utente venditore) {
        this.venditore = venditore;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }
}
