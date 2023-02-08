package it.ispw.mangaeater.controller_grafici;

import it.ispw.mangaeater.controller.pagamento.PagamentoCompraProdotto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PagamentoCPController {

    @FXML
    private Button confermaButton;

    @FXML
    private Text costo;

    @FXML
    private Text emailVend;

    @FXML
    private ImageView image;

    @FXML
    private ImageView imgBackDettaglio;

    @FXML
    private Text saldo;

    @FXML
    private Label titolo;

    private PagamentoCompraProdotto pcp;



    public PagamentoCPController(PagamentoCompraProdotto pcp) {
        this.pcp = pcp;
    }

    @FXML
    void backHome(MouseEvent event) {

    }

    @FXML
    void procediAlPagamento() {
        confermaButton.setStyle("""
                -fx-text-fill: #ffb84d;
                -fx-border-color: #ffb84d;
                -fx-background-color: white;
                -fx-border-radius: 20;
                -fx-background-radius: 20;
                """);

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.web("#ffb84d"));
        innerShadow.setWidth(56.17);
        innerShadow.setHeight(47.38);
        innerShadow.setRadius(25.39);
        confermaButton.setEffect(innerShadow);

        confermaButton.setOnMouseClicked(event -> {

        });
    }

}
