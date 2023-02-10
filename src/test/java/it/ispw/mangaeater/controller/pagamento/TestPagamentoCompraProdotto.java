package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.entity.Annuncio;
import it.ispw.mangaeater.entity.Utente;
import it.ispw.mangaeater.exception.UserNotLoggedException;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPagamentoCompraProdotto {

    @Test
    void testCalcolaNuovoSaldo() {
        ComprareProdotto comprareProdotto = new ComprareProdotto(new Annuncio(1, "test", "test", 15, "test", CategoriaAnnuncio.SHONEN));
        comprareProdotto.setUtenteLoggato(new Utente(1, "test", "test", "test", "test", "test", 100));
        PagamentoCompraProdotto pagamentoCompraProdotto = new PagamentoCompraProdotto(comprareProdotto);
        try {
            pagamentoCompraProdotto.estraiInfoPagamento();
        } catch (UserNotLoggedException e) {
            Logger logger = Logger.getLogger(TestPagamentoCompraProdotto.class.getName());
            logger.log(Level.WARNING, "Errore durante test: utente non loggato");
        }
        double actualSaldo = pagamentoCompraProdotto.calcolaNuovoSaldo();
        assertEquals(85.0, actualSaldo, 0);
    }
}