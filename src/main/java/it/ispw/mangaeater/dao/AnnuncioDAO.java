package it.ispw.mangaeater.dao;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.entity.Annuncio;

import java.io.IOException;
import java.util.List;

public interface AnnuncioDAO {

    List<Annuncio> selectAnnunciTot() throws IOException, CsvValidationException;
}
