package it.ispw.mangaeater.decoratorPattern;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public abstract class FiltroAnnunci {

    protected List<Annuncio> listaAnnunci;

    // se true allora il filtro è un decoratore per filtrare le categorie
    protected boolean categoriaFiltrata = false;

    // se true allora il filtro è un decoratore per filtrare i titoli
    protected boolean titoloFiltrato = false;


    public abstract List<Annuncio> visualizzaAnnunci();

    public abstract void setOrdineAnnunci(OrdineAnnunci ordineAnnunci);

    public enum OrdineAnnunci{
        ID,
        TITOLO,
        COSTO_CRESCENTE,
        COSTO_DECRESCENTE
    }

}
