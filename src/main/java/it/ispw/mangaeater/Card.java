package it.ispw.mangaeater;

import it.ispw.mangaeater.bean.AnnuncioBean;
import it.ispw.mangaeater.controller.ComprareProdotto;
import it.ispw.mangaeater.myenum.CategoriaAnnuncio;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Card extends Pane {

    protected final ImageView photo;
    protected final Label name;
    protected final Text description;
    protected final Label labelCost;
    protected final Label cost;

    public Card(int idIn, String nameIn, String descriptionIn, Double costIn, String emailVendIn, CategoriaAnnuncio categoriaIn, HomeController homeController) {

        photo = new ImageView();
        name = new Label();
        labelCost = new Label();
        description = new Text();
        cost = new Label();

        setId(idIn + "");
        setPrefHeight(200.0);
        setPrefWidth(385.0);

        photo.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/"+idIn+".jpg"))));
        photo.setLayoutX(10);
        photo.setLayoutY(40);
        photo.setFitHeight(140);
        photo.setFitWidth(90);

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        name.setLayoutX(0);
        name.setLayoutY(0);
        name.setPrefHeight(26.0);
        name.setPrefWidth(385.0);
        name.setText(nameIn);
        name.setStyle("-fx-font-weight: bold; -fx-font-size: 1.9em;");

        labelCost.setAlignment(Pos.TOP_LEFT);
        labelCost.setLayoutX(260);
        labelCost.setLayoutY(175);
        labelCost.setPrefHeight(20);
        labelCost.setPrefWidth(110);
        labelCost.setText("Costo:");
        labelCost.setFont(new Font(13.0));

        description.setLayoutX(110);
        description.setLayoutY(45);
        description.setWrappingWidth(265);
        description.setText(descriptionIn);
        description.setFont(new Font(14));

        cost.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        cost.setLayoutX(300);
        cost.setLayoutY(170);
        cost.setPrefHeight(19.0);
        cost.setPrefWidth(115.0);
        cost.setText(costIn.toString() + "€");
        cost.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");

        setOnMouseClicked(e -> {
            try {
                ComprareProdotto cp = homeController.getCp();
                AnnuncioBean bean = new AnnuncioBean(idIn, nameIn, descriptionIn, costIn, emailVendIn, categoriaIn);
                // con la seguente operazione viene richiesto al controller applicativo di memorizzare le informazioni dell'annuncio di cui si vuole il dettaglio
                cp.mostraDettaglioAnnuncio(bean);
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("dettaglio-annuncio.fxml")));

                loader.setControllerFactory(aClass -> new DettaglioAnnuncioController (cp));

                Parent root = loader.load();
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Manga Eater - Dettaglio annuncio");

                stage.show();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //#fff7e6 più chiaro
        setStyle("""
                -fx-background-color:#FFD699;-fx-border-size:0.5;
                -fx-border-color:  #ffb84d;
                -fx-border-radius: 20;-fx-background-radius: 20;""");

        setCursor(Cursor.HAND);

        //mostra la card creata a schermo
        getChildren().addAll(photo,name, description, labelCost, cost);



    }
}