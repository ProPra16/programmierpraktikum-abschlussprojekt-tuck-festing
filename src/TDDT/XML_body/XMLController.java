package TDDT.XML_body;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
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
            doc.appendChild(exercises);
            for(int i =0; i<e.getExersises().size(); i++)
            {
                Element exercise = doc.createElement("exercise");
                exercises.appendChild(exercise);

                Element exerciseName = doc.createElement("exerciseName");
                exerciseName.appendChild(doc.createTextNode(e.getExersises().get(i).getExersiseName()));
                exercise.appendChild(exerciseName);

                Element exerciseText = doc.createElement("exerciseText");
                exerciseText.appendChild(doc.createTextNode(e.getExersises().get(i).getExerciseTextAsString()));
                exercise.appendChild(exerciseText);

                Element writeableCode = doc.createElement("class");
                writeableCode.appendChild(doc.createTextNode(e.getExersises().get(i).getCodeAsString()));
                exercise.appendChild(writeableCode);

                Element testCode = doc.createElement("testClass");
                testCode.appendChild(doc.createTextNode(e.getExersises().get(i).getTestCodeAsString()));
                exercise.appendChild(testCode);

                Element state = doc.createElement("state");
                state.appendChild(doc.createTextNode(String.valueOf(e.getExersises().get(i).getState())));
                exercise.appendChild(state);

                Element enableBabysteps = doc.createElement("enableBabySteps");
                enableBabysteps.appendChild(doc.createTextNode(String.valueOf(e.getExersises().get(i).getEnableBabysteps())));
                exercise.appendChild(enableBabysteps);

                Element BabyStepTimer = doc.createElement("BabyStepTimer");
                BabyStepTimer.appendChild(doc.createTextNode(String.valueOf(e.getExersises().get(i).getBabyStepsTimer())));
                exercise.appendChild(BabyStepTimer);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource fromDoc = new DOMSource(doc);
                StreamResult toFile = new StreamResult(new FileOutputStream("Aufgaben.xml"));

                transformer.transform(fromDoc, toFile);

            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        } catch (TransformerException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
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
