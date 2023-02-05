package it.ispw.mangaeater.interfaces;

import it.ispw.mangaeater.exception.UserNotLoggedException;

public interface Pagamento {

    public void estraiInfoPagamento() throws UserNotLoggedException;

    public void paga();

    public void concludiPagamento();
}
