package it.ispw.mangaeater.dao;

import it.ispw.mangaeater.entity.Annuncio;

import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> selectAnnunciTot();
}
