package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public abstract class Decorator extends FiltroAnnunci{

    FiltroAnnunci filtro;

    public Decorator(FiltroAnnunci filtro) {
        this.filtro = filtro;
    }


    @Override
    public List<Annuncio> visualizzaAnnunci() {
        return filtro.visualizzaAnnunci();
    }

    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {
        filtro.setOrdineAnnunci(ordineAnnunci);
    }
}
