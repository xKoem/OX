package pl.xkoem.gamestates;

import pl.xkoem.DashBoard;

import java.util.function.Consumer;

public class EndOfGame {
    private final Consumer<String> userOutput;
    private final DashBoard results;

    public EndOfGame(Consumer<String> userOutput, DashBoard results) {
        this.userOutput = userOutput;
        this.results = results;
    }

    /**
     * Pojazuje rezultat calej rozgrywki na podstawie otrzymanego DashBoardu
     */

    public void showResults() {
        userOutput.accept("Results: ");
    }
}
