package com.blackjackgui.blackjackgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/blackjackgui/blackjackgui/hello-view.fxml"));
        VBox root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Blackjack Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
