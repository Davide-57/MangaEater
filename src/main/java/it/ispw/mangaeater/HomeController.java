package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private MenuButton categorieButton;

    @FXML
    private TextField searchBar;

    private ComprareProdotto cp = new ComprareProdotto();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //chiedo la lista al controller, pattern MVP
        List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciTot();
        inizializzaLista(listaAnnunciBean);
        inizializzaBottoneFiltro();

    }





    private void inizializzaBottoneFiltro() {
        // crea menuitems
        MenuItem menuItem1 = new MenuItem("Shonen                 ");
        MenuItem menuItem2 = new MenuItem("Kodomo");
        MenuItem menuItem3 = new MenuItem("Shoujo");
        MenuItem menuItem4 = new MenuItem("Josei");
        MenuItem menuItem5 = new MenuItem("Seinen");

        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHONEN);
                inizializzaLista(listaAnnunciBean);
            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.KODOMO);
                inizializzaLista(listaAnnunciBean);
            }
        });

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SHOUJO);
                inizializzaLista(listaAnnunciBean);
            }
        });

        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.JOSEI);
                inizializzaLista(listaAnnunciBean);
            }
        });

        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerCategoria(CategoriaAnnuncio.SEINEN);
                inizializzaLista(listaAnnunciBean);
            }
        });


        categorieButton.getItems().add(menuItem1);
        categorieButton.getItems().add(menuItem2);
        categorieButton.getItems().add(menuItem3);
        categorieButton.getItems().add(menuItem4);
        categorieButton.getItems().add(menuItem5);
    }

    private void inizializzaLista(List<AnnuncioBean> listaAnnunciBean) {

        if(listaAnnunciBean.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("La ricerca non ha avuto risultati\n Immetere un altro filtro");
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

    @FXML
    void estraiAnnunciPerTitolo(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio"
            List<AnnuncioBean> listaAnnunciBean = cp.estraiAnnunciPerTitolo(searchBar.getText());
            inizializzaLista(listaAnnunciBean);
        }
    }


}