package TDDT.Editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        // loader.setControllerFactory(t -> new Controller(new Model(); // Erstmal ersetzt da model jetzt automatisch im Controller Konstruktor konstruiert wird.
        loader.setControllerFactory(t -> new Controller());


        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
