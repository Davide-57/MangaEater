package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.Login;
import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

public class PagamentoPremium implements  Pagamento{
    @Override
    public InfoPagamentoBean estraiInfoPagamento() throws UserNotLoggedException {
        //questa funzionalità non è stata implementata
        return null;
    }

    @Override
    public void finalizzaPagamento() throws InsufficientCreditException, SQLUtenteException, EmailNotFoundException {
        //questa funzionalità non è stata implementata
    }

    @Override
    public Login creaLoginController() {
        //questa funzionalità non è stata implementata
        return null;
    }

    public UtenteBeanFromController getUtenteLoggatoBean() {
        //questa funzionalità non è stata implementata
        return null;
    }
}
