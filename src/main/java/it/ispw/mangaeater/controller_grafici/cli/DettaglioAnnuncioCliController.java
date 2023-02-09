package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.boundary.jikan.JikanBoundary;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.exception.NoInternetConnectionException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DettaglioAnnuncioCliController implements Initializable {

    private final ComprareProdotto cp;

    @FXML
    private ImageView image;

    @FXML
    private TextField inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private TextArea comandiText;



    public DettaglioAnnuncioCliController(ComprareProdotto cp) {
        this.cp = cp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        outputText.setWrapText(true);
        valorizzaDettaglioAnnuncio();
        comandiText.setWrapText(true);
        comandiText.setText("""
                Di seguito sono elencati i comandi disponibili:
                1) Per tornare alla home inserisci "home"
                2) Per procedere all'acquisto del prodotto inserisci "compra"
                """);

    }

    private void valorizzaDettaglioAnnuncio() {

        AnnuncioBean annuncioBean = cp.getBeanDettaglioAnnuncio();
        outputText.appendText("TITOLO: " + annuncioBean.getTitolo() +
                "\n\nDESCRIZIONE: " + annuncioBean.getDescrizione() +
                "\n\nCOSTO: " + annuncioBean.getCosto() +
                "\n\nEMAIL VENDITORE: " + annuncioBean.getVenditoreEmail());

        image.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/"+annuncioBean.getId()+".jpg"))));

        //di seguito chiamo la libreria Jikan per prendere informazioni sulla trama del manga
        try{
            JikanBoundary jikanBoundary = new JikanBoundary();
            String descJikan = jikanBoundary.estraiDescrizioneManga(annuncioBean.getTitolo());

            outputText.appendText("\n\nDESCRIZIONE JIKAN: " + Objects.requireNonNullElse(descJikan, "Non è disponibile una descrizione dalla libreria Jikan"));

        } catch (NoInternetConnectionException e) {
            outputText.appendText("""


                    DESCRIZIONE JIKAN: Connessione internet assente.
                    Per visualizzare la descrizione fornita da Jikan connettersi ad una rete internet.
                    """);
        }

    }


    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio" mentre la inputText è selezionata
            String input = inputText.getText();
            switch (input) {
                case "home" -> backHome();
                case "compra" -> compraProdotto();
                default -> comandiText.appendText("\nATTENZIONE: comando non esistente.");
            }
            inputText.clear();
        }

    }

    private void compraProdotto() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("pagamento-cp-cli.fxml")));
            loader.setControllerFactory(aClass -> new PagamentoCompraProdCliController(cp.creaControllerPagamento(), this));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)inputText.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manga Eater - Conferma pagamento");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(DettaglioAnnuncioCliController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    private void backHome() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("home-cli.fxml")));
            loader.setControllerFactory(aClass -> new HomeCliController(cp));
            Parent root = loader.load();
            Stage stage = (Stage)inputText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(DettaglioAnnuncioCliController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

}
