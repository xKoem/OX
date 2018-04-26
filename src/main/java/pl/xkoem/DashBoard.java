package pl.xkoem;

import java.util.*;

public class DashBoard {

    private HashMap<Player, Integer> playersScores = new HashMap<>();

    DashBoard(Players players) {
        playersScores.put(players.getPlayer(0), 0);
        playersScores.put(players.getPlayer(1), 0);
    }

    public void addWinner(Player player) {
        playersScores.replace(player, playersScores.get(player)+3);
    }

    boolean isWinner() {
        Collection<Integer> values = playersScores.values();
        Iterator<Integer> iterator = values.iterator();
        Integer firstValue = iterator.next();
        return !firstValue.equals(iterator.next());
    }

    public String getWinner() {
        return " ";
    }

    public String getWinnerPoints() {
        return " ";
    }
    public String getLoser() {
        return " ";
    }

    public String getLoserPoints() {
        return " ";
    }
}
