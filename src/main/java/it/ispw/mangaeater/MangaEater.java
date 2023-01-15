package it.ispw.mangaeater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class MangaEater extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MangaEater.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Manga Eater");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(MangaEater.class.getResourceAsStream("/images/Logo_MangaEater.png"))));

        stage.show();

        String url = "jdbc:mysql://localhost:3306/mangaeater";
        String username = "user";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, "user")) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }


    public static void main(String[] args) {
        launch();
    }

}