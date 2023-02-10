package it.ispw.mangaeater.controller_grafici.cli;

import com.opencsv.exceptions.CsvException;
import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.pagamento.Pagamento;
import it.ispw.mangaeater.dao.DbConnection;
import it.ispw.mangaeater.exception.EmailNotFoundException;
import it.ispw.mangaeater.exception.InsufficientCreditException;
import it.ispw.mangaeater.exception.SQLUtenteException;
import it.ispw.mangaeater.exception.UserNotLoggedException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    // se procedi == true allora è stato inserito il comando "procedi" che permette di concludere il pagamento
    private boolean procedi = false;


    public PagamentoCompraProdCliController(Pagamento controllerPagamento, DettaglioAnnuncioCliController dettaglioControllerGrafico) {
        this.controllerPagamento = controllerPagamento;
        this.dettaglioControllerGrafico = dettaglioControllerGrafico;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            comandiText.setWrapText(true);
            comandiText.setText("""
                Di seguito sono elencati i comandi disponibili:
                1) Per tornare al dettaglio dell'annuncio inserisci "back"
                2) Per procedere all'acquisto del prodotto inserisci "procedi"
                3) Dopo aver inserito "procedi" puoi concludere il pagamento inserendo il comando "compra"
                """);
            valorizzaInfoPagamento();

        } catch (UserNotLoggedException e) {

            apriLogin();
            disabilitaViewPagamento();

        }

    }

    private void disabilitaViewPagamento() {

        outputText.setWrapText(true);
        outputText.setText("""
                ATTENZIONE:
                Per procedere al pagamento occorre effettuare il Login.
                Una volta effettuato, tornare indietro al dettaglio dell'annuncio inserendo un qualsiasi carattere e riprovare l'acquisto.
                """);
        inputText.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                backDettaglioAnnuncio();
            }

                });

    }

    private void apriLogin() {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login-cli.fxml")));
            // viene passato il controller applicativo dell'acquisto di un prodotto per settare eventualmente la Sessione con l'utente che si loggerà
            loader.setControllerFactory(aClass -> new LoginCliController(controllerPagamento.creaLoginController()));
            Stage stage = new Stage();
            stage.setTitle("Manga Eater - Login Cli");
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(PagamentoCompraProdCliController.class.getName());
            logger.log(Level.WARNING, "Errore nell apertura di un file FXML");
        }

    }

    private void valorizzaInfoPagamento() throws UserNotLoggedException {

        InfoPagamentoBean infoPagamentoBean = controllerPagamento.estraiInfoPagamento();

        outputText.appendText("TITOLO: " + infoPagamentoBean.getTitoloAnnuncio() +
                "\n\nEMAIL VENDITORE: " + infoPagamentoBean.getEmailVenditore() +
                "\n\nCOSTO: " + infoPagamentoBean.getCosto() + "€" +
                "\n\nSALDO: " + infoPagamentoBean.getSaldoAcquirente() + "€");

        image.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/" + infoPagamentoBean.getIdAnnuncio() + ".jpg"))));

    }


    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio" mentre la inputText è selezionata
            String input = inputText.getText();
            switch (input) {
                case "back" -> backDettaglioAnnuncio();
                case "procedi" -> procediAlPagamento();
                case "compra" -> {
                    if(procedi){
                        concludiPagamento();
                    }
                    else{
                        comandiText.appendText("""
                                
                                ATTENZIONE: Non puoi utilizzare il comando "compra" senza inserire prima il comando "procedi".
                                """);
                    }
                }
                default -> comandiText.appendText("\nATTENZIONE: comando non esistente.");
            }
            inputText.clear();
        }

    }

    private void concludiPagamento() {
        try{
            controllerPagamento.finalizzaPagamento();
        }
        catch (EmailNotFoundException e) {
            String titoloErrore = "ERRORE INVIO EMAIL";
            String descrizioneErrore = "Errore interno del sistema: email errate.\nContattare il gestore del sistema all'email gestore.mangaeater@gmail.com";
            mostraErrore(titoloErrore, descrizioneErrore);
        } catch (InsufficientCreditException e) {
            String titoloErrore = "SALDO INSUFFICIENTE";
            String descrizioneErrore = "Prima di procedere con l'acquisto occorre ricaricare il conto MangaEater.";
            mostraErrore(titoloErrore, descrizioneErrore);
        } catch (SQLUtenteException | CsvException | IOException e) {
            String titoloErrore = "ERRORE INTERNO SISTEMA";
            String descrizioneErrore = "Errore interno del sistema: dispositivi di I/O non raggiungibile.\nContattare il gestore del sistema all'email gestore.mangaeater@gmail.com";
            mostraErrore(titoloErrore, descrizioneErrore);
        }

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("home-cli.fxml")));

            UtenteBeanFromController utenteLoggatoBean = controllerPagamento.getUtenteLoggatoBean();
            loader.setControllerFactory(aClass -> new HomeCliController(utenteLoggatoBean));

            Parent root = loader.load();
            Stage stage = (Stage)inputText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater Cli");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(DbConnection.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    private void mostraErrore(String titolo, String descrizione){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(titolo);
        alert.setContentText(descrizione);
        alert.showAndWait();
    }

    private void procediAlPagamento() {
        procedi = true;
        comandiText.appendText("""
                
                Ora puoi inserire il comando "compra".
                """);

    }

    private void backDettaglioAnnuncio() {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("dettaglio-annuncio-cli.fxml")));

            loader.setControllerFactory(aClass -> dettaglioControllerGrafico);

            Parent root = loader.load();
            Stage stage = (Stage)inputText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater - Dettaglio annuncio");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(PagamentoCompraProdCliController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }

    }

}
