package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.controller.pagamento.Pagamento;
import it.ispw.mangaeater.exception.UserNotLoggedException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PagamentoCompraProdCliController implements Initializable {

    private final Pagamento controllerPagamento;

    //riferimento al controller grafico del dettaglio
    private final DettaglioAnnuncioCliController dettaglioControllerGrafico;

    @FXML
    private TextArea comandiText;

    @FXML
    private ImageView image;

    @FXML
    private TextField inputText;

    @FXML
    private TextArea outputText;


    public PagamentoCompraProdCliController(Pagamento controllerPagamento, DettaglioAnnuncioCliController dettaglioControllerGrafico) {
        this.controllerPagamento = controllerPagamento;
        this.dettaglioControllerGrafico = dettaglioControllerGrafico;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            InfoPagamentoBean infoPagamentoBean = controllerPagamento.estraiInfoPagamento();



            image.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/" + infoPagamentoBean.getIdAnnuncio() + ".jpg"))));
            /*titolo.setText(infoPagamentoBean.getTitoloAnnuncio());
            emailVend.setText(infoPagamentoBean.getEmailVenditore());
            costo.setText("" + infoPagamentoBean.getCosto());
            saldo.setText("" + infoPagamentoBean.getSaldoAcquirente());*/

        } catch (UserNotLoggedException e) {

            /*apriLogin();
            disabilitaViewPagamento();*/

        }

    }


    @FXML
    void ottieniInput(KeyEvent event) {

    }

}
