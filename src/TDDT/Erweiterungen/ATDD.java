package TDDT.Erweiterungen;

public class ATDD {

    private boolean test = false;
    private boolean code = false;
    private int fails;

    public boolean checkForSwap(int fails, boolean state) {

        if (state == true) {

            if (fails == 1) {
                code = true;
                return code;
            } else {
                if (fails > 1) {
                    test = true;
                    return test;
                }
            }
        }
        return false;
    }
}