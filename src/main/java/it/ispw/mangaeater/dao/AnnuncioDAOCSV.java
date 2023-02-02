package it.ispw.mangaeater.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAOCSV implements AnnuncioDAO {

    private final File fd;

    private static final String CSV_FILE_NAME = "src/main/resources/annunci/annunci.csv";

    public AnnuncioDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);
    }

    @Override
    public List<Annuncio> selectAnnunciTot() throws IOException, CsvValidationException {

        // create csvReader object passing file reader as a parameter
        CSVReader csvReader;
        csvReader = new CSVReaderBuilder(new BufferedReader(new FileReader(fd))).build();

        String[] line;

        List<Annuncio> listaAnnunci = new ArrayList<>();

        boolean firstLoop = true;
        while ((line = csvReader.readNext()) != null) {
            if(firstLoop){
                //scarto la prima riga perché è di intestazione
                firstLoop = false;
            }
            else{
                //le righe successive alla prima contengono le informazioni sugli annunci che vengono utilizzate per aggiungere un nuovo annuncio alla lista
                int annuncioId = Integer.parseInt(line[AnnuncioDAOCSV.AnnuncioIndiceAttributi.INDEX_ANNUNCIOID]);
                String titolo = line[AnnuncioIndiceAttributi.INDEX_TITOLO];
                String descrizione = line[AnnuncioIndiceAttributi.INDEX_DESCRIZIONE];
                double costo = Double.parseDouble(line[AnnuncioIndiceAttributi.INDEX_COSTO]);
                String venditoreEmail = line[AnnuncioIndiceAttributi.INDEX_VENDITOREEMAIL];
                CategoriaAnnuncio categoria = CategoriaAnnuncio.valueOf(line[AnnuncioIndiceAttributi.INDEX_CATEGORIA].toUpperCase());

                Annuncio annuncio = new Annuncio(annuncioId, titolo, descrizione, costo, venditoreEmail, categoria);
                listaAnnunci.add(annuncio);
            }

        }
        csvReader.close();

        return listaAnnunci;
    }


    private static class AnnuncioIndiceAttributi {
        public static final int INDEX_ANNUNCIOID = 0;

        public static final int INDEX_TITOLO = 1;

        public static final int INDEX_DESCRIZIONE = 2;

        public static final int INDEX_COSTO = 3;

        public static final int INDEX_VENDITOREEMAIL = 4;

        public static final int INDEX_CATEGORIA = 5;
    }
}
