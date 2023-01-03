package it.ispw.mangaeater;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("home.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manga Eater");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
