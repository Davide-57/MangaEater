package it.ispw.mangaeater.entity;

import it.ispw.mangaeater.state.StateMachineAnnuncio;
import it.ispw.mangaeater.state.StateMachineAnnuncioConcreta;

public class Annuncio {
    private int id;
    private String titolo;
    private String descrizione;
    private double costo;
    private String venditoreEmail;

    private StateMachineAnnuncio stateMachine;

    public Annuncio(int id, String titolo, String descrizione, double costo, String venditoreEmail) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.costo = costo;
        this.venditoreEmail = venditoreEmail;
        this.stateMachine = new StateMachineAnnuncioConcreta(); //composizione
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
}
