package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAOJDBC implements AnnuncioDAO{
    @Override
    public List<Annuncio> selectAnnunciTot() {
        List<Annuncio> listaAnnunci = new ArrayList<>();
        listaAnnunci.add(new Annuncio("prova1","desc", 100.2, "axs@xse.it", "ednkjwebdkjbwa"));
        /*listaAnnunci.add(new Annuncio("prova2","desc", 20, "axs@xse.it", "ednkjwebdkjbwa"));
        listaAnnunci.add(new Annuncio("prova3","desc", 20, "axs@xse.it", "ednkjwebdkjbwa"));
        listaAnnunci.add(new Annuncio("prova4","desc", 20, "axs@xse.it", "ednkjwebdkjbwa"));
        listaAnnunci.add(new Annuncio("prova5","desc", 20, "axs@xse.it", "ednkjwebdkjbwa"));

         */
        return listaAnnunci;
    }
}
