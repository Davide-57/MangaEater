package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    private final ObservableList<Card> list = FXCollections.observableArrayList();

    @FXML
    private GridPane cardHolder;

    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ComprareProdotto cp = new ComprareProdotto();

        //chiedo la lista al controller, pattern MVP
        List<AnnuncioBean> listaAnnunciBean = cp.visualizzaAnnunci();

        int count = 0;

        //INIZIO INIZIALIZZAZIONE LISTA DI ANNUNCI
        for(AnnuncioBean annuncioBean : listaAnnunciBean){
            list.add(new Card(count, annuncioBean.getTitolo(), annuncioBean.getDescrizione(), annuncioBean.getCosto()));
            count+=1;
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


        count = 0;
        for(Card card: list){
            cardHolder.add(card,count%2, count/2);
            count++;
        }
        //FINE INIZIALIZZAZIONE LISTA DI ANNUNCI


    }

    @FXML
    void openLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login - Manga Eater");
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


}