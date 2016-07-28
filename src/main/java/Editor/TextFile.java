package Editor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**************
 * Hier haben wir einen shönen Objekt, der unsere Liste mit Strings hat und einen Pfad für die
 * entsprechende datei besitzt. Sonst ist alles hier glaube ich auch selbsterklärend.
 */

public class TextFile {


    private final List<String> content;

    public TextFile( List<String> content){

        this.content = content;
    }

    public List<String> getContent(){
        return this.content;
    }
    public ArrayList<String> getAsArrayList()
    {
    ArrayList<String> t = new ArrayList<String>();
        t.addAll(content);
        return t;
    }
    public String getString(){

        String result = "";
        for(String text : content){
            result += text + "\n";
        }
        return result;
    }
    
}
