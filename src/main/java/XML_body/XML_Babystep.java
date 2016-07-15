package XML_body;

/**
 * Created by stefa on 13.07.2016.
 */
public class XML_Babystep {
    boolean enableBabysteps;
    int babyStepsTimer;

    public int getBabyStepsTimer() {
        return babyStepsTimer;
    }

    public void setBabyStepsTimer(int babyStepsTimer) {
        this.babyStepsTimer = babyStepsTimer;
    }

    public boolean isEnableBabysteps() {

        return enableBabysteps;
    }

    public void setEnableBabysteps(boolean enableBabysteps) {
        this.enableBabysteps = enableBabysteps;
    }

    public XML_Babystep(String bool, String value)
    {
       /* if(bool == "true")enableBabysteps = true;
        else enableBabysteps = false;
        babyStepsTimer = Integer.parseInt(value);
        */


    }
}
