package Erweiterungen;

import Editor.Controller;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Antonio on 11.07.2016.
 */
public class Babysteps {

    Task task;
    Timer timer;
    boolean isRunning;

    public Babysteps(int seconds, Controller windowControler, Label textField) {
        task = new Task(seconds, windowControler, textField);
        timer = new Timer();
        isRunning = false;
    }

    public void Start() {
        if (!isRunning) {
            timer.schedule(task, 0, 1000);

            isRunning = true;
        }
    }

    public void Stop() {
        timer.cancel();
        isRunning = false;
    }

    public boolean Running() {
        return isRunning;
    }

}

class Task extends TimerTask {
    private final Controller windowControler;
    private final Label textField;
    private int seconds;

    public Task(int seconds, Controller windowControler, Label textField) {
        this.windowControler = windowControler;
        this.seconds = seconds;
        this.textField = textField;
    }

    @Override
    public void run() {
        --seconds;

        Platform.runLater(() -> textField.setText("Time left: " + String.valueOf(seconds)));

        if (seconds == 0) {
            this.cancel();
            Platform.runLater(() -> windowControler.stepBack());
        }
    }
}
