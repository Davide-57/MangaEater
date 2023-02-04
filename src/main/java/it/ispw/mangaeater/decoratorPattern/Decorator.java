package it.ispw.mangaeater.decoratorPattern;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public abstract class Decorator extends FiltroAnnunci{

    protected FiltroAnnunci filtro;

    protected Decorator(FiltroAnnunci filtro) {
        this.filtro = filtro;
    }


    @Override
    public List<Annuncio> visualizzaAnnunci() {
        return filtro.visualizzaAnnunci();
    }

    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {
        filtro.setOrdineAnnunci(ordineAnnunci);
    }

    protected abstract void rimuoviDecoratorDaSovrascrivere();

    public FiltroAnnunci getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroAnnunci filtro) {
        this.filtro = filtro;
    }
}
