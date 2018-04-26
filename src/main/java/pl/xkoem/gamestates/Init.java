package pl.xkoem.gamestates;

import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Init {

    private final Supplier<String> userInput;
    private final GameConfiguration gameConfiguration;
    private Players players;
    private final Consumer<String> userOutput;

    public Init(Supplier<String> userInput, Consumer<String> userOutput, Players players, GameConfiguration gameConfiguration) {
        this.userInput = userInput;
        this.players = players;
        this.userOutput = userOutput;
        this.gameConfiguration = gameConfiguration;
    }

    public Players askForNames() {
        userOutput.accept("Podaj nazwe gracza O: ");
        String playerO = userInput.get();
        userOutput.accept("Podaj nazwe gracza X: ");
        String playerX = userInput.get();
        players.setPlayers(playerO, playerX);
        return players;
    }
    
    public GameConfiguration askForConfiguration(GameConfiguration gameConfiguration) {
        askForBoardSettings();
        askForLanguage();
        return gameConfiguration;
    }

    private void askForLanguage() {
        userOutput.accept("Wybierz jezyk: "); //todo obsluga wprowadzania jezykow
        gameConfiguration.setLanguage("pl");

    }

    private void askForBoardSettings() {
        userOutput.accept("Podaj rozmiary stolu: "); //todo obsluga rozmiarow
//        Integer sizeX = 3;
//        Integer sizeY = 3;
//        Integer charsToWin = 3;
//        gameConfiguration.setBoardSettings(sizeX,sizeY,charsToWin); //todo catch InvalidParameterException
    }
}
