package it.ispw.mangaeater.decorator_pattern;

import com.opencsv.exceptions.CsvValidationException;
import it.ispw.mangaeater.dao.AnnuncioDAO;
import it.ispw.mangaeater.dao.AnnuncioDAOJDBC;
import it.ispw.mangaeater.entity.Annuncio;

import java.io.IOException;
import java.util.List;

public class FiltroStandard extends FiltroAnnunci{

    //con questo attributo identifico l'ordine che deve essere dato alla lista da estrarre. Di default è per ID
    private OrdineAnnunci ordineAnnunci = OrdineAnnunci.ID;

    @Override
    public List<Annuncio> visualizzaAnnunci() {
        try{
            //può essere scelto il tipo di DAO (CSV o DBMS). Da mettere una scelta randomica dei due
            AnnuncioDAO annuncioDAO = new AnnuncioDAOJDBC();
            listaAnnunci = annuncioDAO.selectAnnunciOrdinati(ordineAnnunci);

        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return listaAnnunci;
    }

    //questo metodo serve a cambiare l'ordinamento della lista
    public void setOrdineAnnunci(OrdineAnnunci ordineAnnunci) {

        this.ordineAnnunci = ordineAnnunci;
    }

}
