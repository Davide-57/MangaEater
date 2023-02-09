package it.ispw.mangaeater.controller_grafici;

import it.ispw.mangaeater.bean.UtenteBeanFromView;
import it.ispw.mangaeater.controller.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final Login login;            // controller applicativo del login

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    public LoginController(Login controllerLogin) {
        this.login = controllerLogin;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login.estraiUtenti();

    }

    @FXML
    void onLoginButtonClick() {
        String emailText = this.email.getText();
        String psw = this.password.getText();
        if(!emailText.isEmpty() && !psw.isEmpty()){
            UtenteBeanFromView bean = new UtenteBeanFromView(emailText, psw);
            if(bean.controllaValiditaEmail()){
                if(login.autenticaUtente(bean)){
                    // utente autenticato, la schermata di login viene chiusa
                    ((Stage)email.getScene().getWindow()).close();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("EMAIL O PASSWORD ERRATE");
                    alert.setContentText("Inserire correttamente le credenziali");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("EMAIL NON VALIDA");
                alert.setContentText("Inserire una email valida");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("MANCANZA DI INFORMAZIONI");
            alert.setContentText("Inserire sia email che password");
            alert.showAndWait();
        }
    }

    @FXML
    void onRegistratiButtonClick() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText("La registrazione non è implementata");
        alert.showAndWait();
    }
}
