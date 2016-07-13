package TDDT.Erweiterungen;

import javafx.scene.control.Label;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Antonio on 11.07.2016.
 */
public class Babysteps {

    Task task;
    Timer timer;

    public Babysteps(int seconds, Method doAtExpired, Label textField) {
        task = new Task(seconds, doAtExpired, textField);
        timer = new Timer();
    }

    public void Start() {
        timer.schedule(task, 0, 1000);
    }

    public void Stop() {
        timer.cancel();
    }
}

class Task extends TimerTask {
    private final Method doAtExpired;
    private final Label textField;
    private int seconds;

    public Task(int seconds, Method doAtExpired, Label textField) {
        this.doAtExpired = doAtExpired;
        this.seconds = seconds;
        this.textField = textField;
    }

    @Override
    public void run() {

        --seconds;
        textField.setText(String.valueOf(seconds));

        if (seconds == 0) {
            this.cancel();

            try {
                doAtExpired.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
