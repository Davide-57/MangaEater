package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.bean.UtenteBeanFromView;
import it.ispw.mangaeater.controller.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginCliController implements Initializable {

    private final Login login;            // controller applicativo del login

    private String email = null;

    private String psw = null;

    private boolean log = false;

    private boolean reg = false;

    private static final String SEPARATORE = "----------------------------------------------------------------------------\n";

    @FXML
    private TextField inputText;

    @FXML
    private TextArea outputText;

    public LoginCliController(Login controllerLogin) {
        this.login = controllerLogin;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        outputText.setWrapText(true);
        login.estraiUtenti();
        mostraComandi();

    }

    private void mostraComandi() {
        outputText.setText("""
                Per eseguire il login inserisci "log".
                Per registrarti inserisci "reg".
                """ + SEPARATORE);
    }

    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            String input = inputText.getText();
            if(!log && !reg) {
                switch (input) {
                    case "log":
                        log = true;
                        outputText.appendText("Login avviato.\n\nInserisci l'email: ");
                        inputText.clear();
                        break;
                    case "reg":
                        outputText.appendText("Registrazione avviata ma non implementata.\nIl comando verrà ignorato.\n" + SEPARATORE);
                        inputText.clear();
                        break;
                    default:
                        outputText.appendText("Comando non valido.\n");
                        break;
                }
            }
            else if(log){
                if(email == null) {
                    email = input;
                    outputText.appendText(email+ "\nInserisci la password: ");
                    inputText.clear();
                }
                else {
                    psw = input;
                    effettuaLogin();
                }
                inputText.clear();
            }
            else{
                // questo pezzo di codice è utile se la registrazione viene implementata
                outputText.appendText("La registrazione non è stata implementata.\n" + SEPARATORE);
                reg = false;
            }

        }

    }

    private void effettuaLogin() {
        if(!email.isEmpty() && !psw.isEmpty()){
            UtenteBeanFromView bean = new UtenteBeanFromView(email, psw);
            if(bean.controllaValiditaEmail()){
                if(login.autenticaUtente(bean)){
                    // utente autenticato, la schermata di login viene chiusa
                    ((Stage)inputText.getScene().getWindow()).close();
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

}
