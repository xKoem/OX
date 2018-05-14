package pl.xkoem.board;

import pl.xkoem.Symbol;
import pl.xkoem.userinterface.UserInterface;

class BoardDrawer {

    private static final int CELL_SPACING = 2;

    static void drawBoard(Symbol[] boardSymbols, UserInterface userInterface, int width, int height) {
        userInterface.accept(prepareBoard(boardSymbols, width, height));
    }

    private static String prepareBoard(Symbol[] boardSymbols, int width, int height) {
        StringBuilder stringBuilder = new StringBuilder();
        int position = 0;

        int maxSize = Integer.toString(boardSymbols.length).length();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBuilder.append(formatCell(boardSymbols[position], position++, maxSize));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static String formatCell(Symbol symbol, int position, int maxSize) {
        if (symbol == null) {
            return String.format("%1$" + (maxSize + CELL_SPACING) + "s", position);
        } else {
            return String.format("%1$" + (maxSize + CELL_SPACING + 9) + "s", symbol);
        }
    }

}