package pl.xkoem;

public enum Symbol {
    X(Color.ANSI_GREEN + "X" + Color.ANSI_DEFAULT),
    O(Color.ANSI_RED + "O" + Color.ANSI_DEFAULT);

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
