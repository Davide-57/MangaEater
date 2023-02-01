package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public abstract class FiltroAnnunci {

    protected List<Annuncio> listaAnnunci;

    public abstract List<Annuncio> visualizzaAnnunci();

    public abstract void setOrdineAnnunci(OrdineAnnunci ordineAnnunci);

    public enum OrdineAnnunci{
        ID,
        TITOLO,
        COSTO
    }

}
