package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public HomeController() {
    }

    public HomeController(ComprareProdotto cp) {
        this.cp = cp;
    }

    private final ObservableList<Card> list = FXCollections.observableArrayList();

    @FXML
    private GridPane cardHolder;

    @FXML
    private MenuButton filtroButton;

    @FXML
    private TextField searchBar;

    private ComprareProdotto cp = new ComprareProdotto();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inizializzaLista();
        inizializzaBottoneFiltro();

    }

    private void inizializzaBottoneFiltro() {
        // crea menuitems
        MenuItem menuItem1 = new MenuItem("Ordina per inserimento");
        MenuItem menuItem2 = new MenuItem("Ordina per titolo");
        MenuItem menuItem3 = new MenuItem("Ordina per costo");

        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opzione 1 selezionata");
            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opzione 2 selezionata");
            }
        });

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opzione 2 selezionata");
            }
        });


        filtroButton.getItems().addAll(menuItem1, menuItem2, menuItem3);
    }

    private void inizializzaLista() {
        //chiedo la lista al controller, pattern MVP
        List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciTot();
        
        for(AnnuncioBean annuncioBean : listaAnnunciBean){
            list.add(new Card(annuncioBean.getId(), annuncioBean.getTitolo(), annuncioBean.getDescrizione(), annuncioBean.getCosto(), annuncioBean.getVenditoreEmail(), this));
        }

        /*
        QUESTO PEZZO COMMENTATO LO LEVERò IN QUANTO ASSUMO CHE QUANDO L'APPLICAZIONE VIENE AVVIATA CI SIANO ALMENO 2 ANNUNCI DA MOSTRARE
        MI SARà UTILE QUANDO INSERISCO IL MECCANISMO DI FILTRAGGIO IN QUANTO DA Lì POSSONO USCIRE ANCHE 0 O 1 ANNUNCI

        if(list.size()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("La ricerca non ha avuto risultati\n Immetere un altro filtro");
            alert.showAndWait();
        } else {
            count = 0;
            for(Card card: list){
                cardHolder.add(card,count%2, count/2);
                count++;
            }
        }

        */


        int count = 0;
        for(Card card: list){
            cardHolder.add(card,count%2, count/2);
            count++;
        }
    }

    @FXML
    void openLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manga Eater - Login");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ComprareProdotto getCp() {

        return cp;
    }


}