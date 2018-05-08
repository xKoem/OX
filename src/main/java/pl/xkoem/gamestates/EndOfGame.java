package pl.xkoem.gamestates;

import pl.xkoem.DashBoard;
import pl.xkoem.userinterface.UserInterface;

public class EndOfGame {
    private final UserInterface userInterface;
    private final DashBoard dashBoard;

    public EndOfGame(UserInterface userInterface, DashBoard dashBoard) {
        this.userInterface = userInterface;
        this.dashBoard = dashBoard;
    }

    /**
     * Pojazuje rezultat calej rozgrywki na podstawie otrzymanego DashBoardu
     */

    public void printResults() {
        StringBuilder results = new StringBuilder();
        results.append("Winner: ").append(dashBoard.getWinnerName()).append(" points: ").append(dashBoard.getWinnerPoints()).append("\n")
                .append("Loser: ").append(dashBoard.getLoserName()).append(" points: ").append(dashBoard.getLoserPoints()).append("\n");
        userInterface.accept(results.toString());
    }
}
