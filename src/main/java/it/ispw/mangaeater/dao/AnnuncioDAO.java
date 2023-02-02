package it.ispw.mangaeater.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.decorator.FiltroAnnunci;
import it.ispw.mangaeater.entity.Annuncio;

import java.io.IOException;
import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> selectAnnunciOrdinati(FiltroAnnunci.OrdineAnnunci ordineAnnunci) throws IOException, CsvValidationException;
}
