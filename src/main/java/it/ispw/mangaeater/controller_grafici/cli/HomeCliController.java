package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.controller_grafici.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeCliController implements Initializable {

    @FXML
    private TextField inputText;

    @FXML
    private TextArea outputText;

    private ComprareProdotto cp;

    private List<AnnuncioBean> listaAnnunciBean;

    private static final String SEPARATORE = "-------------------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------------------------";


    public HomeCliController() {
        this.cp = new ComprareProdotto();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.setWrapText(true);
        mostraComandi();

        inizializzaLista();

    }

    private void mostraComandi() {

        outputText.appendText("""
                Benvenuto nell'app MangaEater.
                Di seguito verranno delle indicazioni ed i comandi disponibili per interagire con il programma:
                1) Ogni tipo di input va inserito nella barra in basso
                2) Per entrare nel dettaglio di un annuncio inserire il suo ID
                3) Per effettuare il login o il logout, inserire il comando "log"
                4) Per accedere alla modalità filtro, inserire il comando "fil"
                5) Dopo essere entrati nella modalità filtro, inserire il comando "tit" per filtrare in base al titolo e "cat" per filtrare in base alla categoria
                6) Per accedere alla modalità ordinamento, inserire il comando "ord"
                7) Per rimuovere tutti i filtri e ordinamenti, inserire il comando "reset"
                8) Per  visualizzare di nuovo i comandi disponibili inserisci "cmd"
                
                I comandi sono terminati, di seguito verranno visualizzati gli annunci disponibili.
                
                """ + SEPARATORE + "\n" + SEPARATORE);
    }


    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio" mentre la inputText è selezionata
            String input = inputText.getText();
            switch (input){
                case "log":
                    startLogin();
                    break;
                case "fil":
                    System.out.println("Filtro da fare");
                    break;
                case "ord":
                    System.out.println("Ordinamento da fare");
                    break;
                case "reset":
                    System.out.println("Reset filtro da fare");
                    break;
                case "cmd":
                    outputText.appendText("\n" + SEPARATORE);
                    mostraComandi();
                    break;
                default:
                    AnnuncioBean annuncioBean = verificaAnnuncio(input);
                    if(annuncioBean != null){
                        startDettaglioAnnuncio(annuncioBean);
                    }
                    else{
                        outputText.appendText(SEPARATORE + "\nINPUT ERRATO, COMANDO NON RILEVATO.\n" + SEPARATORE);
                    }
                    break;

            }

            inputText.clear();

        }

    }

    private void startDettaglioAnnuncio(AnnuncioBean annuncioBean) {
        try {
            cp.mostraDettaglioAnnuncio(annuncioBean);
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("dettaglio-annuncio-cli.fxml")));
            loader.setControllerFactory(aClass -> new DettaglioAnnuncioCliController(cp));
            Parent root = loader.load();
            Stage stage = (Stage)inputText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater - Dettaglio annuncio Cli");
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(HomeCliController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    private void startLogin() {
        try {
            if(!cp.isLogged()){
                //se l'utente non è loggato va fatto il login
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login-cli.fxml")));
                // viene passato il controller applicativo dell'acquisto di un prodotto per settare eventualmente la Sessione con l'utente che si loggerà
                loader.setControllerFactory(aClass -> new LoginCliController(cp.creaControllerLogin()));
                Stage stage = new Stage();
                stage.setTitle("Manga Eater - Login - Cli");
                stage.setScene(new Scene(loader.load()));
                stage.setResizable(false);
                stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            else{
                //altrimenti c'è un utente loggato e quindi si vuole effettuare il logout
                cp.effettuaLogout();
            }
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(HomeController.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    private AnnuncioBean verificaAnnuncio(String input) {
        AnnuncioBean annuncioBean = null;

        try {
            int inputInt = Integer.parseInt(input);

            for(AnnuncioBean a: listaAnnunciBean){
                if(a.getId() == inputInt){
                    annuncioBean = a;
                    break;
                }
            }
        }
        catch(Exception e){
            // nel caso in cui la variabile "input" non sia un numero
            Logger logger = Logger.getLogger(HomeCliController.class.getName());
            logger.log(Level.WARNING, "Input non corretto");
        }

        return annuncioBean;
    }


    private void inizializzaLista() {

        listaAnnunciBean = cp.estraiAnnunciTot();
        if(listaAnnunciBean.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("La ricerca non ha avuto risultati\nImmetere un altro filtro");
            alert.setTitle("NESSUN RISULTATO");
            alert.showAndWait();
        }
        else{
            for(AnnuncioBean annuncioBean: listaAnnunciBean){
                outputText.appendText("\n\n" +
                        "\nID annuncio: " + annuncioBean.getId() +
                        "\nTitolo: " + annuncioBean.getTitolo() +
                        "\nDescrizione: " + annuncioBean.getDescrizione() +
                        "\nCosto: " + annuncioBean.getCosto());
                outputText.appendText("\n\n" + SEPARATORE);
            }
        }
    }
}
