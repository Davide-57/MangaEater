package it.ispw.mangaeater.boundary;

import it.ispw.mangaeater.exception.EmailNotFoundException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VenditoreBoundaryCP implements BoundaryEmail{

    private String emailVenditore;
    
    private String titoloAnnuncio;

    private String emailAcquirente;

    private final String FILE_NAME = "src/main/resources/emailDummy/venditore.txt";

    private final String terminatoreEmail = "\n\n--------------------------------------------------------------------------------------------------------\n\n";

    public VenditoreBoundaryCP(String titoloAnnuncio, String emailVenditore, String emailAcquirente) {
        this.titoloAnnuncio = titoloAnnuncio;
        this.emailVenditore = emailVenditore;
        this.emailAcquirente = emailAcquirente;
    }

    @Override
    public void inviaEmail() throws EmailNotFoundException {

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dataCorrente = LocalDateTime.now();
        String testoEmail = "DESTINATARIO: " + emailVenditore + "\n" +
                "OGGETTO: MangaEater - Un tuo prodotto è stato acquistato!\n" +
                "DATA INVIO EMAIL: " + formatoData.format(dataCorrente) + "\n" +
                "TESTO EMAIL:\n" +
                "L'annuncio contenente " + titoloAnnuncio + " è stato acquistato.\n" +
                "Per inviare informazioni all'acquirente puoi contattarlo all'email " + emailAcquirente + ".\n";
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
