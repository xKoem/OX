package pl.xkoem.gamestates;

import pl.xkoem.DashBoard;
import pl.xkoem.userinterface.LanguageName;
import pl.xkoem.userinterface.ReplacePattern;
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
        if (dashBoard.isWinner()) {
            printWinnerResults();
        } else {
            printDrawResults();
        }
    }

    private void printDrawResults() {
        userInterface.accept(LanguageName.game_draw, new ReplacePattern("points", dashBoard.getWinnerPoints()));
    }

    private void printWinnerResults() {
        userInterface.accept(LanguageName.game_winner,
                new ReplacePattern("winner", dashBoard.getWinnerName()),
                new ReplacePattern("winner_points", dashBoard.getWinnerPoints()),
                new ReplacePattern("loser", dashBoard.getLoserName()),
                new ReplacePattern("loser_points", dashBoard.getLoserPoints()));
    }
}
