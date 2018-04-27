package pl.xkoem;

import java.security.InvalidParameterException;

public class GameConfiguration {

    private Integer sizeX;
    private Integer symbolsToWin;
    private Integer sizeY;
    private String language;

    GameConfiguration() {
        this(3,3,3);
    }

    GameConfiguration(Integer sizeX, Integer sizeY, Integer symbolsToWin) {
        setBoardSettings(sizeX, sizeY, symbolsToWin);
        setLanguage("pl");
    }

    public void setBoardSettings(Integer sizeX, Integer sizeY, Integer symbolsToWin) throws InvalidParameterException {
        if(symbolsToWin > sizeX || symbolsToWin > sizeY) {
            throw new InvalidParameterException("Not possible to win on board <"+sizeX+"/"+sizeY+"> when amount of charts to win is specified to " + symbolsToWin);
        }

        if(symbolsToWin < 3) {
            throw new InvalidParameterException("Too low value of chars to win: " + symbolsToWin + "should be greater than 2");
        }

        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.symbolsToWin = symbolsToWin;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public Integer[] getBoardSize() {
        return new Integer[]{sizeX, sizeY};
    }

    public Integer getSymbolsToWin() {
        return symbolsToWin;
    }
}
