package it.ispw.mangaeater.decorator_pattern;

import it.ispw.mangaeater.entity.Annuncio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFiltroPerTitoloDecorator {

    @Test
    void testVisualizzaAnnunci() {
        FiltroAnnunci filtroBase = new FiltroStandard();
        String titolo = "on";
        FiltroPerTitoloDecorator filtroPerTitolo = new FiltroPerTitoloDecorator(filtroBase, titolo);

        List<Annuncio> listaAnnunciFiltrati = filtroPerTitolo.visualizzaAnnunci();

        List<Annuncio> listaAnnunciTotali = filtroBase.visualizzaAnnunci();
        int count = 0;
        for(Annuncio annuncio: listaAnnunciTotali){
            if(annuncio.getTitolo().toUpperCase().contains(titolo.toUpperCase())){
                count++;
            }
        }

        //valore aspettato = conteggio dei record che contento quel titolo nel proprio titolo
        //valore attuale = dimensione della lista filtrata ritornata dal metodo
        assertEquals(count, listaAnnunciFiltrati.size(), 0);
    }
}