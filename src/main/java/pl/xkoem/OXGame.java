package pl.xkoem;

import pl.xkoem.gamestates.EndOfGame;
import pl.xkoem.gamestates.Init;
import pl.xkoem.gamestates.Match;

import java.lang.management.PlatformLoggingMXBean;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OXGame {

    private Players players;
    private final Supplier<String> userInput;
    private final Consumer<String> userOutput;
    private GameConfiguration gameConfiguration;


    public OXGame(Players players, Supplier<String> userInput, Consumer<String> userOutput, GameConfiguration gameConfiguration) {
        this.players = players;
        this.userInput = userInput;
        this.userOutput = userOutput;
        this.gameConfiguration = gameConfiguration;
    }



    /**
     * Główna metoda uruchamiająca cały proces gry po kolei
     */

    void run() {
        initGame();

        Match match = new Match(userInput, userOutput, players, gameConfiguration);

        do {
            match.begin();
        } while (!match.isFinished());

        EndOfGame endOfGame = new EndOfGame(userOutput, match.getResults());
        endOfGame.showResults();
    }

    private void initGame() {
        Init init = new Init(userInput, userOutput, players, gameConfiguration);
        players = init.askForNames();
        gameConfiguration = init.askForConfiguration(gameConfiguration);
        players.printPlayerNames();
    }


}
