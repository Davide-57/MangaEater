package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public abstract class FiltroAnnunci {

    protected List<Annuncio> listaAnnunci;

    protected boolean categoriaFiltrata = false;

    protected boolean titoloFiltrato = false;

    public abstract List<Annuncio> visualizzaAnnunci();

    public abstract void setOrdineAnnunci(OrdineAnnunci ordineAnnunci);

    public enum OrdineAnnunci{
        ID,
        TITOLO,
        COSTO
    }

}
