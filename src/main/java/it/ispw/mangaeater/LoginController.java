package it.ispw.mangaeater;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController{

    @FXML
    private TextField email;

    @FXML
    private Button loginButton;

    @FXML
    private Label registratiButton;

    @FXML
    private TextField password;

    @FXML
    void onLoginButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dettaglio - Manga Eater");
            stage.setScene(new Scene(fxmlLoader.load(),450,450));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRegistratiButtonClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText("La registrazione non è implementata");
        alert.showAndWait();
    }

}
