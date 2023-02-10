package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.Login;
import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;

public interface Pagamento {

    public InfoPagamentoBean estraiInfoPagamento() throws UserNotLoggedException;

    public void finalizzaPagamento() throws InsufficientCreditException, SQLUtenteException, EmailNotFoundException;

    public Login creaLoginController();

    public UtenteBeanFromController getUtenteLoggatoBean();

}
