package pl.xkoem.board;

import pl.xkoem.Symbol;

import java.util.HashMap;

class BoardSymbols {

    private HashMap<Integer, Symbol> boardSymbols;
    private final int numberOfPositions;

    BoardSymbols(int numberOfPositions) {
        this.boardSymbols = new HashMap<>();
        this.numberOfPositions = numberOfPositions;
    }

    int size() {
        return numberOfPositions;
    }

    Symbol get(int position) {
        if (isEmpty(position)) {
            return Symbol.EMPTY;
        } else {
            return boardSymbols.get(position);
        }
    }

    boolean isEmpty(int position) {
        return !boardSymbols.containsKey(position);
    }

    void set(int position, Symbol symbol) {
        boardSymbols.put(position, symbol);
    }
}
