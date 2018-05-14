package pl.xkoem.board;

import pl.xkoem.GameConfiguration;
import pl.xkoem.Symbol;
import pl.xkoem.userinterface.UserInterface;

import java.security.InvalidParameterException;

public class GameBoard {
    private BoardSymbols boardSymbols;
    private final int width;
    private final int height;
    private final UserInterface userInterface;
    private int newestPosition;

    public GameBoard(GameConfiguration gameConfiguration, UserInterface userInterface) {
        int[] boardSize = gameConfiguration.getBoardSize();
        this.width = boardSize[0];
        this.height = boardSize[1];
        this.userInterface = userInterface;
        int numberOfPositions = width * height;
        boardSymbols = new BoardSymbols(numberOfPositions);
        newestPosition = -1;
    }

    public void drawBoard() {
        BoardDrawer.drawBoard(boardSymbols, userInterface, width, height);
    }

    public boolean isPositionValid(int position) {
        if(position < 0 || position > boardSymbols.size() -1)
            return false;
        return boardSymbols.isEmpty(position);
    }

    public void setSymbolAtPosition(Symbol symbol, int position) throws InvalidParameterException {
        if(!isPositionValid(position)) {
            throw new InvalidParameterException("Bad position");
        }
        boardSymbols.set(position, symbol);
        newestPosition = position;

    }

    public int boardSize() {
        return boardSymbols.size();
    }

    public int[] translatePositionToCoordinates(int position) {
        int positions[] = new int[2];
        positions[1] = (position/width);
        positions[0] = position - (position/width) * width;
        return positions;
    }

    public int translateCoordinatesToPosition(int x, int y) {
        return y * width + x;
    }

    public Symbol getSymbolAtPosition(int x, int y) {
        int position = translateCoordinatesToPosition(x,y);
        if(position >= boardSymbols.size() || position < 0) {
            throw new InvalidParameterException();
        }
        return getSymbolAtPosition(position);
    }

    public int getNewestPosition() {
        return newestPosition;
    }

    public Symbol getSymbolAtPosition(int position) {
        return boardSymbols.get(position);
    }

    public int getWidth() {
        return width;
    }
}
