package XML_body;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stefan on 25.06.2016.
 */
public class Exersises {
     ArrayList<Exersise> exersises = new ArrayList<Exersise>();
    public Exersises(){
        //Cant construct this body in Code
    }
    public void addExercise(Exersise e)
    {
        exersises.add(e);
    }
    /*public void createExersise()
    {
        exersises.add(new Exersise());
    }*/

    public ArrayList<Exersise> getExersises() {
        return exersises;
    }

    public void setExersises(ArrayList<Exersise> exersises) {
        this.exersises = exersises;
    }
}
