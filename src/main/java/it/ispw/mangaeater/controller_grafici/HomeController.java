package it.ispw.mangaeater.controller_grafici;

import it.ispw.mangaeater.MangaEater;
import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.bean.SessioneBean;
import it.ispw.mangaeater.bean.UtenteBeanFromController;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.dao.DbConnection;
import it.ispw.mangaeater.decorator_pattern.FiltroAnnunci;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import it.ispw.mangaeater.observer_pattern.Observer;
import it.ispw.mangaeater.observer_pattern.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController implements Initializable, Observer {

    //questo costruttore è quello richiamato dal main
    //per questo motivo dovrà essere creata una nuova sessione e impostata come Subject di questa istanza
    public HomeController() {
        //creo il controller applicativo
        this.cp = new ComprareProdotto();
        this.setSubject(new SessioneBean(this));
        cp.setObserverSessione(sessioneBean);
    }

    //questo costruttore viene richiamato dal dettaglioAnnuncioController
    //dato che già esiste una sessione la devo richiamare dal controller applicativo
    public HomeController(ComprareProdotto cp) {
        //recupero il controller applicativo
        this.cp = cp;
        this.setSubject(new SessioneBean(this));
        // avendo ricreato un nuovo SessioneBean verrà resettato l'Observer della Sessione per poi associargli quello nuovo
        cp.resetObserverSessione();
        cp.setObserverSessione(sessioneBean);
    }

    //costruttore richiamato da PagamentoCompraProdController quando si termina un pagamento
    public HomeController(UtenteBeanFromController utenteBean) {
        this.cp = new ComprareProdotto();
        // avendo acquistato un prodotto significa che un utente è loggato, quindi viene settato nella sessione (appena ricreata) prima di attivare gli Observer
        cp.setUtenteLoggato(utenteBean);
        this.setSubject(new SessioneBean(this));
        cp.setObserverSessione(sessioneBean);
    }

    private final ObservableList<Card> list = FXCollections.observableArrayList();

    @FXML
    private GridPane cardHolder;

    @FXML
    private MenuButton categorieButton;

    @FXML
    private MenuButton iconaOrdinamento;

    @FXML
    private TextField searchBar;

    @FXML
    private TextField loginButton;

    private final ComprareProdotto cp;

    private Subject sessioneBean;

    private static final String LOGOUT = "Logout";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //chiedo la lista al controller, pattern MVP
        List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciTot();
        inizializzaLista(listaAnnunciBean);
        inizializzaBottoneFiltro();
        inizializzaIconaOrdinamento();

        // se è stato effettuato il login il bottone del login diventa quello del logout
        if(cp.isLogged()){
            loginButton.setText(LOGOUT);
        }

    }

    @FXML
    void openLogin() {
        try {
            if(!cp.isLogged()){
                //se l'utente non è loggato va fatto il login
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("login.fxml")));
                // viene passato il controller applicativo dell'acquisto di un prodotto per settare eventualmente la Sessione con l'utente che si loggerà
                loader.setControllerFactory(aClass -> new LoginController(cp));

                Stage stage = new Stage();
                stage.setTitle("Manga Eater - Login");
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
            Logger logger = Logger.getLogger(DbConnection.class.getName());
            logger.log(Level.WARNING, "Errore durante apertura file FXML");
        }
    }

    public ComprareProdotto getCp() {

        return cp;
    }

    @FXML
    void estraiAnnunciPerTitolo(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio"
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerTitolo(searchBar.getText());
            inizializzaLista(listaAnnunciBean);
        }
    }


    private void inizializzaIconaOrdinamento() {
        // crea menu items
        MenuItem menuItem1 = new MenuItem("Inserimento");
        MenuItem menuItem2 = new MenuItem("Titolo");
        MenuItem menuItem3 = new MenuItem("Costo Crescente");
        MenuItem menuItem4 = new MenuItem("Costo Decrescente");

        menuItem1.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.ID);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem2.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.TITOLO);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem3.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.COSTO_CRESCENTE);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem4.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.cambiaOrdinamento(FiltroAnnunci.OrdineAnnunci.COSTO_DECRESCENTE);
            inizializzaLista(listaAnnunciBean);
        });

        //di seguito vengono aggiunti i menu item appena creati al bottone
        iconaOrdinamento.getItems().add(menuItem1);
        iconaOrdinamento.getItems().add(menuItem2);
        iconaOrdinamento.getItems().add(menuItem3);
        iconaOrdinamento.getItems().add(menuItem4);
    }


    private void inizializzaBottoneFiltro() {
        // crea menu items
        MenuItem menuItem1 = new MenuItem("Shonen                 ");
        MenuItem menuItem2 = new MenuItem("Kodomo");
        MenuItem menuItem3 = new MenuItem("Shoujo");
        MenuItem menuItem4 = new MenuItem("Josei");
        MenuItem menuItem5 = new MenuItem("Seinen");
        MenuItem menuItem6 = new MenuItem("Tutti");

        menuItem1.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHONEN);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem2.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.KODOMO);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem3.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHOUJO);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem4.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.JOSEI);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem5.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SEINEN);
            inizializzaLista(listaAnnunciBean);
        });

        menuItem6.setOnAction(event -> {
            List<AnnuncioBean> listaAnnunciBean = cp.rimuoviFiltri();
            inizializzaLista(listaAnnunciBean);
            //rimuovo qualsiasi cosa nella searchbar per chiearire il fatto che vengono rimossi anche eventuali filtri sul titolo
            searchBar.clear();
        });


        //di seguito vengono aggiunti i menu item appena creati al bottone
        categorieButton.getItems().add(menuItem1);
        categorieButton.getItems().add(menuItem2);
        categorieButton.getItems().add(menuItem3);
        categorieButton.getItems().add(menuItem4);
        categorieButton.getItems().add(menuItem5);
        categorieButton.getItems().add(menuItem6);
    }

    private void inizializzaLista(List<AnnuncioBean> listaAnnunciBean) {

        if(listaAnnunciBean.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("La ricerca non ha avuto risultati\n Immetere un altro filtro");
            alert.setTitle("NESSUN RISULTATO");
            alert.showAndWait();
        } else {
            cardHolder.getChildren().removeAll(list);
            list.clear();
            for(AnnuncioBean annuncioBean : listaAnnunciBean){
                list.add(new Card(annuncioBean.getId(), annuncioBean.getTitolo(), annuncioBean.getDescrizione(), annuncioBean.getCosto(), annuncioBean.getVenditoreEmail(), annuncioBean.getCategoria(), this));
            }

            int count = 0;
            for(Card card: list){
                cardHolder.add(card,count%2, count/2);
                count++;
            }
        }
    }

    @Override
    public void update() {
        // viene cambiata l'etichetta del loginButton
        String textLoginButton = loginButton.getText();
        if(textLoginButton.equals(LOGOUT)){
            loginButton.setText("Accedi | Registrati");
        }
        else{
            loginButton.setText(LOGOUT);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        sessioneBean = sub;
    }


}