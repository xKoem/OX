package pl.xkoem;

import java.security.InvalidParameterException;

public class GameConfiguration {

    private Integer sizeX;
    private Integer charsToWin;
    private Integer sizeY;
    private String language;

    GameConfiguration() {
        this(8,8,8);
    }

    GameConfiguration(Integer sizeX, Integer sizeY, Integer charsToWin) {
        setBoardSettings(sizeX, sizeY, charsToWin);
        setLanguage("pl");
    }

    public void setBoardSettings(Integer sizeX, Integer sizeY, Integer charsToWin) throws InvalidParameterException {
        if(charsToWin > sizeX || charsToWin > sizeY) {
            throw new InvalidParameterException("Not possible to win on board <"+sizeX+"/"+sizeY+"> when amount of charts to win is specified to " + charsToWin);
        }

        if(charsToWin < 3) {
            throw new InvalidParameterException("Too low value of chars to win: " + charsToWin + "should be greater than 2");
        }

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.charsToWin = charsToWin;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public Integer[] getBoardSize() {
        return new Integer[]{sizeX, sizeY};
    }
}
