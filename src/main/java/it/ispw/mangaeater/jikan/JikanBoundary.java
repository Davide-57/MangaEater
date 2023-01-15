package it.ispw.mangaeater.jikan;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.manga.Manga;
import net.sandrohc.jikan.model.manga.MangaType;

import java.util.Collection;
import java.util.Objects;

public class JikanBoundary {

    public String estraiDescrizioneManga(String nome){
        String descrizione = null;
        Jikan jikan = new Jikan();

        Collection<Manga> results;
        try {
            results = jikan.query().manga().search()
                    .query(nome)
                    .type(MangaType.MANGA)
                    .execute()
                    .collectList()
                    .block();
        } catch (JikanQueryException e) {
            throw new RuntimeException(e);
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
