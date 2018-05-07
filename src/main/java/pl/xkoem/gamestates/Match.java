package pl.xkoem.gamestates;

import pl.xkoem.*;

import java.util.HashMap;
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
    int counter;
    private int MATCHES_ON_GAME = 3;

    public Match(Supplier<String> userInput, Consumer<String> userOutput, Players players, GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        this.players = players;
        this.userInput = userInput;
        this.userOutput = userOutput;
        counter = 0;
    }

    public void run(Player player, DashBoard dashBoard) {
        counter++;
        GameBoard gameBoard = new GameBoard(gameConfiguration,userOutput);
        Judge judge = new Judge(gameBoard.boardSize(), gameConfiguration.getSymbolsToWin());
        do {
            Turn turn = new Turn(userOutput, userInput, player, gameBoard);
            turn.run();
            player = players.getOppositePlayer(player);
            if (turn.isGameQuit()) {
                counter = MATCHES_ON_GAME;
                return;
            }
        } while(! (judge.checkNewPosition(gameBoard)
                || judge.isMatchFinished()));

        gameBoard.drawBoard();
        if(!judge.checkNewPosition(gameBoard)) {
            dashBoard.addDrawPoints();
            userOutput.accept("Remis " + getPoints(dashBoard));
        } else {
            Player winner = players.getPlayer(gameBoard.getSymbolAtPosition(gameBoard.getNewestPosition()));
            dashBoard.addPointsToWinner(winner);
            userOutput.accept("Wygrywa " + winner.getSymbol() + ". " + getPoints(dashBoard));
        }

    }

    String getPoints(DashBoard dashBoard) {
        HashMap<Symbol, Integer> points = dashBoard.getPoints();
        return "O: " + points.get(Symbol.O) + " X: " + points.get(Symbol.X);
    }

    public boolean isFinished() {
        return counter == MATCHES_ON_GAME;
    }

}
