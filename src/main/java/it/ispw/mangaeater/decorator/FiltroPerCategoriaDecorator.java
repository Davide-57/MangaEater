package it.ispw.mangaeater.decorator;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroPerCategoriaDecorator extends Decorator{

    private final CategoriaAnnuncio categoria;

    public FiltroPerCategoriaDecorator(FiltroAnnunci filtro, CategoriaAnnuncio categoria) {

        super(filtro);
        this.categoria = categoria;
        this.categoriaFiltrata = true;
    }


    @Override
    public List<Annuncio> visualizzaAnnunci() {

        rimuoviDecoratorDaSovrascrivere();

        listaAnnunci = super.visualizzaAnnunci();

        List<Annuncio> nuovaListaFiltrata = listaAnnunci.stream()
                .filter(entry -> entry.getCategoria()==categoria)
                .collect(Collectors.toList());

        return nuovaListaFiltrata;
    }

    @Override
    protected void rimuoviDecoratorDaSovrascrivere(){
        if(filtro.categoriaFiltrata){
            filtro = ((Decorator) filtro).getFiltro();
        }
    }



    @Override
    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {

        super.setOrdineAnnunci(ordineAnnunci);
    }

}
