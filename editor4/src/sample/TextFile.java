package sample;

import java.nio.file.Path;
import java.util.List;

/**************
 * Hier haben wir einen shönen Objekt, der unsere Liste mit Strings hat und einen Pfad für die
 * entsprechende datei besitzt. Sonst ist alles hier glaube ich auch selbsterklärend.
 */

public class TextFile {
    private final Path file;

    private final List<String> content;

    public TextFile(Path file, List<String> content){
        this.file = file;
        this.content = content;
    }
    public Path getFile(){
        return this.file;
    }
    public List<String> getContent(){
        return this.content;
    }
    
}
