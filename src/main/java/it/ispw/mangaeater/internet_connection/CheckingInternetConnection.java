package it.ispw.mangaeater.internet_connection;

import it.ispw.mangaeater.exception.NoInternetConnectionException;

import java.net.URL;
import java.net.URLConnection;

public class CheckingInternetConnection {

    public CheckingInternetConnection() {
        //no parametri da settare
    }

    public void checkInternetConnection() throws NoInternetConnectionException {
        try {

            URL url = new URL("https://www.google.it/");
            URLConnection connection = url.openConnection();
            connection.connect();

        }
        catch (Exception e) {
            throw new NoInternetConnectionException(e.getMessage());
        }
    }
}
