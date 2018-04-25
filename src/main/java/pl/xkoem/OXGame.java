package pl.xkoem;

import pl.xkoem.gamestates.Init;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {

    private Players players;
    private final Supplier<String> userInput;
    private final Consumer<String> userOutput;

    public OXGame(Players players, Supplier<String> userInput, Consumer<String> userOutput) {
        this.players = players;
        this.userInput = userInput;
        this.userOutput = userOutput;
    }

    public void run() {
        Init init = new Init(userInput, userOutput, players);
        players = init.askForNames();

        players.printPlayerNames();

    }



}
