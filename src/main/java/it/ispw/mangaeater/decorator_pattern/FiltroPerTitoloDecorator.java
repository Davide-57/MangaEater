package it.ispw.mangaeater.decorator_pattern;

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

        return listaAnnunci.stream()
                .filter(entry -> entry.getTitolo().toUpperCase().contains(titolo.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    protected void rimuoviDecoratorDaSovrascrivere() {
        /*
        LOGICA DELLA RIMOZIONE DEI DECORATOR:
        Vengono rimossi tutti i decorator che applicano un filtro per titolo
        Per farlo il programma scorrerà tutti i filtri nella gerarchia attraverso la variabile filtroInGerarchiaActual
        Mentre la variabile filtroInGerarchiaPrec servirà per avere un riferimento al filtro applicato subito dopo il filtro in filtroInGerarchiaActual
         */

        //la seguente variabile verrà usata per memorizzare sequenzialmente ogni filtro che fa parte della gerarchia
        FiltroAnnunci filtroInGerarchiaActual = filtro;
        //la seguente variabile mantiene il riferimento al filtro precedente in modo da cambiare il suo filtro wrappato se filtroInGerarchiaActual è di un tipo da rimuovere
        Decorator filtroInGerarchiaPrec = this;
        while(filtroInGerarchiaActual.categoriaFiltrata || filtroInGerarchiaActual.titoloFiltrato){
            // se sto nel ciclo significa che filtroInGerarchia è un filtro non standard. Mi fermo quando arrivo al filtro standard
            if(filtroInGerarchiaActual.titoloFiltrato){
                filtroInGerarchiaPrec.setFiltro(((Decorator)filtroInGerarchiaActual).getFiltro());
            }
            //passo al prossimo filtro della gerarchia
            filtroInGerarchiaPrec = (Decorator) filtroInGerarchiaActual;
            filtroInGerarchiaActual = ((Decorator) filtroInGerarchiaActual).getFiltro();
        }
    }
}
