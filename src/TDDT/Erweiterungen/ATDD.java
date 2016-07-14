package TDDT.Erweiterungen;

import javafx.scene.control.Label;

public class ATDD {

    numberoffailedfeaturetests();
    numberfailedtests();

    public int checkForSwap( int phase, int Afails, int fails) {

        if (phase == 3 && fails == 1)
            phase=0;

        if (phase == 3 && fails == 1)
            phase=1;

        if (phase == 0 && fails == 1)
            phase=1;

        if (phase == 0 && fails == 1)
            phase=1;

        if (phase == 0 && fails == 1)
            phase=1;

        if (phase == 0 && fails == 1)
            phase=1;

        if (phase == 0 && fails == 1)
            phase=1;


        return phase;
    }
    public Label labelMode(Label aTDDlabel){
        return aTDDlabel;
    }

}