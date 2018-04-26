package pl.xkoem;

import java.util.function.Consumer;

public class GameBoard {

    private Symbol[] boardSymbols;
    private final Integer width;
    private final Integer height;
    private final Consumer<String> userOutput;


    public GameBoard(GameConfiguration gameConfiguration, Consumer<String> userOutput) {
        Integer[] boardSize = gameConfiguration.getBoardSize();
        width = boardSize[0];
        height = boardSize[1];
        this.userOutput = userOutput;
        Integer numberOfPositions = width * height;
        boardSymbols = new Symbol[numberOfPositions];
    }

    public void drawBoard() {
        StringBuilder stringBuilder = new StringBuilder();
        int position = 0;

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                stringBuilder.append(boardSymbols[position] == null? position++: boardSymbols[position++]);
            }
            stringBuilder.append("\n");
        }
        userOutput.accept(stringBuilder.toString());
    }
}
