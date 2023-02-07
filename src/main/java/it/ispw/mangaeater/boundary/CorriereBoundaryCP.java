package it.ispw.mangaeater.boundary;

import it.ispw.mangaeater.exception.EmailNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CorriereBoundaryCP implements BoundaryEmail{

    private final String emailVenditore;

    private final String emailAcquirente;

    private static final String FILE_NAME = "src/main/resources/emailDummy/corriere.txt";

    private static final String terminatoreEmail = "\n\n--------------------------------------------------------------------------------------------------------\n\n";

    public CorriereBoundaryCP(String emailVenditore, String emailAcquirente){
        this.emailVenditore = emailVenditore;
        this.emailAcquirente = emailAcquirente;
    }

    @Override
    public void inviaEmail() throws EmailNotFoundException {

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dataCorrente = LocalDateTime.now();
        String testoEmail = "OGGETTO: MangaEater - Presto sarà necessaria una spedizione\n" +
                "DATA INVIO EMAIL: " + formatoData.format(dataCorrente) + "\n" +
                "TESTO EMAIL:\n" +
                "Vi comunichiamo che è appena stato bloccato un annuncio in data " + formatoData.format(dataCorrente) + "\n" +
                "Di seguito riportiamo l'email del venditore e l'email dell'acquirente.\n" +
                "Email venditore: " + emailVenditore + ".\n" +
                "Email acquirente: " + emailAcquirente + ".\n";
        try{

            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)));
            output.println(testoEmail);
            output.println(terminatoreEmail);

            output.close();

        } catch (IOException e) {
            throw new EmailNotFoundException(e.getMessage());
        }
    }
}
