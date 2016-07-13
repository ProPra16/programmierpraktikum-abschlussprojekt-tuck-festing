package TDDT.Erweiterungen;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Antonio on 11.07.2016.
 */
public class babysteps extends Application {

    TextField tfTimer = new TextField();
    private Timeline timeline;
    public static void main(String[] args){
        launch(args);
    }



    @Override // override the start method in the application class
    public void start(Stage primaryStage) {
        // Create UI
        // tfTimer.setOnAction(e -> timer());


        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        gridPane.add(new Label("Gebe hier die Zeit in Sekunden an: "), 0, 0);
        gridPane.add(tfTimer, 0, 1);




        // Create a scene and place it in the stage


        Scene scene=new Scene(gridPane,300,200);
        primaryStage.setTitle("Babysteps"); // set title
        primaryStage.setScene(scene); // palce the scene in the stage
        primaryStage.show(); // display the stage
    }

    public void timer(Label now, int zeit  ){
        Integer start = zeit;
        Label timerLabel = now;
        IntegerProperty timeSeconds = new SimpleIntegerProperty(start);


        Stage a=new Stage();
        a.setTitle("Countdown");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 250);

        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        Button button = new Button();
        button.setText("Start");

        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                timeSeconds.set(start);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(start + 1),
                                new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();

            }
        });

        VBox vb = new VBox(20);             // gap between components is 20
        vb.setAlignment(Pos.CENTER);        // center the components within VBox

        vb.setPrefWidth(scene.getWidth());
        vb.getChildren().addAll(button, timerLabel);
        vb.setLayoutY(30);

        root.getChildren().add(vb);

        a.setScene(scene);
        a.show();



    }

}

