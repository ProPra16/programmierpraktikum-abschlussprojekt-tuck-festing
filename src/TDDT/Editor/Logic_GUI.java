package TDDT.Editor;
import TDDT.XML_body.*;
/**
 * Created by Stefan on 08.07.2016.
 */
public class Logic_GUI {
    public static Exersise loadExersise()
    {
        return XMLController.loadAllExercises().getExersises().get(0);

    }
    public static void saveExersise(Exersises e)
    {
        XMLController.saveAllExercises(e);

    }

}
