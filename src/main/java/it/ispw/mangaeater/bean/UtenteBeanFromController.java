package it.ispw.mangaeater.bean;

import it.ispw.mangaeater.entity.Utente;

public class UtenteBeanFromController {

    private final int id;
    private final String nome;
    private final String cognome;
    private final String email;
    private final String tipo;

    public UtenteBeanFromController(int id, String nome, String cognome, String email, String tipo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.tipo = tipo;
    }

    public static UtenteBeanFromController createBean(Utente utenteLoggato) {
        return new UtenteBeanFromController(utenteLoggato.getId(), utenteLoggato.getNome(), utenteLoggato.getCognome(), utenteLoggato.getEmail(), utenteLoggato.getTipo());
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }
}
