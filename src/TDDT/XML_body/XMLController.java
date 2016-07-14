package TDDT.XML_body;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Created by Stefan on 25.06.2016.
 */
public class XMLController {

    public static void saveAllExercises(Exersises e)
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element exercises = doc.createElement("exercises");
            for(int i =0; i<e.getExersises().size(); i++)
            {
                Element exercise = doc.createElement("exercise");
                exercises.appendChild(exercise);
                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);
                Element exerciseText = doc.createElement("class");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseText()));
                exercise.appendChild(exerciseName);
                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);
                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);
                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);
                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);

            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        }
    }
    public static Exersises loadAllExercises()
    {
        Exersises e = new Exersises();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Aufgaben.xml"));
            NodeList exercises = doc.getElementsByTagName("exercise");
            for(int i= 0; i<exercises.getLength(); i++)
            {
                Element exercise = (Element) (exercises.item(i));
                String exerciseName = exercise.getElementsByTagName("exerciseName").item(0).getTextContent();
                String ExClass = exercise.getElementsByTagName("class").item(0).getTextContent();
                String testClass = exercise.getElementsByTagName("testClass").item(0).getTextContent();
                String exerciseText = exercise.getElementsByTagName("exerciseText").item(0).getTextContent();
                String state = exercise.getElementsByTagName("state").item(0).getTextContent();
                String enableBabysteps = exercise.getElementsByTagName("enableBabySteps").item(0).getTextContent();
                String BabyStepsTimer = exercise.getElementsByTagName("BabyStepTimer").item(0).getTextContent();

                e.addExercise(new Exersise(exerciseName,ExClass,testClass,exerciseText,state,enableBabysteps,BabyStepsTimer));

            }
            return e;


        }
        catch( IOException d){return null;}
        catch (ParserConfigurationException p ){return null;}
        catch(SAXException s){return null;}

    }


}
