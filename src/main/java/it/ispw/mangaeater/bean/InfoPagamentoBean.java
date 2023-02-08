package it.ispw.mangaeater.bean;

public class InfoPagamentoBean {

    private final int idAnnuncio;

    private final String titoloAnnuncio;

    private final String emailVenditore;

    private final double costo;

    private final double saldoAcquirente;

    public InfoPagamentoBean(int idAnnuncio, String titoloAnnuncio, String emailVenditore, double costo, double saldoAcquirente) {
        this.idAnnuncio = idAnnuncio;
        this.titoloAnnuncio = titoloAnnuncio;
        this.emailVenditore = emailVenditore;
        this.costo = costo;
        this.saldoAcquirente = saldoAcquirente;
    }

    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public String getTitoloAnnuncio() {
        return titoloAnnuncio;
    }

    public String getEmailVenditore() {
        return emailVenditore;
    }

    public double getCosto() {
        return costo;
    }

    public double getSaldoAcquirente() {
        return saldoAcquirente;
    }
}
