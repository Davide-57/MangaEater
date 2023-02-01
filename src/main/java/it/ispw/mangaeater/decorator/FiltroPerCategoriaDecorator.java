package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPerCategoriaDecorator extends Decorator{

    public FiltroPerCategoriaDecorator(FiltroAnnunci filtro) {
        super(filtro);
    }

    @Override
    public List<Annuncio> visualizzaAnnunci() {
        listaAnnunci = super.visualizzaAnnunci();

        List<Annuncio> nuovaListaFiltrata = listaAnnunci.stream()
                .filter(entry -> entry.getTitolo().startsWith("O"))
                .collect(Collectors.toList());

        return nuovaListaFiltrata;
    }

    @Override
    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {
        super.setOrdineAnnunci(ordineAnnunci);
    }

}
