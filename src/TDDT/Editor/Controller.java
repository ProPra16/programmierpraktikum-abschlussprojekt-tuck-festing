package TDDT.Editor;

import TDDT.Compiler.CompileHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Arrays;
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


    private Model model;

    public Controller(){
        model = new Model();
        setAllTextFiles(model.getAllTextFiles(code, test, task));
        this.compileHelper = new CompileHelper();
    }

    @FXML
    private void makeStep(){
        if(state) {
            onSaveTest();

            codeArea.clear();
            code.getContent().forEach(line -> codeArea.appendText(line +"\n"));

            testArea.clear();
            test.getContent().forEach(line -> testArea.appendText(line +"\n"));

            compileHelper.AddSourceClass("Class", codeArea.getText());
            compileHelper.SetTest("TestClass", testArea.getText());
        }
        else{
            onSaveTest();

            testArea.clear();
            code.getContent().forEach(line -> testArea.appendText(line +"\n"));

            codeArea.clear();
            test.getContent().forEach(line -> codeArea.appendText(line +"\n"));

            compileHelper.AddSourceClass("Class", testArea.getText());
            compileHelper.SetTest("TestClass", codeArea.getText());
        }
        this.state = !this.state;

        compileHelper.CompileAndTest();

        //finish this

        if(compileHelper.HasCompilerErrors()){
            console.appendText(compileHelper.GetCompilerErros());
        }
        else if(compileHelper.NumberOfFailedTests() > 0){
            console.appendText(compileHelper.GetTestFaillures());
        }
        else {
            console.appendText("Test passed.");
        }



    }


    @FXML
    private void onLoadTask(){ // die Load methode.(muss möglicherweise an den state angepasst werden)
        codeArea.clear();
        testArea.clear();
        taskArea.clear();
        model = new Model();
        setAllTextFiles(model.getAllTextFiles(code, test, task));

        code.getContent().forEach(line -> codeArea.appendText(line +"\n"));
        test.getContent().forEach(line -> testArea.appendText(line +"\n"));
        task.getContent().forEach(line -> taskArea.appendText(line +"\n"));

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
            code = new TextFile(Arrays.asList(codeArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(testArea.getText().split("\n")));
            model.getExersise().setWriteableCode(code.getAsArrayList());
            model.getExersise().setTestCode(test.getAsArrayList());
            model.getExersise().setExersiseText(task.getAsArrayList());
            model.saveExersise(0);
        }
        else{
            task = new TextFile(Arrays.asList(taskArea.getText().split("\n")));
            code = new TextFile(Arrays.asList(testArea.getText().split("\n")));
            test = new TextFile(Arrays.asList(codeArea.getText().split("\n")));
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
