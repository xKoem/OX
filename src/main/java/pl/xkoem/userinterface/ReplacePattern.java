package pl.xkoem.userinterface;

public class ReplacePattern {
    private final String from;
    private final String to;

    public ReplacePattern(String from, String to) {
        this.from = from;
        this.to = to;
    }

    String getFrom() {
        return "%" + from + "%";
    }

    String getTo() {
        return to;
    }
}
