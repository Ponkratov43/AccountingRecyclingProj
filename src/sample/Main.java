package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/MainFXML.fxml"));
        primaryStage.setTitle("Accounting Recycle");
        primaryStage.getIcons().add(new Image("sample/view/resources/main.png"));
        primaryStage.setScene(new Scene(root, 322, 524));
        primaryStage.show();
        primaryStage.setResizable(false);
    }



    public static void main(String[] args) {
        launch(args);
    }
}

