package pl.xkoem;

import static pl.xkoem.Symbol.*;

public class Players {

    private Player[] players = new Player[2];

    public void setPlayers(String player1, String player2) {
        players[0] = new Player(player1, O);
        players[1] = new Player(player2, X);
    }

    Player getPlayer(int playerNumber) {
        return players[playerNumber];
    }

    public Player getOppositePlayer(Player player) {
        if(players[0].equals(player))
            return players[1];
        return players[0];
    }

    public Player getPlayer(Symbol symbol) {
        if(players[0].getSymbol().equals(symbol))
            return players[0];
        return players[1];
    }
}
