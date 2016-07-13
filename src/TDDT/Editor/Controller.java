package TDDT.Editor;

import TDDT.Compiler.CompileHelper;
import TDDT.XML_body.Exersise;
import TDDT.XML_body.Exersises;
import TDDT.XML_body.XMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
/*
das ist Controller (Duh) für unsere fxml
 */

public class Controller {
    @FXML
    private TextArea codeArea;                      //das Linke TextArea

    @FXML
    private TextArea testArea;            //das rechte obere TextArea

    @FXML
    private TextArea taskArea;            //das rechte untere TextArea

    @FXML
    private TextArea console;
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
    private CompileHelper compileHelper;
    private IntSenderModel secondModel;
    private Path path;


    private Model model;

    public Controller(){
        secondModel = new IntSenderModel();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task));
        this.compileHelper = new CompileHelper();
    }



    @FXML
    private void makeStep(){
        if(state) {
            onSaveTest();

            codeArea.clear();
            test.getContent().forEach(line -> codeArea.appendText(line +"\n"));

            testArea.clear();
            code.getContent().forEach(line -> testArea.appendText(line +"\n"));

            compileHelper.AddSourceClass("Class", testArea.getText());
            compileHelper.SetTest("TestClass", codeArea.getText());
            this.state = !this.state;
        }
        else{
            onSaveTest();

            testArea.clear();
            test.getContent().forEach(line -> testArea.appendText(line +"\n"));

            codeArea.clear();
            code.getContent().forEach(line -> codeArea.appendText(line +"\n"));

            compileHelper.AddSourceClass("Class", codeArea.getText());
            compileHelper.SetTest("TestClass", testArea.getText());
            this.state = !this.state;
        }

        compileHelper.CompileAndTest();

        //finish this

        if(compileHelper.HasCompilerErrors()){
            console.appendText(compileHelper.GetCompilerErros());
        }
        else if(compileHelper.NumberOfFailedTests() > 0){
            console.appendText(compileHelper.GetTestFaillures());
        }
        else {
            console.appendText("Tests passed.");
        }




    }


    @FXML
    private void onLoadTask(){ // die Load methode.(muss möglicherweise an den state angepasst werden)


        codeArea.clear();
        testArea.clear();
        taskArea.clear();
        getExersice();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task));

        test.getContent().forEach(line -> codeArea.appendText(line +"\n"));
        code.getContent().forEach(line -> testArea.appendText(line +"\n"));
        task.getContent().forEach(line -> taskArea.appendText(line +"\n"));
        state = true;

    }

    private void getExersice(){
        Exersises allExersises = XMLController.loadAllExercises();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Exersise e: allExersises.getExersises()){
            if(e.getExersiseName() != null)
                list.add(e.getExersiseName());
        }
        ListView<String> listView = new ListView<>(list);


        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!listView.getSelectionModel().isEmpty()) {
                    secondModel.setNumber(listView.getSelectionModel().getSelectedIndex());
                    Node source = (Node) event.getSource();
                    Stage locStage = (Stage) source.getScene().getWindow();
                    locStage.close();

                }
            }
        });
        Button pathButton = new Button("Select new XML");
        pathButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser chooser = new FileChooser();
                chooser.setInitialDirectory(new File("./"));
                File file = chooser.showOpenDialog(null);
                if(file != null)
                    path = file.toPath();
            }
        });

        button.setMinWidth(100);
        button.setPrefWidth(100);
        button.setMaxWidth(100);

        BorderPane pane = new BorderPane();
        pane.setCenter(button);
        pane.setBottom(pathButton);
        BorderPane bp = new BorderPane();
        bp.setLeft(listView);
        bp.setRight(pane);

        Stage stage = new Stage();
        stage.setScene(new Scene(bp));
        stage.showAndWait();


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
            task = new TextFile(Arrays.asList(taskArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(codeArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(testArea.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.saveExersise();
        }
        else{
            task = new TextFile(Arrays.asList(taskArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(testArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(codeArea.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.saveExersise();
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
