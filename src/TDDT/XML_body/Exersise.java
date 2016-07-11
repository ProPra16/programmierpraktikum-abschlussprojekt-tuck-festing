package TDDT.XML_body;

/**
 * Created by Stefan on 25.06.2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Exersise {
     String exersiseName;
     ArrayList<String> testCode;
     ArrayList<String> writeableCode;
     ArrayList<String>  exersiseText;
    //May add Constructors for the extensions
    public Exersise()
    {
        //Cant construct this body in Code
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
