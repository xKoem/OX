package pl.xkoem;

import java.util.HashMap;

public class DashBoard {

    private static int POINTS_FOR_WIN = 3;
    private static int POINTS_FOR_DRAW = 1;

    private Player player1;
    private Player player2;

    private int player1Score;
    private int player2Score;

    DashBoard(Players players) {
        player1 = players.getPlayer(0);
        player2 = players.getPlayer(1);

        player1Score = 0;
        player2Score = 0;
    }

    public void addPointsToWinner(Player player) {
        if(player1.equals(player))
            player1Score += POINTS_FOR_WIN;
        else
            player2Score += POINTS_FOR_WIN;
    }

    public boolean isWinner() {
       return !(player1Score == player2Score);
    }

    private Player getWinner() {
        if(player1Score > player2Score)
            return player1;
        return player2;
    }

    public String getWinnerName() {
        return getWinner().getName();
    }

    public String getWinnerPoints() {
        return String.valueOf(player1Score > player2Score? player1Score:player2Score);
    }

    private Player getLoser() {
        if(player1Score < player2Score)
            return player1;
        return player2;
    }

    public String getLoserName() {
        return getLoser().getName();
    }

    public String getLoserPoints() {
        return String.valueOf(player1Score < player2Score? player1Score:player2Score);
    }

    public void addDrawPoints() {
        player1Score += POINTS_FOR_DRAW;
        player2Score += POINTS_FOR_DRAW;
    }

    public HashMap<Symbol, Integer> getPoints() {
        HashMap<Symbol, Integer> points = new HashMap<>();
        points.put(player1.getSymbol(), player1Score);
        points.put(player2.getSymbol(), player2Score);
        return points;
    }
}
