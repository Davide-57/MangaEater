package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.controller_grafici.HomeController;
import it.ispw.mangaeater.decorator_pattern.FiltroAnnunci;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
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

    private final ComprareProdotto cp;

    private List<AnnuncioBean> listaAnnunciBean;

    private boolean filtroPerTitolo = false;

    private static final String SEPARATORE = "--------------------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------------------------";


    public HomeCliController() {
        this.cp = new ComprareProdotto();
    }

    public HomeCliController(ComprareProdotto cp) {
        this.cp = cp;
    }

    public HomeCliController(UtenteBeanFromController utenteBean) {
        this.cp = new ComprareProdotto();
        cp.setUtenteLoggato(utenteBean);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.setWrapText(true);
        mostraComandi();

        listaAnnunciBean = cp.estraiAnnunciTot();
        inizializzaLista();

    }

    private void mostraComandi() {

        outputText.appendText("""
                Benvenuto nell'app MangaEater.
                Di seguito sono elencate delle indicazioni ed i comandi disponibili per interagire con il programma:
                1) Ogni tipo di input va inserito nella barra in basso
                2) Per entrare nel dettaglio di un annuncio inserire il suo ID
                3) Per effettuare il login o il logout, inserire il comando "log"
                4) Per ordinare in base alla data di inserimento dell'annuncio inserire "ord-ins"
                5) Per ordinare in base al titolo dell'annuncio inserire "ord-tit"
                6) Per ordinare in base al costo crescente del prodotto inserire "ord-cc"
                7) Per ordinare in base al costo decrescente del prodotto inserire "ord-cd"
                8) Per filtrare in base alla categoria inserire il comando "fil-cat-" seguito dalla categoria, la quale può essere: shonen, kodomo, shoujo, josei, seinen
                9) Per filtrare in base al titolo inserire il comando "fil-tit". Tale comando avvierà la modalità di filtro in base al titolo con la quale il prossimo testo che viene inserito verrà considerato dal programma per il filtro degli annunci.
                10) Per rimuovere tutti i filtri e ordinamenti, inserire il comando "reset"
                11) Per  visualizzare di nuovo i comandi disponibili inserisci "cmd"
                
                I comandi sono terminati, di seguito verranno visualizzati gli annunci disponibili.
                
                """ + SEPARATORE + "\n" + SEPARATORE);
    }


    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio" mentre la inputText è selezionata
            String input = inputText.getText();
            if(!filtroPerTitolo){
                modalitaNormale(input);
            }
            else{
                modalitaFiltroPerTitolo(input);
            }
            inputText.clear();
        }

    }

    private void modalitaFiltroPerTitolo(String input) {
        filtroPerTitolo = false;
        listaAnnunciBean = cp.estraiAnnunciPerTitolo(input);
        inizializzaLista();
    }

    private void modalitaNormale(String input) {
        switch (input) {
            //login
            case "log" -> startLogin();
            //ordinamento
            case "ord-ins" -> {
                listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.ID);
                inizializzaLista();
            }
            case "ord-tit" -> {
                listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.TITOLO);
                inizializzaLista();
            }
            case "ord-cc" -> {
                listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.COSTO_CRESCENTE);
                inizializzaLista();
            }
            case "ord-cd" -> {
                listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.COSTO_DECRESCENTE);
                inizializzaLista();
            }

            //filtro per categoria
            case "fil-cat-shonen" -> {
                listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHONEN);
                inizializzaLista();
            }
            case "fil-cat-kodomo" -> {
                listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.KODOMO);
                inizializzaLista();
            }
            case "fil-cat-shoujo" -> {
                listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHOUJO);
                inizializzaLista();
            }
            case "fil-cat-josei" -> {
                listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.JOSEI);
                inizializzaLista();
            }
            case "fil-cat-seinen" -> {
                listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SEINEN);
                inizializzaLista();
            }

            //avvio modalità filtro per testo
            case "fil-tit" -> {
                filtroPerTitolo = true;
                outputText.appendText("Modalità filtro per titolo avviata.\nInserire un parametro di ricerca.\n\n");
            }

            //reset di tutti i filtri
            case "reset" -> {
                listaAnnunciBean = cp.rimuoviFiltri();
                inizializzaLista();
            }

            //visualizza i comandi disponibili
            case "cmd" -> {
                outputText.appendText("\n" + SEPARATORE);
                mostraComandi();
            }
            default -> {
                AnnuncioBean annuncioBean = verificaAnnuncio(input);
                if (annuncioBean != null) {
                    startDettaglioAnnuncio(annuncioBean);
                } else {
                    outputText.appendText(SEPARATORE + "\nINPUT ERRATO, COMANDO NON RILEVATO.\n" + SEPARATORE);
                }
            }
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
            outputText.appendText(SEPARATORE + SEPARATORE + "\n\n");
        }
    }
}
