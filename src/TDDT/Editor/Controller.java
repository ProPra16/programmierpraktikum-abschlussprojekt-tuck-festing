package TDDT.Editor;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;
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

    private TextFile current;
    private TextFile task;
    private TextFile code;
    private TextFile test;
    private boolean state = true;                   //if true -> TextFiles sind an desssen stellen.
                                                    //Das ist für die implimentierung von der
                                                    //Taste Compile wichtig, damit man weiss,
                                                    //wo gerade der Code und wo die Tests sind

    private Model model;

    public Controller(Model model){
        this.model = model;
    }


    /*
    -"Wieso so ein Komischer Name?"
    -keine Ahnung. Auf jedem Fall ist das hier nichts anderes als umtauschen von dem inhald der TextAreas
    hier soll dann auch das befehl für das Kompilieren sein.
     */
    @FXML
    private void makeStep(){
        if(state) {
            onSaveCode();
            uneditableAreaText.clear();
            code.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));

            onSaveTest();
            areaText.clear();
            test.getContent().forEach(line -> areaText.appendText(line +"\n"));

        }
        else{onSaveCode();
            areaText.clear();
            code.getContent().forEach(line -> areaText.appendText(line +"\n"));

            onSaveTest();
            uneditableAreaText.clear();
            test.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));

        }
        this.state = !this.state;
    }

    /*
    Alle meine befehle zum Laden funktionieren auf die selbe wiese:
    Einen Filechooser erzäugen -> dem user es zeigen ->
    ->mittels load aus der Klasse Model ein wrapper füllen -> in die entsprechende Area zeilenweise packen
     */
    @FXML
    private void onLoadTask(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("./"));
        File file = chooser.showOpenDialog(null);
        if(file != null){
            Wrapper<TextFile> io = model.load(file.toPath());
            if(io.returnStatus() && io.hasData()){
                task = io.getData();

                uneditableAreaTask.clear();
                task.getContent().forEach(line -> uneditableAreaTask.appendText(line +"\n"));
            }
            else{
                System.out.print("Failed");
            }
        }

    }

    @FXML
    private void onLoadTest(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("./"));
        File file = chooser.showOpenDialog(null);
        if(file != null){
            Wrapper<TextFile> io = model.load(file.toPath());
            if(io.returnStatus() && io.hasData()){
                test = io.getData();

                uneditableAreaText.clear();
                test.getContent().forEach(line -> uneditableAreaText.appendText(line +"\n"));
            }
            else{
                System.out.print("Failed");
            }
        }

    }
    @FXML
    private void onLoadCode(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("./"));
        File file = chooser.showOpenDialog(null);
        if(file != null){
            Wrapper<TextFile> io = model.load(file.toPath());
            if(io.returnStatus() && io.hasData()){
                code = io.getData();

                areaText.clear();
                code.getContent().forEach(line -> areaText.appendText(line +"\n"));
            }
            else{
                System.out.print("Failed");
            }
        }

    }
    /*
    Das Speichern funktioniert folgender weise für alle:
    ein neues TextFile mit dem Pfad von vorherigen TextFile und denn daten, die man aus TextArea abliest ->
    ->mithilfe von Model dies speichern

    dies wird in Zusammenhang mit der Boolscher wert state gemacht, da das Fenster mit Code und das Fenster mit Text
    könnten gerade umgetauscht sein.
     */

    @FXML
    private void onSaveTest(){
        if(state) {
            TextFile textFile = new TextFile(test.getFile(), Arrays.asList(uneditableAreaText.getText().split("\n")));
            model.save(textFile);
        }
        else{
            TextFile textFile = new TextFile(test.getFile(), Arrays.asList(areaText.getText().split("\n")));
            model.save(textFile);
        }

    }

    @FXML
    private void onSaveCode(){
        if(state) {
            TextFile textFile = new TextFile(code.getFile(), Arrays.asList(areaText.getText().split("\n")));
            model.save(textFile);
        }
        else{
            TextFile textFile = new TextFile(code.getFile(), Arrays.asList(uneditableAreaText.getText().split("\n")));
            model.save(textFile);
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
