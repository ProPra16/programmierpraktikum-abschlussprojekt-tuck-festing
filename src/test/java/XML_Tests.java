import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import XML_body.*;

/**
 * Created by Stefan on 26.06.2016.
 */
public class XML_Tests {
    Exersises allExersises;
    @Before
    public void createSomeXMLObject()
    {
        allExersises = new Exersises();
        Exersise  exersise = new Exersise();
        exersise.setExersiseName("test");
        ArrayList<Exersise> exersiseList = new ArrayList<Exersise>();
        exersiseList.add(exersise);
        allExersises.setExersises(exersiseList);
    }
    @Test
    public  void test() {

        // System.out.println(e.exersises.get(0).exersiseName);
        XMLController.saveAllExercises(allExersises);
        Exersises x = XMLController.loadAllExercises();
        assertEquals(allExersises.getExersises().get(0).getExersiseName(),x.getExersises().get(0).getExersiseName());
        System.out.println("Testing save and load ");

    }
}
