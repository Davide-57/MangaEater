package it.ispw.mangaeater.boundary.jikan;

import it.ispw.mangaeater.exception.NoInternetConnectionException;
import it.ispw.mangaeater.internet_connection.CheckingInternetConnection;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.manga.Manga;
import net.sandrohc.jikan.model.manga.MangaType;

import java.util.Collection;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JikanBoundary {

    public String estraiDescrizioneManga(String nome) throws NoInternetConnectionException {
        CheckingInternetConnection checkInternet = new CheckingInternetConnection();
        checkInternet.checkInternetConnection();
        String descrizione = null;
        Jikan jikan = new Jikan();

        Collection<Manga> results = null;
        try {
            results = jikan.query().manga().search()
                    .query(nome)
                    .type(MangaType.MANGA)
                    .execute()
                    .collectList()
                    .block();
        } catch (JikanQueryException e) {
            Logger logger = Logger.getLogger(JikanBoundary.class.getName());
            logger.log(Level.WARNING, "Errore durante la richiesta alle Jikan API");
        }

        for(Manga manga : Objects.requireNonNull(results)){
            if(manga.synopsis != null){
                descrizione = manga.synopsis;
                break;
            }

        }

        return descrizione;
    }

}
