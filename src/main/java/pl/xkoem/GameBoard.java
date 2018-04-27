package pl.xkoem;

import org.omg.PortableInterceptor.INACTIVE;

import java.security.InvalidParameterException;
import java.util.function.Consumer;

public class GameBoard {

    private Symbol[] boardSymbols;
    private final Integer width;
    private final Integer height;
    private final Consumer<String> userOutput;
    private Integer newestPosition;


    public GameBoard(GameConfiguration gameConfiguration, Consumer<String> userOutput) {
        Integer[] boardSize = gameConfiguration.getBoardSize();
        width = boardSize[0];
        height = boardSize[1];
        this.userOutput = userOutput;
        Integer numberOfPositions = width * height;
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

    boolean isPositionValid(Integer position) {
        if(position < 0 || position > boardSymbols.length -1)
            return false;
        return boardSymbols[position] == null;
    }

    public void setSymbolAtPosition(Symbol symbol, Integer position) throws InvalidParameterException {
        if(!isPositionValid(position)) {
            throw new InvalidParameterException("Bad position");
        }
        boardSymbols[position] = symbol;
        newestPosition = position;

    }

    public Integer boardSize() {
        return boardSymbols.length;
    }

    Integer[] translate(Integer position) {
        Integer positions[] = new Integer[2];
        positions[1] = (position/width);
        positions[0] = position - (position/width) * width;
        return positions;
    }

    Integer getArrayPosition(Integer x, Integer y) {
        return y * width + x;
    }


    Symbol getSymbolAtPosition(Integer x, Integer y) {
        Integer position = getArrayPosition(x,y);
        if(position >= boardSymbols.length || position < 0) {
            return null; //todo throw error
        }
        return getSymbolAtPosition(position);

    }

     Integer getNewestPosition() {
        return newestPosition;
    }

    Symbol getSymbolAtPosition(Integer position) {
        return boardSymbols[position];
    }

    Integer getWidth() {
        return width;
    }

    Integer getHeight() {
        return height;
    }
}
