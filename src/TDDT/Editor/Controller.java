package TDDT.Editor;

import TDDT.XML_body.Exersise;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/*
das ist Controller (Duh) für unsere fxml
 */

public class Controller {
    @FXML
    private TextArea areaText;                      //das Linke TextArea

    @FXML
    private TextArea uneditableAreaText;            //das rechte obere TextArea

    @FXML
    private TextArea uneditableAreaTask;            //das rechte untere TextArea

                  //if true -> TextFiles sind an desssen stellen.
    /*                                             //Das ist für die implimentierung von der
    private ArrayList<String> current = new ArrayList();
    private ArrayList<String> task = new ArrayList();
    private ArrayList<String> code = new ArrayList();
    private ArrayList<String> test = new ArrayList();
    private boolean state = true;
                      */

    private TextFile current;
    private TextFile task;
    private TextFile code;
    private TextFile test;
    private boolean state = true;


    private Model model;

    public Controller(){
        model = new Model();
        setAllTextFiles(model.getAllTextFiles(code, test, task));
    }

    @FXML
    private void makeStep(){
        if(state) {
            onSaveTest();
            uneditableAreaText.clear();
            code.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));


            areaText.clear();
            test.getContent().forEach(line -> areaText.appendText(line +"\n"));

        }
        else{
            onSaveTest();
            areaText.clear();
            code.getContent().forEach(line -> areaText.appendText(line +"\n"));

            uneditableAreaText.clear();
            test.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));

        }
        this.state = !this.state;


    }


    @FXML
    private void onLoadTask(){ // die Load methode.(muss möglicherweise an den state angepasst werden)
        areaText.clear();
        uneditableAreaText.clear();
        uneditableAreaTask.clear();
        model = new Model();
        setAllTextFiles(model.getAllTextFiles(code, test, task));

        code.getContent().forEach(line -> areaText.appendText(line +"\n"));
        test.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));
        task.getContent().forEach(line -> uneditableAreaTask.appendText(line +"\n"));

    }
    private void setAllTextFiles(ArrayList<TextFile> t)
    {
        code = t.get(0);
        test = t.get(1);
        task = t.get(2);
    }




    @FXML
    private void onSaveTest(){

        if(state) {
            task = new TextFile(Arrays.asList(uneditableAreaTask.getText().split("\n")));
            code = new TextFile(Arrays.asList(areaText.getText().split("\n")));
            test = new TextFile(Arrays.asList(uneditableAreaText.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.saveExersise(0);
        }
        else{
            task = new TextFile(Arrays.asList(uneditableAreaTask.getText().split("\n")));
            code = new TextFile(Arrays.asList(uneditableAreaText.getText().split("\n")));
            test = new TextFile(Arrays.asList(areaText.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.saveExersise(0);
        }
    }

    @FXML
    private void onClose(){
            System.exit(0);
    }
    @FXML
    private void onAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("This is an about menu");
        alert.show();
    }

}
