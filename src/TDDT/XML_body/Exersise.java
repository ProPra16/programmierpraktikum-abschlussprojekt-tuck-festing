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
    boolean enableATTD;
    ArrayList<String> ATTD;

    public ArrayList<String> getATTD() {
        return ATTD;
    }

    public void setATTD(ArrayList<String> ATTD) {
        this.ATTD = ATTD;
    }

    public boolean isEnableATTD() {

        return enableATTD;
    }

    public void setEnableATTD(boolean enableATTD) {
        this.enableATTD = enableATTD;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //May add Constructors for the extensions
    public Exersise(String exersiseName, String exerciseClass, String testClass , String Aufgabe ,String state, String enableBabySteps, String BabyStepsTimer, String enableATTD, String ATTD)
    {
        this.exersiseName = exersiseName;
        this.testCode = getToArray(testClass);
        this.writeableCode = getToArray(exerciseClass);
        this.exersiseText = getToArray(Aufgabe);
        this.state = Integer.parseInt(state);
        this.enableBabysteps = Boolean.parseBoolean(enableBabySteps);
        this.BabyStepsTimer = Integer.parseInt(BabyStepsTimer);
        this.enableATTD = Boolean.parseBoolean(enableATTD);
        this.ATTD = getToArray(ATTD);
    }
    public String getATTDCodeAsString()
    {
        String returner = "";
        for(String t : ATTD)
        {
            returner += t + "\n";
        }
        return returner;
    }
    public String getTestCodeAsString()
    {
        String returner = "";
        for(String t : testCode)
        {
            returner += t + "\n";
        }
        return returner;
    }
    public String getCodeAsString()
    {
        String returner = "";
        for(String t : writeableCode)
        {
            returner += t + "\n";
        }
        return returner;
    }
    public String getExerciseTextAsString()
    {
        String returner = "";
        for(String t : exersiseText)
        {
            returner += t + "\n";
        }
        return returner;
    }
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
