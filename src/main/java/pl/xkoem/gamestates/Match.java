package pl.xkoem.gamestates;

import pl.xkoem.DashBoard;
import pl.xkoem.GameBoard;
import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;

import java.util.function.Consumer;
import java.util.function.Supplier;



public class Match {
    /**
     * Zarządza każdą partią. Musi być stworzona osobno dla każdej parti. Dzięki temu pozwala na użycie z rożnymi parametrami rozgrywki
     * przy użyciu
     * @param gameConfiguration
     */

    Supplier<String> userInput;
    Consumer<String> userOutput;
    Players players;
    GameConfiguration gameConfiguration;
    Integer counter;


    public Match(Supplier<String> userInput, Consumer<String> userOutput, Players players, GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        this.players = players;
        this.userInput = userInput;
        this.userOutput = userOutput;
        counter = 0;
    }

    public void begin() {
        userOutput.accept("Match began");
        counter++;
        GameBoard gameBoard = new GameBoard(gameConfiguration,userOutput);
        gameBoard.drawBoard();
    }

    public boolean isFinished() {
        return counter == 3;
    }

    public DashBoard getResults() {
        return new DashBoard();
    }
}
