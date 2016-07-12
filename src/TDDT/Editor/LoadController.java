package TDDT.Editor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class LoadController {
    private final IntSenderModel model;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button button;

    public LoadController(IntSenderModel model){
        this.model = model;
    }

    @FXML
    public void sendInt(){
        model.setNumber(0);
    }
}
