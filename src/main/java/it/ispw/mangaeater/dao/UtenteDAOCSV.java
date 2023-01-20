package it.ispw.mangaeater.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.entity.Utente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOCSV implements UtenteDAO{

    private final File fd;

    private static final String CSV_FILE_NAME = "src/main/resources/utenze/utenti.CSV";

    public UtenteDAOCSV(){
        this.fd = new File(CSV_FILE_NAME);
    }

    @Override
    public List<Utente> selectUtentiTot() throws CsvValidationException, IOException {

        // create csvReader object passing file reader as a parameter
        CSVReader csvReader;
        csvReader = new CSVReaderBuilder(new BufferedReader(new FileReader(fd))).build();

        String[] line;

        List<Utente> listaUtenti = new ArrayList<>();

        boolean firstLoop = true;
        while ((line = csvReader.readNext()) != null) {
            if(firstLoop){
                //scarto la prima riga perché è di intestazione
                firstLoop = false;
            }
            else{
                //le righe successive alla prima contengono le informazioni sugli utenti che vengono utilizzate per aggiungere un nuovo utente alla lista
                int utenteId = Integer.parseInt(line[UtenteIndiceAttributi.INDEX_UTENTEID]);
                String nome = line[UtenteIndiceAttributi.INDEX_NOME];
                String cognome = line[UtenteIndiceAttributi.INDEX_COGNOME];
                String email = line[UtenteIndiceAttributi.INDEX_EMAIL];
                String tipo = line[UtenteIndiceAttributi.INDEX_TIPO];
                String psw = line[UtenteIndiceAttributi.INDEX_PASSWORD];

                Utente utente = new Utente(utenteId, nome, cognome, email, tipo, psw);
                listaUtenti.add(utente);
            }

        }
        csvReader.close();

        return listaUtenti;
    }



    private static class UtenteIndiceAttributi {
        public static final int INDEX_UTENTEID = 0;

        public static final int INDEX_NOME = 1;

        public static final int INDEX_COGNOME = 2;

        public static final int INDEX_EMAIL = 3;

        public static final int INDEX_TIPO = 4;

        public static final int INDEX_PASSWORD = 5;
    }
}
