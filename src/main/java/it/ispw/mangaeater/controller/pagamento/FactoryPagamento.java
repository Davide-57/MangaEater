package it.ispw.mangaeater.controller.pagamento;

import it.ispw.mangaeater.controller.ComprareProdotto;

public class FactoryPagamento {

    private static FactoryPagamento factoryPagamento = null;

    private FactoryPagamento() {
    }

    public static synchronized FactoryPagamento getFactoryPagamento() {
        if(factoryPagamento == null) factoryPagamento = new FactoryPagamento();
        return factoryPagamento;
    }

    public PagamentoCompraProdotto getPagamentoCompraProdotto(ComprareProdotto cp){
        return new PagamentoCompraProdotto(cp);
    }

    public PagamentoCarrelloPremium getPagamentoCarrelloPremium(){
        return new PagamentoCarrelloPremium();
    }

    public PagamentoPremium getPagamentoPremium(){
        return new PagamentoPremium();
    }
}
