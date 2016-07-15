package Editor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntSenderModel {
    private final IntegerProperty number = new SimpleIntegerProperty();

    public IntegerProperty getProperty(){
        return this.number;
    }
    public Integer getNumber(){
        return this.number.get();
    }
    public void setNumber(int number){
        this.number.set(number);
    }
}
