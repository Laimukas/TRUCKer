package com.example.trucker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("TRUCKer");
        stage.setScene(scene);
        stage.show();
    }
//    public void changeScene(String fxml,Integer Width,Integer Height,String title) throws IOException{
        public void changeScene(String fxml,Integer Width,Integer Height) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.setWidth(Width);
        stg.setHeight(Height);
        stg.centerOnScreen();
//        stg.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }

}