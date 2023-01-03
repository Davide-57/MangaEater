package it.ispw.mangaeater;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Card extends Pane {

    protected final ImageView photo;
    protected final Label name;
    protected final Text description;
    protected final Label labelCost;
    protected final Label cost;

    private int ID;

    public Card(int Id, String Name, String Description, Double Cost) {

        photo = new ImageView();
        name = new Label();
        labelCost = new Label();
        description = new Text();
        cost = new Label();

        setId(Id + "");
        setPrefHeight(200.0);
        setPrefWidth(385.0);

        photo.setImage(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/One-Punch_Man.jpg"))));
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
        name.setText(Name);
        name.setStyle("-fx-font-weight: bold; -fx-font-size: 1.9em;");

        labelCost.setAlignment(Pos.TOP_LEFT);
        labelCost.setLayoutX(260);
        labelCost.setLayoutY(175);
        labelCost.setPrefHeight(20);
        labelCost.setPrefWidth(110);
        labelCost.setText("Cost:");
        labelCost.setFont(new Font(13.0));

        description.setLayoutX(110);
        description.setLayoutY(45);
        description.setWrappingWidth(265);
        description.setText(Description);
        description.setFont(new Font(14));

        cost.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        cost.setLayoutX(300);
        cost.setLayoutY(170);
        cost.setPrefHeight(19.0);
        cost.setPrefWidth(115.0);
        cost.setText(Cost.toString() + "€");
        cost.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;");

        setOnMouseClicked(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dettaglio-annuncio.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Dettaglio annuncio - Manga Eater");
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setResizable(false);
                stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(e.getSource())).getScene().getWindow().hide();
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

        //mostra la card creata a schermo
        getChildren().addAll(photo,name, description, labelCost, cost);



    }
}