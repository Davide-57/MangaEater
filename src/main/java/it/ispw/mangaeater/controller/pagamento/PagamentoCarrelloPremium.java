package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

public class PagamentoCarrelloPremium implements  Pagamento{
    @Override
    public void estraiInfoPagamento() throws UserNotLoggedException {
        //questa funzionalità non è stata implementata
    }

    @Override
    public void finalizzaPagamento() throws InsufficientCreditException, SQLUtenteException, EmailNotFoundException {
        //questa funzionalità non è stata implementata
    }
}
