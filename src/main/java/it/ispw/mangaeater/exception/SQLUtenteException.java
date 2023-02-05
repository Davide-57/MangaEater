package it.ispw.mangaeater.exception;

public class SQLUtenteException extends  Exception{

    public SQLUtenteException(String message) {
        super("L'errore deriva da un errore durante la lettura/scrittura su DB per informazioni dell'utente. \nMessaggio originale: "+message);
    }
}
