package it.ispw.mangaeater.decorator_pattern;

import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestFiltroPerCategoriaDecorator {

    @Test
    void testVisualizzaAnnunci() {

        FiltroAnnunci filtroBase = new FiltroStandard();
        CategoriaAnnuncio categoria = CategoriaAnnuncio.SHONEN;
        FiltroPerCategoriaDecorator filtroPerCategoria = new FiltroPerCategoriaDecorator(filtroBase, categoria);

        List<Annuncio> listaAnnunciFiltrati = filtroPerCategoria.visualizzaAnnunci();

        List<Annuncio> listaAnnunciTotali = filtroBase.visualizzaAnnunci();
        int count = 0;
        for(Annuncio annuncio: listaAnnunciTotali){
            if(annuncio.getCategoria() == categoria){
                count++;
            }
        }

        //valore aspettato = conteggio dei record con quella categoria
        //valore attuale = dimensione della lista filtrata ritornata dal metodo
        assertEquals(count, listaAnnunciFiltrati.size(), 0);

    }
}