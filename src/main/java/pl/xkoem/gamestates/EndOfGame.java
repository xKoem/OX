package pl.xkoem.gamestates;

import pl.xkoem.DashBoard;

import java.util.function.Consumer;

public class EndOfGame {
    private final Consumer<String> userOutput;
    private final DashBoard dashBoard;

    public EndOfGame(Consumer<String> userOutput, DashBoard dashBoard) {
        this.userOutput = userOutput;
        this.dashBoard = dashBoard;
    }

    /**
     * Pojazuje rezultat calej rozgrywki na podstawie otrzymanego DashBoardu
     */

    public void printResults() {
        StringBuilder results = new StringBuilder();
        results.append("Winner: ").append(dashBoard.getWinnerName()).append(" points: ").append(dashBoard.getWinnerPoints()).append("\n")
                .append("Loser: ").append(dashBoard.getLoserName()).append(" points: ").append(dashBoard.getLoserPoints()).append("\n");
        userOutput.accept(results.toString());
    }
}
