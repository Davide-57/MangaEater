package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.jikan.JikanBoundary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DettaglioAnnuncioController implements Initializable{

    public DettaglioAnnuncioController(ComprareProdotto cp) {
        this.cp = cp;
    }

    @FXML
    private VBox allFrame;

    @FXML
    private Button buttonCompra;

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

    private ComprareProdotto cp;

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
        JikanBoundary jikanBoundary = new JikanBoundary();
        String descJikan = jikanBoundary.estraiDescrizioneManga(title.getText());
        descriptionJikan.setText(Objects.requireNonNullElse(descJikan, "Non è disponibile una descrizione dalla libreria Jikan"));
        //la seguente istruzione è utile a far andare accapo il testo
        descriptionJikan.setWrappingWidth(495);
    }

    @FXML
    void backHome(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("home.fxml")));
            loader.setControllerFactory(aClass -> new HomeController (cp));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater");

            //di seguito recupero il controller della home appena creato e gli invio l'istanza del controller applicativo ComprareProdotto
            //HomeController myController = loader.getController();
            //myController.setCp(cp);

            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void compraProdotto(MouseEvent event) {
        if(cp.isLogged()){
            //un utente si è loggato quindi avvio il caso d'uso Pagamento
        }
        else{
            //non si è ancora loggato nessun utente quindi avvio il caso d'uso Login
        }
    }
}
