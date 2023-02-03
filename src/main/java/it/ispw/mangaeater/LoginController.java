package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.UtenteBeanFromView;
import it.ispw.mangaeater.controller.ComprareProdotto;
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

    Login login;            // controller applicativo del login
    ComprareProdotto cp = null;    // controller applicativo dell'acquisto di un prodotto

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    public LoginController(ComprareProdotto cp) {
        this.cp = cp;
    }

    public LoginController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (cp != null) {
            // è in corso il caso d'uso dell'acquisto di un prodotto
            login = new Login(cp);
        }
        else{
            login = new Login();
        }

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
                    System.out.println("VERIFICATO");
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
