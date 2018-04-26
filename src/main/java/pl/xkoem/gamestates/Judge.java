package pl.xkoem.gamestates;

public class Judge {

    private int i;

    Judge() {
        this.i = 0;
    }

    boolean isMatchFinished() {
        i++;
        return i > 2;
    }
}
