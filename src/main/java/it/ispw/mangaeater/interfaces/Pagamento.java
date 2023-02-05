package it.ispw.mangaeater.interfaces;

import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

public interface Pagamento {

    public void estraiInfoPagamento() throws UserNotLoggedException;

    public void paga() throws InsufficientCreditException, SQLUtenteException;

    public void concludiPagamento();
}
