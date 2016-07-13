package TDDT.Editor;

import TDDT.Compiler.CompileHelper;
import TDDT.XML_body.Exersise;
import TDDT.XML_body.Exersises;
import TDDT.XML_body.XMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
das ist Controller (Duh) für unsere fxml
 */

public class Controller {
    @FXML
    private TextArea editableArea;

    @FXML
    private Label babystepsLabel;

    @FXML
    private TextArea uneditableRightTopArea;            //das rechte obere TextArea

    @FXML
    private TextArea uneditableRightBottomArea;            //das rechte untere TextArea

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
    int phase;
    int modeState = 0;
    boolean refactorBool;

    private Model model;

    public Controller(){
        secondModel = new IntSenderModel();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task));
        this.compileHelper = new CompileHelper();
    }



    @FXML
    private void makeStep(){

        if(phase%3 == 0) {
            compileHelper.AddSourceClass("Class", uneditableRightTopArea.getText());
            compileHelper.SetTest("TestClass", editableArea.getText());
        }
        else{
            compileHelper.AddSourceClass("Class", editableArea.getText());
            compileHelper.SetTest("TestClass", uneditableRightTopArea.getText());
        }

        compileHelper.CompileAndTest();

        if(phase%3 == 0 && (compileHelper.NumberOfFailedTests() == 0 || compileHelper.NumberOfFailedTests() > 1)){
            editableArea.setStyle("-fx-background-color: red");

            console.clear();

            if(compileHelper.HasCompilerErrors()){
                console.appendText(compileHelper.GetCompilerErros());
            }
            else if(compileHelper.NumberOfFailedTests() > 0){
                console.appendText(compileHelper.GetTestFaillures());
            }
            else {
                if(compileHelper.NumberOfFailedTests() == 0)
                    console.appendText("Mind. ein Test muss fehlschlagen!");
                if(compileHelper.NumberOfFailedTests() > 1)
                    console.appendText("Nur ein Test soll fehlschlagen!");
            }

        }
        if(phase%3 == 0 && compileHelper.NumberOfFailedTests() == 1){
            onSave();

            uneditableRightTopArea.clear();
            test.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));

            editableArea.clear();
            code.getContent().forEach(line -> editableArea.appendText(line + "\n"));
            editableArea.setStyle("-fx-background-color: green");
            phase = 1;

            console.clear();
            if(compileHelper.HasCompilerErrors()){
                console.appendText(compileHelper.GetCompilerErros());
            }
            else if(compileHelper.NumberOfFailedTests() > 0){
                console.appendText(compileHelper.GetTestFaillures());
            }

        }

        if(phase%3 == 1){
            if(compileHelper.NumberOfFailedTests() > 0 || compileHelper.HasCompilerErrors()){
                console.clear();
                if(compileHelper.HasCompilerErrors()){
                    console.appendText(compileHelper.GetCompilerErros());
                }
                else if(compileHelper.NumberOfFailedTests() > 0){
                    console.appendText(compileHelper.GetTestFaillures());
                }
                else {
                    console.appendText("Tests passed.");
                }

                editableArea.setStyle("-fx-background-color: green");
                phase = 1;

            }
            if(compileHelper.NumberOfFailedTests() == 0 && !compileHelper.HasCompilerErrors()){
                console.clear();
                if(compileHelper.HasCompilerErrors()){
                    console.appendText(compileHelper.GetCompilerErros());
                }
                else if(compileHelper.NumberOfFailedTests() > 0){
                    console.appendText(compileHelper.GetTestFaillures());
                }
                else {
                    console.appendText("Tests passed.");
                }
                onSave();

                editableArea.setStyle("-fx-background-color: gray");
                phase = 2;
            }
        }
        if(phase%3 == 2){
            if(compileHelper.NumberOfFailedTests() > 0 || compileHelper.HasCompilerErrors()){
                console.clear();
                if(compileHelper.HasCompilerErrors()){
                    console.appendText(compileHelper.GetCompilerErros());
                }
                else if(compileHelper.NumberOfFailedTests() > 0){
                    console.appendText(compileHelper.GetTestFaillures());
                }
                else {
                    console.appendText("Tests passed.");
                }

                editableArea.setStyle("-fx-background-color: gray");
                phase = 2;

            }
            if(compileHelper.NumberOfFailedTests() == 0 && !compileHelper.HasCompilerErrors()){
                console.clear();
                if(compileHelper.HasCompilerErrors()){
                    console.appendText(compileHelper.GetCompilerErros());
                }
                else if(compileHelper.NumberOfFailedTests() > 0){
                    console.appendText(compileHelper.GetTestFaillures());
                }
                else {
                    console.appendText("Tests passed.");
                }

                editableArea.setStyle("-fx-background-color: red");
                phase = 0;

                onSave();

                editableArea.clear();
                test.getContent().forEach(line -> editableArea.appendText(line + "\n"));

                uneditableRightTopArea.clear();
                code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            }
        }

        /*
        if(!compileHelper.HasCompilerErrors()){
            if (state) {

                onSave();

                uneditableRightTopArea.clear();
                test.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));

                editableArea.clear();
                code.getContent().forEach(line -> editableArea.appendText(line + "\n"));

                this.state = !this.state;

                editableArea.setStyle("-fx-background-color: green");
            }
            else{
                if((    editableArea.getStyle() == "-fx-background-color: green" ||
                        editableArea.getStyle() == "-fx-background-color: gray")
                        && refactorBool){
                    if(compileHelper.NumberOfFailedTests() == 0)
                        editableArea.setStyle("-fx-background-color: gray");
                }
                else {
                    onSave();

                    editableArea.clear();
                    test.getContent().forEach(line -> editableArea.appendText(line + "\n"));

                    uneditableRightTopArea.clear();
                    code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));

                    this.state = !this.state;

                    editableArea.setStyle("-fx-background-color: red");
                    refactorBool = true;
                }
            }

        }

        if(compileHelper.HasCompilerErrors()){
            console.appendText(compileHelper.GetCompilerErros());
        }
        else if(compileHelper.NumberOfFailedTests() > 0){
            console.appendText(compileHelper.GetTestFaillures());
        }
        else {
            console.clear();
            console.appendText("Tests passed.");
        }*/
    }
    @FXML
    public void stepBack()
    {
        if(modeState == 1) {
            editableArea.clear();
            test.getContent().forEach(line -> editableArea.appendText(line + "\n"));

            uneditableRightTopArea.clear();
            code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            modeState--;
            state = !state;
        }

    }
    @FXML
    private void onRefactor(){
        refactorBool = false;
        makeStep();
    }


    @FXML
    private void onLoad(){ // die Load methode.(muss möglicherweise an den state angepasst werden)


        editableArea.clear();
        uneditableRightTopArea.clear();
        uneditableRightBottomArea.clear();
        getExersice();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task));

        test.getContent().forEach(line -> editableArea.appendText(line +"\n"));
        code.getContent().forEach(line -> uneditableRightTopArea.appendText(line +"\n"));
        task.getContent().forEach(line -> uneditableRightBottomArea.appendText(line +"\n"));
        modeState = model.getExersise().getState();
        state = true;
        phase = 0;
        refactorBool = true;
        editableArea.setStyle("-fx-background-color: red");
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
    private void onSave(){

        if(phase%3 == 0) {
            task = new TextFile(Arrays.asList(uneditableRightBottomArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(editableArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(uneditableRightTopArea.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.getExersise().setState(modeState);
            model.saveExersise();
        }
        else{
            task = new TextFile(Arrays.asList(uneditableRightBottomArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(uneditableRightTopArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(editableArea.getText().split("\n")));
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
