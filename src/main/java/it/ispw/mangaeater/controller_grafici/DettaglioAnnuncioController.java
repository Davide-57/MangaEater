package it.ispw.mangaeater.controller_grafici;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.boundary.jikan.JikanBoundary;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.exception.NoInternetConnectionException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DettaglioAnnuncioController implements Initializable{

    @FXML
    private Text cost;

    @FXML
    private Text description;

    @FXML
    private Text descriptionJikan;

    @FXML
    private Text emailVend;

    @FXML
    private ImageView image;

    @FXML
    private Label title;

    private final ComprareProdotto cp;

    public DettaglioAnnuncioController(ComprareProdotto cp) {
        this.cp = cp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnnuncioBean annuncioBean = cp.getBeanDettaglioAnnuncio();
        title.setText(annuncioBean.getTitolo());
        cost.setText(annuncioBean.getCosto()+"€");
        description.setText(annuncioBean.getDescrizione());
        //la seguente istruzione è utile a far andare accapo il testo
        description.setWrappingWidth(495);
        emailVend.setText(annuncioBean.getVenditoreEmail());
        image.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/"+annuncioBean.getId()+".jpg"))));

        //di seguito chiamo la libreria Jikan per prendere informazioni sulla trama del manga
        try{
            JikanBoundary jikanBoundary = new JikanBoundary();
            String descJikan = jikanBoundary.estraiDescrizioneManga(title.getText());
            descriptionJikan.setText(Objects.requireNonNullElse(descJikan, "Non è disponibile una descrizione dalla libreria Jikan"));
            //la seguente istruzione è utile a far andare accapo il testo
            descriptionJikan.setWrappingWidth(495);
        } catch (NoInternetConnectionException e) {
            descriptionJikan.setText("""
                    Connessione internet assente.
                    Per visualizzare la descrizione fornita da Jikan connettersi\s
                    ad una rete internet.""");
        }

    }

    @FXML
    void backHome(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("home.fxml")));
            loader.setControllerFactory(aClass -> new HomeController(cp));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(DettaglioAnnuncioController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    @FXML
    void compraProdotto(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("pagamento-cp.fxml")));
            loader.setControllerFactory(aClass -> new PagamentoCompraProdController(cp.creaControllerPagamento(), this));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manga Eater - Conferma pagamento");

            stage.show();
        }
        catch (IOException ex) {
            Logger logger = Logger.getLogger(DettaglioAnnuncioController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }

    }
}
