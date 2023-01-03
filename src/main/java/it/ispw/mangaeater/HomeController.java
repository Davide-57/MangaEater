package it.ispw.mangaeater;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    ObservableList<Card> list = FXCollections.observableArrayList();

    @FXML
    private GridPane cardHolder;

    @FXML
    private TextField loginButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //INIZIO INIZIALIZZAZIONE LISTA DI ANNUNCI
        list.add(new Card(1, "One-Punch Man Volume 1", "Un bel mangaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!", 500.0));
        list.add(new Card(2, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(3, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(4, "Pawan Ghewande", "96******30", 50000.0));
        list.add(new Card(5, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(6, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(7, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(8, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(9, "Pawan Ghewande", "96******30", 500.0));
        list.add(new Card(10, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(11, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(12, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(13, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(14, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(15, "Pawan Ghewande","96******30", 500.0));
        list.add(new Card(16, "Pawan Ghewande","96******30", 500.0));
        
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                cardHolder.add(list.get(count), j, i);
                count++;
            }
        }
        //FINE INIZIALIZZAZIONE LISTA DI ANNUNCI


    }

    @FXML
    void openLogin(MouseEvent event) {
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