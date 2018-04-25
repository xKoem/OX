package pl.xkoem.gamestates;

import pl.xkoem.Players;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Init {

    private final Supplier<String> userInput;
    private Players players;
    private final Consumer<String> userOutput;

    public Init(Supplier<String> userInput, Consumer<String> userOutput, Players players) {
        this.userInput = userInput;
        this.players = players;
        this.userOutput = userOutput;
    }

    public Players askForNames() {
        userOutput.accept("Podaj nazwe gracza O: ");
        String playerO = userInput.get();
        userOutput.accept("Podaj nazwe gracza X: ");
        String playerX = userInput.get();

        players.setPlayers(playerO, playerX);
        return players;
    }
}
