package it.ispw.mangaeater;

import it.ispw.mangaeater.controller.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Login login;

    @FXML
    private TextField email;

    @FXML
    private Button loginButton;

    @FXML
    private Label registratiButton;

    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login = new Login();
        login.estraiUtenti();
    }

    @FXML
    void onLoginButtonClick() {
        String email = this.email.getText();
        String psw = this.password.getText();
    }

    @FXML
    void onRegistratiButtonClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText("La registrazione non è implementata");
        alert.showAndWait();
    }
}
