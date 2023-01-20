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
                int utenteId = Integer.parseInt(line[UtenteIndiceAttributi.indexUtenteID]);
                String nome = line[UtenteIndiceAttributi.indexNome];
                String cognome = line[UtenteIndiceAttributi.indexCognome];
                String email = line[UtenteIndiceAttributi.indexEmail];
                String tipo = line[UtenteIndiceAttributi.indexTipo];
                String psw = line[UtenteIndiceAttributi.indexPassword];

                Utente utente = new Utente(utenteId, nome, cognome, email, tipo, psw);
                listaUtenti.add(utente);
            }

        }
        csvReader.close();

        return listaUtenti;
    }



    private static class UtenteIndiceAttributi {
        public static int indexUtenteID = 0;

        public static int indexNome = 1;

        public static int indexCognome = 2;

        public static int indexEmail = 3;

        public static int indexTipo = 4;

        public static int indexPassword = 5;
    }
}
