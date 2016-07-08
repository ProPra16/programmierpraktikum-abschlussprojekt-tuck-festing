package TDDT.Editor;

import TDDT.XML_body.Exersise;
import TDDT.XML_body.Exersises;
import TDDT.XML_body.XMLController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*************************************************
 * Diese Klasse soll dazu dienen, um die Klasse Controller etwas vereinfachen/ordlich halten.
 * Hier haben wir nichts anderes als das Speichern und Löschen.
 * Ich würde auch bei weiterem Entwickeln unseres Projekts die entsprechende Methoden fürs Speichern
 * und Laden hier haben, denn es beschafft besseren Überblick;
 */

public class Model {
    Exersise currentExercise;
    Exersises allExercises;


    public Model()
    {
        currentExercise = loadExersise();
        System.out.print("test");
        //initiateAllTextFiles(currentExercise);

    }
    /*
    Save speichert mittels Files, mehr dazu kann man in der Klassenbeschreibung lesen :)
     */
   /* public void save(TextFile textFile){
        try {
            Files.write(textFile.getFile(), textFile.getContent(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public Exersise getExersise()
    {
        return this.currentExercise;
    }
    private  Exersise loadExersise()
    {
        // Diese Methode returned immmoment nur die erste Aufgabe später soll
        allExercises = XMLController.loadAllExercises();
        return allExercises.getExersises().get(0);
        //return XMLController.loadAllExercises().getExersises().get(0);

    }
    public void saveExersise(int index)
    {
        allExercises.getExersises().set(0, currentExercise );
        XMLController.saveAllExercises(allExercises);

    }

    /*
    Load liefert uns unsere gewrappte TextFile.
    Wir versuchen hier eine temporäre Liste mittels Files ablesen, und dann wrappen wir den Zeug.
    Wichtig ist das Fehlschlagen:
        sollte aus irgendeinen Grund das Laden nicht gelingen, stürzt das Programm immernoch nicht ab,
        da wir einfach einen leeren Wraper senden können (und das tuen wir auch :)).
    Man könnte und vlt sollte auch dann ein entsprechendes Alarm einbauen.
    Das kann man am besten in der Klasse Wrapper selbst machen, aber vorher soll alles andere gemacht werden,
    da wir die möglichen exceptions erst wissen sollen, damit wir die entsprechende Alarms konfigurieren.
     */
    public void setAllTextFiles(TextFile code, TextFile test, TextFile task)
    {
        code = new TextFile(currentExercise.getWriteableCode());
        test = new TextFile(currentExercise.getTestCode());
        task = new TextFile(currentExercise.getExersiseText());

    }

    public Wrapper<TextFile> load(Path file){
        try {
            List<String> lines = Files.readAllLines(file);
            return new Wrapper<>(true, new TextFile( lines));
        } catch (IOException e) {
            e.printStackTrace();
            return new Wrapper<>(false, null);
        }

    }
}