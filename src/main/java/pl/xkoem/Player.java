package pl.xkoem;

public class Player {
    private final String playerName;
    private final Symbol symbol;

    Player(String playerName, Symbol symbol) {
        this.playerName = playerName;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object object) { //todo upgrade player equals

        if(!(object instanceof Player)) {
            return false;
        }
        Player player = (Player) object;

        if(playerName == null ||
                player.playerName == null ||
                symbol == null ||
                player.symbol == null)
            return false;

        return playerName.equals(player.playerName) && symbol.equals(player.symbol);
    }

    public String getName() {
        return playerName;
    }
}
