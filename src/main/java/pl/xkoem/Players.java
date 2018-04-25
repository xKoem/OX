package pl.xkoem;

import static pl.xkoem.Symbol.*;

public class Players {

    private Player[] players = new Player[2];

    public void setPlayers(String player1, String player2) {
        players[0] = new Player(player1, X);
        players[1] = new Player(player2, O);
    }

    public Player getPlayer(int playerNumber) {
        return players[playerNumber];  //todo do obtestowania
    }

    public void printPlayerNames() {
        System.out.println(String.format("Gracz O: %s Gracz X: %s", players[0].getName(), players[1].getName()));
    }
}
