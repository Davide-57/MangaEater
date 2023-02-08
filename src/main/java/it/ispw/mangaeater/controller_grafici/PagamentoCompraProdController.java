package it.ispw.mangaeater.controller_grafici;

import com.opencsv.exceptions.CsvException;
import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.InfoPagamentoBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.pagamento.Pagamento;
import it.ispw.mangaeater.dao.DbConnection;
import it.ispw.mangaeater.exception.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PagamentoCompraProdController implements Initializable {

    @FXML
    private Text avvisoText;

    @FXML
    private Button confermaButton;

    @FXML
    private Text costo;

    @FXML
    private Text emailVend;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hbox3;

    @FXML
    private HBox hbox4;

    @FXML
    private ImageView image;

    @FXML
    private Text saldo;

    @FXML
    private Label titolo;

    private final Pagamento controllerPagamento;

    //riferimento al controller grafico del dettaglio
    private final DettaglioAnnuncioController dettaglioControllerGrafico;


    public PagamentoCompraProdController(Pagamento controllerPagamento, DettaglioAnnuncioController dettaglioControllerGrafico) {
        this.controllerPagamento = controllerPagamento;
        this.dettaglioControllerGrafico = dettaglioControllerGrafico;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            InfoPagamentoBean infoPagamentoBean = controllerPagamento.estraiInfoPagamento();

            image.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/" + infoPagamentoBean.getIdAnnuncio() + ".jpg"))));
            titolo.setText(infoPagamentoBean.getTitoloAnnuncio());
            emailVend.setText(infoPagamentoBean.getEmailVenditore());
            costo.setText("" + infoPagamentoBean.getCosto());
            saldo.setText("" + infoPagamentoBean.getSaldoAcquirente());

        } catch (UserNotLoggedException e) {

            apriLogin();
            disabilitaViewPagamento();

        }

    }

    private void apriLogin() {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login.fxml")));
            // viene passato il controller applicativo dell'acquisto di un prodotto per settare eventualmente la Sessione con l'utente che si loggerà
            loader.setControllerFactory(aClass -> new LoginController(controllerPagamento.creaLoginController()));
            Stage stage = new Stage();
            stage.setTitle("Manga Eater - Login");
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(DbConnection.class.getName());
            logger.log(Level.WARNING, "Errore nell'apertura di un file FXML");
        }
    }

    private void disabilitaViewPagamento() {

        avvisoText.setText("Per procedere al pagamento occorre effettuare il Login.\n" +
                "Una volta effettuato, tornare indietro al dettaglio dell'annuncio e riprovare l'acquisto.");

        hbox1.setDisable(true);
        hbox2.setDisable(true);
        hbox3.setDisable(true);
        hbox4.setDisable(true);
        hbox1.setVisible(false);
        hbox2.setVisible(false);
        hbox3.setVisible(false);
        hbox4.setVisible(false);

    }


    @FXML
    void backDettaglioAnnuncio(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("dettaglio-annuncio.fxml")));

            loader.setControllerFactory(aClass -> dettaglioControllerGrafico);

            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater - Dettaglio annuncio");

            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

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
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("home.fxml")));

                UtenteBeanFromController utenteLoggatoBean = controllerPagamento.getUtenteLoggatoBean();
                loader.setControllerFactory(aClass -> new HomeController(utenteLoggatoBean));

                Parent root = loader.load();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Manga Eater");

                stage.show();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

        });
    }

    private void mostraErrore(String titolo, String descrizione){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(titolo);
        alert.setContentText(descrizione);
        alert.showAndWait();
    }

}
