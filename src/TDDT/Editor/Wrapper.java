package TDDT.Editor;

/*
Ein einfacher Wrapper, der für die Klasse TextFile erzeugt ist.
Der Grund dafür ist folgender:
    Das Lesen und schreiben erfolg durch files, und daher könnte es auch fehlschlagen,
    also machte ich einen einfachsten Wrapper, um immer sicher sein, dass etwas zurückgegeben wird,
    auch wenn es null ist

Besser versteht man den Grund in der Klasse Model, Methode load();
 */
public class Wrapper<T> {
    private T text;
    private boolean state;

    public Wrapper(boolean state, T text){                  //Erzeugen der Wrapper
        this.text = text;
        this.state = state;
    }
    public boolean hasData(){                               //Soll ich das hier wirklich erklären? XD
        return text!=null;
    }
    public T getData(){
        return text;
    }
    public boolean returnStatus(){
        return this.state;
    }
}
