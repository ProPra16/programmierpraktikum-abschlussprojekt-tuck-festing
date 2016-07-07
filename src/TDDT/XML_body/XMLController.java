package TDDT.XML_body;

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
            e.createExersise();
            FileOutputStream xmlStream = new FileOutputStream("example.xml");
            java.beans.XMLEncoder xmlEncoder = new java.beans.XMLEncoder(xmlStream);
            xmlEncoder.writeObject(e);
            xmlEncoder.flush();
            xmlEncoder.close();
        }
        catch(IOException io)
        {
            System.out.println("Something went wrong");
        }
    }
    public static Exersises loadAllExercises()
    {
        try{
            Exersises t = new Exersises();
            FileInputStream xmlStream = new FileInputStream("example.xml");
            java.beans.XMLDecoder xmlDecoder = new java.beans.XMLDecoder(xmlStream);
            t = (Exersises) xmlDecoder.readObject();
            xmlDecoder.close();
            return t;
        }
        catch(IOException io)
        {
            System.out.println("Something went wrong");
            return null;
        }

    }


}
