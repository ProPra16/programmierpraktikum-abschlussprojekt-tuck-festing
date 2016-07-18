package Editor;

import Compiler.CompileHelper;
import Erweiterungen.Babysteps;
import XML_body.Exersise;
import XML_body.Exersises;
import XML_body.XMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import static javafx.scene.paint.Color.RED;
/*
das ist Controller (Duh) für unsere fxml
 */

public class Controller {
    @FXML
    private TextArea editableArea;

    @FXML
    private Label aTDDLabel;

    @FXML
    private TitledPane consoleTitle;

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
    private TextFile atddt;
    private boolean state = true;
    private CompileHelper compileHelper;
    private IntSenderModel secondModel;
    private Path path;
    private Babysteps babysteps;

    int phase = 0;
    boolean refactorBool;

    private Model model;

    public Controller(){
        secondModel = new IntSenderModel();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task, atddt));
        this.compileHelper = new CompileHelper();
        atddt = new TextFile(Arrays.asList(" "));

    }




    @FXML
    private void makeStep() {
       // if (compileHelper.NumberOfFailedFeatureTest()==0&& phase==2)
       //     onATDDT();
        consoleTitle.setText("");
        if(compileHelper.NumberOfFailedFeatureTest()==0)   // prüft den Akzeptanztest
        aTDDLabel.setStyle("-fx-background-color: green;");
        else if(compileHelper.NumberOfFailedFeatureTest()!=0)
            aTDDLabel.setStyle("-fx-background-color: red;");

        int phaseSetter = 0; // Dieser phaseSetter wird benutzt um die Phasen zu setzen da wenn man diese erhöht das nächsthöhere if-Event sonst getriggered wird.
        if(phase%3 == 0) {
            compileHelper.AddSourceClass("Class", uneditableRightTopArea.getText());
            compileHelper.SetTest("TestClass", editableArea.getText());
        }
        else{
            compileHelper.AddSourceClass("Class", editableArea.getText());
            compileHelper.SetTest("TestClass", uneditableRightTopArea.getText());
        }

        compileHelper.CompileAndTest();

         /*   if (compileHelper.GetTestClassCompilerError() == null || phase % 3 == 0 && compileHelper.NumberOfFailedTests() != 1 ) {
                editableArea.setStyle("-fx-background-color: red");

                console.clear();

                 if (compileHelper.NumberOfFailedTests() > 0) {

                    console.appendText(compileHelper.GetTestFaillures());
                } else {
                    if (compileHelper.NumberOfFailedTests() == 0)
                        console.appendText("Mind. ein Test muss fehlschlagen!");
                    if (compileHelper.NumberOfFailedTests() > 1)
                        console.appendText("Nur ein Test soll fehlschlagen!");
                }

            }*/

        if (phase % 3 == 0 && compileHelper.GetTestClassCompilerError() != null) {

            if (compileHelper.HasCompilerErrors()) {
                onSave();
                console.clear();
                console.appendText(compileHelper.GetCompilerErros());
                console.positionCaret(1);
                consoleTitle.setText("Compiler");

                uneditableRightTopArea.clear();
                test.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));

                editableArea.clear();
                code.getContent().forEach(line -> editableArea.appendText(line + "\n"));
                editableArea.setStyle("-fx-background-color: green");
                phaseSetter = 1;
                onSave();
                StartBabysteps();
            }
            else if(compileHelper.NumberOfFailedTests() == 1)
            {
                onSave();
                console.clear();
                console.appendText(compileHelper.GetTestFaillures());
                console.positionCaret(1);
                consoleTitle.setText("Tests");

                uneditableRightTopArea.clear();
                test.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));

                editableArea.clear();
                code.getContent().forEach(line -> editableArea.appendText(line + "\n"));
                editableArea.setStyle("-fx-background-color: green");
                phaseSetter = 1;
                onSave();
                StartBabysteps();
            }
            else {
                if (compileHelper.NumberOfFailedTests() == 0)
                    console.appendText("Mind. ein Test muss fehlschlagen!");
                    console.positionCaret(1);
                    consoleTitle.setText("Tests");
                if (compileHelper.NumberOfFailedTests() > 1)
                    console.appendText("Nur ein Test soll fehlschlagen!");
                    console.positionCaret(1);
                    consoleTitle.setText("Tests");
            }


        }

        if (phase % 3 == 1) {
            if (compileHelper.HasCompilerErrors()) {
                editableArea.setStyle("-fx-background-color: green");
                onSave();
                console.clear();
                console.appendText(compileHelper.GetCompilerErros());
                console.positionCaret(1);
                consoleTitle.setText("Compiler");
                phaseSetter = 1;
            }
            else if (compileHelper.NumberOfFailedTests() > 0 ) {
                editableArea.setStyle("-fx-background-color: green");
                onSave();
                console.clear();
                console.appendText(compileHelper.GetTestFaillures());
                console.positionCaret(1);
                consoleTitle.setText("Tests");
                phaseSetter = 1;
            }
            else {
                console.clear();
                editableArea.setStyle("-fx-background-color: gray");
                phaseSetter = 2;
                consoleTitle.setText("Tests");
                console.appendText("Tests passed. You are now in refactoring mode.\n Press 'Refactor' if you want to Test if your code works. \n Press 'Compile' if your code works and you want to continue to the Test-Phase. ");
                console.positionCaret(1);
                onSave();
                babysteps.Stop();
            }
        }



        if (phase % 3 == 2) {

            if (compileHelper.HasCompilerErrors()) {
                console.appendText(compileHelper.GetCompilerErros());
                console.positionCaret(1);
                consoleTitle.setText("Compiler");
                editableArea.setStyle("-fx-background-color: gray");
                phaseSetter = 2;
            } else if (compileHelper.NumberOfFailedTests() > 0) {
                consoleTitle.setText("Tests");
                console.appendText(compileHelper.GetTestFaillures());
                console.positionCaret(1);
                editableArea.setStyle("-fx-background-color: gray");
                phaseSetter = 2;
            }
            else if (compileHelper.NumberOfFailedTests() == 0 ) {
                consoleTitle.setText("Tests");
                console.clear();
                console.appendText("Tests passed. You are now back in Test-Mode");
                console.positionCaret(1);
                editableArea.setStyle("-fx-background-color: red");
                phaseSetter = 0;

                onSave();

                editableArea.clear();
                test.getContent().forEach(line -> editableArea.appendText(line + "\n"));

                uneditableRightTopArea.clear();
                code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            }
            else if(compileHelper.NumberOfFailedFeatureTest()==0&& compileHelper.NumberOfFailedTests()==0){
                phaseSetter=3;
                onATDDT();
            }
        }
        phase = phaseSetter;
        System.out.print("");

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
    public void onATDDT() {
        if (model.isATTD()) {
            Stage stage = new Stage();
            stage.setTitle("ATDDT");
            TextArea textArea = new TextArea();
            GridPane pane = new GridPane();
            Button button = new Button("Done");
            textArea.clear();
            atddt.getContent().forEach(line -> textArea.appendText(line + "\n"));

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    atddt = new TextFile(Arrays.asList(textArea.getText().split("\n")));
                    stage.close();
                }
            });
            pane.add(textArea, 0, 0);
            pane.add(button, 1, 1);
            stage.setScene(new Scene(pane));
            stage.show();

        }
    }
    @FXML
    public void stepBack()
    {
        if(phase == 1) {
            editableArea.clear();
            test.getContent().forEach(line -> editableArea.appendText(line + "\n"));

            uneditableRightTopArea.clear();
            code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            editableArea.setStyle("-fx-background-color: red");

            console.clear();
            phase = 0;

        }

    }
    @FXML
    private void onRefactor(){
        if(phase == 2) {

            compileHelper.AddSourceClass("Class", editableArea.getText());
            compileHelper.SetTest("TestClass", uneditableRightTopArea.getText());
            compileHelper.CompileAndTest();
            if (compileHelper.HasCompilerErrors()) {
                console.clear();
                console.appendText(compileHelper.GetCompilerErros());
                console.positionCaret(1);
                consoleTitle.setText("Compiler");
                editableArea.setStyle("-fx-background-color: gray");
            } else if (compileHelper.NumberOfFailedTests() == 0) {
                consoleTitle.setText("Tests");
                console.clear();
                console.appendText("Tests passed. You can now Continue with 'Compile' or Refactor some more.");
                console.positionCaret(1);
                editableArea.setStyle("-fx-background-color: gray");

            }
        }
        else{
            console.clear();
            console.appendText("You can only use this Button In refactoring mode, (indicated by a grey Border around the Editor Field.");
        }
    }


    @FXML
    private void onLoad() { // die Load methode.(muss möglicherweise an den state angepasst werden)

        editableArea.clear();
        uneditableRightTopArea.clear();
        uneditableRightBottomArea.clear();
        getExersice();
        model = new Model(secondModel.getNumber());
        setAllTextFiles(model.getAllTextFiles(code, test, task, atddt));
        phase = model.getExersise().getState();
        state = true;
        setColorAccordingToPhase();
        refactorBool = true;

        if(phase == 1){
            StartBabysteps();
        }
        if (phase == 0)
        {
            test.getContent().forEach(line -> editableArea.appendText(line + "\n"));
            code.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            task.getContent().forEach(line -> uneditableRightBottomArea.appendText(line + "\n"));
        }
        else{
            code.getContent().forEach(line -> editableArea.appendText(line + "\n"));
            test.getContent().forEach(line -> uneditableRightTopArea.appendText(line + "\n"));
            task.getContent().forEach(line -> uneditableRightBottomArea.appendText(line + "\n"));
        }


    }
    private void setColorAccordingToPhase()
    {
        if(phase == 0) editableArea.setStyle("-fx-background-color: red");
        else if(phase == 1) editableArea.setStyle("-fx-background-color: green");
        else  editableArea.setStyle("-fx-background-color: gray");
    }

    private void getExersice(){
        Exersises allExersises = XMLController.loadAllExercises();

        Stage stage = new Stage();
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
        pathButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        pathButton.setStyle("-fx-background-color: red");
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Text("This feature is not implemented"));
                        Scene dialogScene = new Scene(dialogVbox, 300, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
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

        stage.setScene(new Scene(bp));
        stage.showAndWait();


    }

    private void setAllTextFiles(ArrayList<TextFile> t)
    {
        code = t.get(0);
        test = t.get(1);
        task = t.get(2);
        atddt =t.get(3);
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
            model.getExersise().setATTD(atddt.getAsArrayList());
            model.getExersise().setState(phase);
            model.saveExersise();
        }
        else{
            task = new TextFile(Arrays.asList(uneditableRightBottomArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(uneditableRightTopArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(editableArea.getText().split("\n")));
            atddt = new TextFile(Arrays.asList(editableArea.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setExersiseText(atddt.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.getExersise().setState(phase);
            model.saveExersise();
        }
    }

    @FXML
    private void onClose(){
        if(babysteps != null && babysteps.Running())
        babysteps.Stop();

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

    private void StartBabysteps(){
        if(model.isBabystepsEnabled()) {
            babysteps = new Babysteps(model.getBabyStepTimer(), this, babystepsLabel);
            babysteps.Start();
        }
    }

}
