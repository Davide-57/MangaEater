package it.ispw.mangaeater.controller;

import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

public interface Pagamento {

    public void estraiInfoPagamento() throws UserNotLoggedException;

    public void finalizzaPagamento() throws InsufficientCreditException, SQLUtenteException, EmailNotFoundException;

}
