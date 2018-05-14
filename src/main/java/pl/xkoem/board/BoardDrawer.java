package pl.xkoem.board;

import pl.xkoem.Symbol;
import pl.xkoem.userinterface.UserInterface;

class BoardDrawer {

    private static final int CELL_SPACING = 2;
    private static final int ADDITIONAL_SPACING_FOR_ASCII_CODE = 9;

    static void drawBoard(BoardSymbols boardSymbols, UserInterface userInterface, int width, int height) {
        userInterface.accept(prepareBoard(boardSymbols, width, height));
    }

    private static String prepareBoard(BoardSymbols boardSymbols, int width, int height) {
        StringBuilder stringBuilder = new StringBuilder();
        int position = 0;

        int maxSize = Integer.toString(boardSymbols.size()).length();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(formatCell(boardSymbols.get(position), position++, maxSize));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static String formatCell(Symbol symbol, int position, int maxSize) {
        if (symbol == Symbol.EMPTY) {
            return String.format("%1$" + (maxSize + CELL_SPACING) + "s", position);
        } else {
            return String.format("%1$" + (maxSize + CELL_SPACING + ADDITIONAL_SPACING_FOR_ASCII_CODE) + "s", symbol);
        }
    }

}