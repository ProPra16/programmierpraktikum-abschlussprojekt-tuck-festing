package TDDT.Editor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        IntSenderModel model = new IntSenderModel();

        FXMLLoader loadScreen = new FXMLLoader(getClass().getResource("load.fxml"));
        loadScreen.setController(new LoadController(model));
        Parent first = loadScreen.load();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(new Controller(model));
        Parent second = loader.load();

        Stage stage = new Stage();
        stage.setScene((new Scene(first)));
        stage.show();

        primaryStage.setScene(new Scene(second));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
