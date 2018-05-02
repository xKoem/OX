package pl.xkoem;

import java.security.InvalidParameterException;
import java.util.function.Consumer;

public class GameBoard {

    private Symbol[] boardSymbols;
    private final int width;
    private final int height;
    private final Consumer<String> userOutput;
    private int newestPosition;

    public GameBoard(GameConfiguration gameConfiguration, Consumer<String> userOutput) {
        int[] boardSize = gameConfiguration.getBoardSize();
        width = boardSize[0];
        height = boardSize[1];
        this.userOutput = userOutput;
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
                String s = String.format( //fixme
                        boardSymbols[position] == null? "%1$"+(maxSize+2)+"s": "%1$"+(maxSize+2 + 9)+"s" ,
                        boardSymbols[position] == null? position++: boardSymbols[position++]
                );
                stringBuilder.append(s);
            }
            stringBuilder.append("\n");
        }
        userOutput.accept(stringBuilder.toString());
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
            return null; //todo throw error
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
