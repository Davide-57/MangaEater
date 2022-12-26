package it.ispw.mangaeater;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    ObservableList<Card> list = FXCollections.observableArrayList();

    @FXML
    private GridPane cardHolder;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.add(new Card(1, "Pawan Ghewande Pawan Ghewande Pawan Ghewande Pawan Ghewande", "96******30", "10/02/2019", "3"));
        list.add(new Card(2, "Pawan Ghewande", "96******30", "10/02/2019", "4"));
        list.add(new Card(3, "Pawan Ghewande", "96******30", "10/02/2019", "5"));
        list.add(new Card(4, "Pawan Ghewande", "96******30", "10/02/2019", "6"));
        list.add(new Card(5, "Pawan Ghewande", "96******30", "10/02/2019", "7"));
        list.add(new Card(6, "Pawan Ghewande", "96******30", "10/02/2019", "8"));
        list.add(new Card(7, "Pawan Ghewande", "96******30", "10/02/2019", "9"));
        list.add(new Card(8, "Pawan Ghewande", "96******30", "10/02/2019", "10"));
        list.add(new Card(9, "Pawan Ghewande", "96******30", "10/02/2019", "11"));
        list.add(new Card(10, "Pawan Ghewande","96******30", "10/02/2019", "12"));
        list.add(new Card(11, "Pawan Ghewande","96******30", "10/02/2019", "13"));
        list.add(new Card(12, "Pawan Ghewande","96******30", "10/02/2019", "14"));
        list.add(new Card(13, "Pawan Ghewande","96******30", "10/02/2019", "15"));
        list.add(new Card(14, "Pawan Ghewande","96******30", "10/02/2019", "16"));
        list.add(new Card(15, "Pawan Ghewande","96******30", "10/02/2019", "17"));
        list.add(new Card(16, "Pawan Ghewande","96******30", "10/02/2019", "17"));
        /*cardHolder.setAlignment(Pos.CENTER);
        cardHolder.setVgap(20.00);
        cardHolder.setHgap(20.00);
        cardHolder.setStyle("-fx-padding:10px;-fx-border-color:transparent");*/
        
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                cardHolder.add(list.get(count), j, i);
                count++;
                //System.out.println(i + " " + j);
            }
        }
    }
}