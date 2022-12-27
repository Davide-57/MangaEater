package it.ispw.mangaeater;

import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Card extends Pane {

    protected final ImageView photo;
    protected final Label name;
    protected final Text description;
    protected final Label labelCost;
    protected final Label cost;
    protected final Label visites;
    protected final Label label0;
    private int ID;

    public Card(int Id, String Name, String Description, Double Cost, String Vistes) {

        photo = new ImageView();
        name = new Label();
        labelCost = new Label();
        description = new Text();
        cost = new Label();
        visites = new Label();
        label0 = new Label();

        setId(Id + "");
        setPrefHeight(200.0);
        setPrefWidth(385.0);
        //setStyle("-fx-background-color:#FFF; -fx-border-radius: 10px; -fx-background-radius: 10px;");

        /*DropShadow dropShadow = new DropShadow();
        dropShadow.setHeight(3);
        dropShadow.setWidth(3);
        dropShadow.setBlurType(BlurType.TWO_PASS_BOX);
        setEffect(dropShadow);*/

        photo.setImage(new Image("C:\\Users\\Davide\\Desktop\\Università\\ISPW\\Progetto\\trunk\\MangaEater\\src\\main\\resources\\images\\One-Punch_Man.jpg"));
        photo.setLayoutX(10);
        photo.setLayoutY(30);
        photo.setFitHeight(140);
        photo.setFitWidth(90);
//        photo.setRadius(45.0);
//        photo.setStroke(javafx.scene.paint.Color.valueOf("#d7d7d7"));
//        photo.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);

        name.setAlignment(javafx.geometry.Pos.CENTER);
        name.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        name.setLayoutX(0);
        name.setLayoutY(0);
        name.setPrefHeight(26.0);
        name.setPrefWidth(385.0);
        name.setText(Name);
        name.setFont(new Font(20));
        name.setStyle("-fx-font-weight: bold;");

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
        cost.setFont(new Font(18.0));
        cost.setStyle("-fx-font-weight: bold;");

        visites.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        visites.setLayoutX(107.0);
        visites.setLayoutY(160.0);
        visites.setPrefHeight(19.0);
        visites.setPrefWidth(115.0);
        visites.setText(Vistes);
        visites.setFont(new Font(13.0));

        label0.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        label0.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label0.setLayoutX(39.0);
        label0.setLayoutY(160.0);
        label0.setPrefHeight(19.0);
        label0.setPrefWidth(62.0);
        label0.setText("Descrizione da Wikipedia:");
        label0.setFont(new Font(13.0));

        /*setOnMouseClicked(e -> {
            // Action you want to do
            Alert alert = new Alert(AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText("Sample Alert");
            alert.showAndWait();
        });*/

        //setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //getStylesheets().add(getClass().getResource("CardDesign.css").toExternalForm());

        //#fff7e6 più chiaro
        setStyle("""
                -fx-background-color:#FFD699;-fx-border-size:0.5;
                -fx-border-color:  #ffb84d;
                -fx-border-radius: 20;-fx-background-radius: 20;""");
        getChildren().addAll(photo,name, description, labelCost, cost,visites,label0);



    }
}