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

    private File fd;

    private static final String CSV_FILE_NAME = "src/main/resources/utenze/utenti.CSV";

    public UtenteDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);

        if (!fd.exists()) {
            fd.createNewFile();
        }
    }

    @Override
    public List<Utente> selectUtentiTot() throws CsvValidationException, IOException {

        // create csvReader object passing file reader as a parameter
        CSVReader csvReader;
        csvReader = new CSVReaderBuilder(new BufferedReader(new FileReader(fd))).build();

        String[] record;

        List<Utente> listaUtenti = new ArrayList<>();

        boolean firstLoop = true;
        while ((record = csvReader.readNext()) != null) {
            if(firstLoop){
                //scarto la prima riga perché è di intestazione
                firstLoop = false;
            }
            else{
                //le righe successive alla prima contengono le informazioni sugli utenti che vengono utilizzate per aggiungere un nuovo utente alla lista
                int utenteId = Integer.valueOf(record[UtenteIndiceAttributi.getIndex_UtenteID()]);
                String nome = record[UtenteIndiceAttributi.getIndex_Nome()];
                String cognome = record[UtenteIndiceAttributi.getIndex_Cognome()];
                String email = record[UtenteIndiceAttributi.getIndex_Email()];
                String tipo = record[UtenteIndiceAttributi.getIndex_Tipo()];
                String psw = record[UtenteIndiceAttributi.getIndex_Password()];

                Utente utente = new Utente(utenteId, nome, cognome, email, tipo, psw);
                listaUtenti.add(utente);
            }

        }
        csvReader.close();

        return listaUtenti;
    }



    private static class UtenteIndiceAttributi {
        public static int getIndex_UtenteID() {
            return 0;
        }

        public static int getIndex_Nome() {
            return 1;
        }

        public static int getIndex_Cognome() {
            return 2;
        }

        public static int getIndex_Email() {
            return 3;
        }

        public static int getIndex_Tipo() {
            return 4;
        }

        public static int getIndex_Password() {
            return 5;
        }
    }
}
