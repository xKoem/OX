package pl.xkoem;

import java.security.InvalidParameterException;

public class GameConfiguration {

    private Integer sizeX;
    private Integer signsToWin;
    private Integer sizeY;
    private String language;

    GameConfiguration() {
        this(3,3,3);
    }

    GameConfiguration(Integer sizeX, Integer sizeY, Integer signsToWin) {
        setBoardSettings(sizeX, sizeY, signsToWin);
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
        this.signsToWin = charsToWin;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public Integer[] getBoardSize() {
        return new Integer[]{sizeX, sizeY};
    }

    public Integer getSignsToWin() {
        return signsToWin;
    }
}
