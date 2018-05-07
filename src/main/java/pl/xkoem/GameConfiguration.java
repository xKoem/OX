package pl.xkoem;

import java.security.InvalidParameterException;

public class GameConfiguration {


    private int MIN_X = 3;
    private int MIN_Y = 3;
    private int MIN_SYMBOLS_TO_WIN = 3;

    private int sizeX;
    private int symbolsToWin;
    private int sizeY;
    private String language;

    public GameConfiguration() {
        this(3,3,3);
    }

    GameConfiguration(int sizeX, int sizeY, int symbolsToWin) {
        setBoardSettings(sizeX, sizeY, symbolsToWin);
        setLanguage("pl");
    }

    public void setBoardSettings(int sizeX, int sizeY, int symbolsToWin) throws InvalidParameterException {
        if(symbolsToWin > sizeX || symbolsToWin > sizeY) {
            throw new InvalidParameterException("Not possible to win on board <"+sizeX+"/"+sizeY+"> when amount of charts to win is specified to " + symbolsToWin);
        }

        if(symbolsToWin < MIN_SYMBOLS_TO_WIN) {
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

    public int getMinSymbolsToWin() {
        return MIN_SYMBOLS_TO_WIN;
    }

    public int getMinY() {
        return MIN_Y;
    }

    public int getMinX() {
        return MIN_X;
    }



}
