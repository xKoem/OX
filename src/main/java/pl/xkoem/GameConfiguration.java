package pl.xkoem;

import java.security.InvalidParameterException;

public class GameConfiguration {

    private int sizeX;
    private int symbolsToWin;
    private int sizeY;
    private String language;

    GameConfiguration() {
        this(5,5,5);
    }

    GameConfiguration(int sizeX, int sizeY, int symbolsToWin) {
        setBoardSettings(sizeX, sizeY, symbolsToWin);
        setLanguage("pl");
    }

    public void setBoardSettings(int sizeX, int sizeY, int symbolsToWin) throws InvalidParameterException {
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


    public int[] getBoardSize() {
        return new int[]{sizeX, sizeY};
    }

    public int getSymbolsToWin() {
        return symbolsToWin;
    }
}
