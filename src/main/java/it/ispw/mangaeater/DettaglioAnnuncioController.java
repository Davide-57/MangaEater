package it.ispw.mangaeater;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DettaglioAnnuncioController {

    @FXML
    private ImageView image;

    @FXML
    private ImageView imgBackHome;

    @FXML
    private Label title;

    @FXML
    void backHome(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Manga Eater");
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
