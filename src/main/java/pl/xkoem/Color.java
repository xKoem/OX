package pl.xkoem;

public enum Color {
    ANSI_DEFAULT("\u001B[0m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m");

    private String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
