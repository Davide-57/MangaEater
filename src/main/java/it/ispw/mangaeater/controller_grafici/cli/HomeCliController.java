package it.ispw.mangaeater.controller_grafici.cli;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeCliController implements Initializable {

    @FXML
    private TextField inputText;

    @FXML
    private TextArea outputText;

    private ComprareProdotto cp;

    private List<AnnuncioBean> listaAnnunciBean;

    private final static String SEPARATORE = "-------------------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------------------------";


    public HomeCliController() {
        this.cp = new ComprareProdotto();
    }

    @FXML
    void ottieniInput(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            // codice da eseguire quando viene premuto il tasto "invio" mentre la inputText è selezionata

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.setWrapText(true);
        mostraComandi();

        inizializzaLista();

    }

    private void mostraComandi() {

        outputText.appendText("""
                Benvenuto nell'app MangaEater.
                Di seguito verranno delle indicazioni ed i comandi disponibili per interagire con il programma:
                1) Ogni tipo di input va inserito nella barra in basso
                2) Per entrare nel dettaglio di un annuncio inserire il suo ID
                3) Per effettuare il login, inserire il comando "login"
                4) Per accedere alla modalità filtro, inserire il comando "fil"
                5) Dopo essere entrati nella modalità filtro, inserire il comando "tit" per filtrare in base al titolo e "cat" per filtrare in base alla categoria
                6) Per accedere alla modalità ordinamento, inserire il comando "ord"
                7) Per rimuovere tutti i filtri e ordinamenti, inserire il comando "reset"
                8) Per  visualizzare di nuovo i comandi disponibili inserisci "cmd"
                
                I comandi sono terminati, di seguito verranno visualizzati gli annunci disponibili.
                
                """ + SEPARATORE);
    }

    private void inizializzaLista() {

        listaAnnunciBean = cp.estraiAnnunciTot();
        if(listaAnnunciBean.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("La ricerca non ha avuto risultati\nImmetere un altro filtro");
            alert.setTitle("NESSUN RISULTATO");
            alert.showAndWait();
        }
        else{
            for(AnnuncioBean annuncioBean: listaAnnunciBean){
                outputText.appendText("\n\n" +
                        "\nID annuncio: " + annuncioBean.getId() +
                        "\nTitolo: " + annuncioBean.getTitolo() +
                        "\nDescrizione: " + annuncioBean.getDescrizione() +
                        "\nCosto: " + annuncioBean.getCosto());
                outputText.appendText("\n\n" + SEPARATORE);
            }
        }
    }
}
