package pl.xkoem;

public enum Symbol {
    X(Color.ANSI_GREEN + "X" + Color.ANSI_DEFAULT) {
        @Override
        public Symbol getOpponent() {
            return O;
        }
    },
    O(Color.ANSI_RED + "O" + Color.ANSI_DEFAULT){
        @Override
        public Symbol getOpponent() {
            return X;
        }
    };

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public abstract Symbol getOpponent();

    @Override
    public String toString() {
        return symbol;
    }
}
