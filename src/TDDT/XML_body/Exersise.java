package TDDT.XML_body;

/**
 * Created by Stefan on 25.06.2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Exersise {
     String exersiseName;
     ArrayList<String> testCode;
     ArrayList<String> writeableCode;
     ArrayList<String>  exersiseText;
    Boolean enableBabysteps;
    int BabyStepsTimer;
     int state;


    public int getState() {

        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //May add Constructors for the extensions
    public Exersise(String exersiseName, String exersiseClass, String testClass , String Aufgabe ,String state, String enableBabySteps, String BabyStepsTimer)
    {
        this.exersiseName = exersiseName;
        this.testCode = getToArray(testClass);
        this.writeableCode = getToArray(testClass);
        this.exersiseText = getToArray(Aufgabe);
        this.state = Integer.parseInt(state);
        this.enableBabysteps = Boolean.parseBoolean(enableBabySteps);
        this.BabyStepsTimer = Integer.parseInt(BabyStepsTimer);

    }
    public String 
    private ArrayList<String> getToArray(String t)
    {
        ArrayList<String> test = new ArrayList<String>();
        test.addAll( Arrays.asList(t.split("\n")));
        return test;
    }
    public Boolean getEnableBabysteps() {
        return enableBabysteps;
    }

    public void setEnableBabysteps(Boolean enableBabysteps) {
        this.enableBabysteps = enableBabysteps;
    }
    public int getBabyStepsTimer() {
        return BabyStepsTimer;
    }

    public void setBabyStepsTimer(int babyStepsTimer) {
        BabyStepsTimer = babyStepsTimer;
    }
    public void setTestCode(ArrayList<String> t)
    {
        testCode = t;
    }
    public void setWriteableCode(ArrayList<String> t)
    {
       writeableCode = t;
    }
    public void setExersiseText(ArrayList<String> t)
    {
        exersiseText = t;
    }
    public void setTestCode(String t)
    {
        exersiseName = t;
    }


    public String getExersiseName() {
        return exersiseName;
    }

    public void setExersiseName(String exersiseName) {
        this.exersiseName = exersiseName;
    }

    public ArrayList<String> getTestCode() {
        return testCode;
    }
    public ArrayList<String> getWriteableCode() {
        return writeableCode;
    }

    public ArrayList<String> getExersiseText() {
        return exersiseText;
    }
}
