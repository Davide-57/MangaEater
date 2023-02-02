package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPerTitoloDecorator extends Decorator{

    private final String titolo;

    public FiltroPerTitoloDecorator(FiltroAnnunci filtro, String titolo) {

        super(filtro);
        this.titolo = titolo;
        this.titoloFiltrato = true;
    }

    @Override
    public List<Annuncio> visualizzaAnnunci() {

        rimuoviDecoratorDaSovrascrivere();

        listaAnnunci = super.visualizzaAnnunci();

        List<Annuncio> nuovaListaFiltrata = listaAnnunci.stream()
                .filter(entry -> entry.getTitolo().toUpperCase().contains(titolo.toUpperCase()))
                .collect(Collectors.toList());

        return nuovaListaFiltrata;
    }

    @Override
    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {
        super.setOrdineAnnunci(ordineAnnunci);
    }

    @Override
    protected void rimuoviDecoratorDaSovrascrivere() {
        if(filtro.titoloFiltrato){
            filtro = ((Decorator) filtro).getFiltro();
        }
    }
}