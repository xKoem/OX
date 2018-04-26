package pl.xkoem.gamestates;

import pl.xkoem.*;

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

    public void begin(Player player, DashBoard dashBoard) {
        counter++;
        GameBoard gameBoard = new GameBoard(gameConfiguration,userOutput);
        Judge judge = new Judge(gameBoard.boardSize());
        do {
            player = players.getOppositePlayer(player);
            Turn turn = new Turn(userOutput, userInput, player, gameBoard);
            turn.run();
            judge.checkNewPosition(gameBoard);
        }while(!judge.isMatchFinished());

        Player winner = player; //todo set real winner
        dashBoard.addPointsToWinner(player);
        userOutput.accept("Koniec meczu. Zwyciezca: " + player.getName());
    }

    public boolean isFinished() {
        return counter == 3;
    }

}
