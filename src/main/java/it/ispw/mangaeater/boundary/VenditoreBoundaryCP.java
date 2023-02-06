package it.ispw.mangaeater.boundary;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VenditoreBoundaryCP implements BoundaryEmail{

    private String emailVenditore;

    public VenditoreBoundaryCP(String emailVenditore) {
        this.emailVenditore = emailVenditore;
    }

    @Override
    public void inviaEmail(){

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dataCorrente = LocalDateTime.now();
        String testoEmail = """
                OGGETTO: MangaEater --> Un tuo prodotto è stato acquistato!
                DATA INVIO EMAIL: """ + formatoData.format(dataCorrente) +
                """
                TESTO EMAIL:
                L'annuncio contenente X è stato acquistato.
                Per inviare informazioni all'acquirente puoi scrivere alla sua email X.
                """;
        try{
            BufferedReader bufferedReader = new BufferedReader(new StringReader(testoEmail));
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/emailDummy/venditore.txt")));


            //da finire

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
