package TDDT.XML_body;

/**
 * Created by Stefan on 25.06.2016.
 */
import java.util.ArrayList;
import java.util.List;

public class Exersise {
     String exersiseName;
     List<String> testCode;
     List<String> writeableCode;
     List<String>  exersiseText;
    //May add Constructors for the extensions
    public Exersise()
    {
        //Cant construct this body in Code
    }
    public void setTestCode(List<String> t)
    {
        writeableCode = t;
    }
    public void setWriteableCode(List<String> t)
    {
        testCode = t;
    }
    public void setExersiseText(List<String> t)
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

    public List<String> getTestCode() {
        return testCode;
    }
    public List<String> getWriteableCode() {
        return writeableCode;
    }

    public List<String> getExersiseText() {
        return exersiseText;
    }
}
