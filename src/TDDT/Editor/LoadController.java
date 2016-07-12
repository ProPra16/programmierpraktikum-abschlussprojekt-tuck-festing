package TDDT.Editor;

import TDDT.XML_body.*;
import TDDT.XML_body.XMLController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class LoadController {
    private final IntSenderModel model;
    private Exersises allExersises;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button button;

    public LoadController(IntSenderModel model){
        listView = new ListView<String>();
        this.model = model;
        allExersises = XMLController.loadAllExercises();
        ArrayList<String>  test = new ArrayList<String>();
        ListProperty<String> listProperty = new SimpleListProperty<>();

        for(Exersise t :  allExersises.getExersises()) {
            test.add(t.getExersiseName());
        }
        ObservableList<String> toSet = FXCollections.observableArrayList(test);

       // listProperty.set(FXCollections.observableArrayList(test));

        listView.setItems(toSet);

    }

    @FXML
    public void sendInt(){
        model.setNumber(0);
    }
}
