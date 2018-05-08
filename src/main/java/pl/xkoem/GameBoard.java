package pl.xkoem;

import pl.xkoem.userinterface.UserInterface;

import java.security.InvalidParameterException;

public class GameBoard {

    private Symbol[] boardSymbols;
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
        boardSymbols = new Symbol[numberOfPositions];
        newestPosition = -1;
    }

    public void drawBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        int position = 0;

        int maxSize = Integer.toString(boardSymbols.length).length();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                String s = String.format(
                        boardSymbols[position] == null? "%1$"+(maxSize+2)+"s": "%1$"+(maxSize+2 + 9)+"s" ,
                        boardSymbols[position] == null? position++: boardSymbols[position++]
                );
                stringBuilder.append(s);
            }
            stringBuilder.append("\n");
        }
        userInterface.accept(stringBuilder.toString());
    }

    boolean isPositionValid(int position) {
        if(position < 0 || position > boardSymbols.length -1)
            return false;
        return boardSymbols[position] == null;
    }

    public void setSymbolAtPosition(Symbol symbol, int position) throws InvalidParameterException {
        if(!isPositionValid(position)) {
            throw new InvalidParameterException("Bad position");
        }
        boardSymbols[position] = symbol;
        newestPosition = position;

    }

    public int boardSize() {
        return boardSymbols.length;
    }

    int[] translatePositionToCoordinates(int position) {
        int positions[] = new int[2];
        positions[1] = (position/width);
        positions[0] = position - (position/width) * width;
        return positions;
    }

    int translateCoordinatesToPosition(int x, int y) {
        return y * width + x;
    }

    Symbol getSymbolAtPosition(int x, int y) {
        int position = translateCoordinatesToPosition(x,y);
        if(position >= boardSymbols.length || position < 0) {
            throw new InvalidParameterException();
        }
        return getSymbolAtPosition(position);
    }

    public int getNewestPosition() {
        return newestPosition;
    }

    public Symbol getSymbolAtPosition(int position) {
        return boardSymbols[position];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}
