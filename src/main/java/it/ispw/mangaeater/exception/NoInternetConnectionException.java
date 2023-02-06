package it.ispw.mangaeater.exception;

public class NoInternetConnectionException extends Exception{

    public NoInternetConnectionException(String message) {
        super("Connessione internet assente.\nMessaggio originale: "+message);
    }

}
