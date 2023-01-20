package it.ispw.mangaeater.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.entity.Annuncio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAOCSV implements AnnuncioDAO {

    private File fd;

    private static final String CSV_FILE_NAME = "src/main/resources/utenze/annunci.csv";

    public AnnuncioDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);
    }

    @Override
    public List<Annuncio> selectAnnunciTot() throws IOException, CsvValidationException {

        // create csvReader object passing file reader as a parameter
        CSVReader csvReader;
        csvReader = new CSVReaderBuilder(new BufferedReader(new FileReader(fd))).build();

        String[] record;

        List<Annuncio> listaAnnunci = new ArrayList<>();

        boolean firstLoop = true;
        while ((record = csvReader.readNext()) != null) {
            if(firstLoop){
                //scarto la prima riga perché è di intestazione
                firstLoop = false;
            }
            else{
                //le righe successive alla prima contengono le informazioni sugli annunci che vengono utilizzate per aggiungere un nuovo annuncio alla lista
                int annuncioId = Integer.valueOf(record[AnnuncioDAOCSV.AnnuncioIndiceAttributi.getIndex_AnnuncioID()]);
                String titolo = record[AnnuncioIndiceAttributi.getIndex_Titolo()];
                String descrizione = record[AnnuncioIndiceAttributi.getIndex_Descrizione()];
                double costo = Double.valueOf(record[AnnuncioIndiceAttributi.getIndex_Costo()]);
                String vendtoreEmail = record[AnnuncioIndiceAttributi.getIndex_VenditoreEmail()];

                Annuncio annuncio = new Annuncio(annuncioId, titolo, descrizione, costo, vendtoreEmail);
                listaAnnunci.add(annuncio);
            }

        }
        csvReader.close();

        return listaAnnunci;
    }


    private static class AnnuncioIndiceAttributi {
        public static int getIndex_AnnuncioID() {
            return 0;
        }

        public static int getIndex_Titolo() {
            return 1;
        }

        public static int getIndex_Descrizione() {
            return 2;
        }

        public static int getIndex_Costo() {
            return 3;
        }

        public static int getIndex_VenditoreEmail() {
            return 4;
        }
    }
}
