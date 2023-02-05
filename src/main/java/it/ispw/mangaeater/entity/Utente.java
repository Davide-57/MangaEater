package it.ispw.mangaeater.entity;

public class Utente {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String tipo;
    private String psw;

    private double saldo;

    public Utente() {
    }

    public Utente(int id, String nome, String cognome, String email, String tipo, String psw, double saldo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.tipo = tipo;
        this.psw = psw;
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
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

    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }
}
