package pl.xkoem.gamestates;

import pl.xkoem.*;
import pl.xkoem.board.GameBoard;
import pl.xkoem.userinterface.language.LanguageName;
import pl.xkoem.userinterface.ReplacePattern;
import pl.xkoem.userinterface.UserInterface;

import java.util.HashMap;



public class Match {
    /**
     * Zarządza każdą partią. Musi być stworzona osobno dla każdej parti. Dzięki temu pozwala na użycie z rożnymi parametrami rozgrywki
     * przy użyciu
     * @param gameConfiguration
     */
    private final UserInterface userInterface;
    Players players;
    GameConfiguration gameConfiguration;
    int counter;
    private int MATCHES_ON_GAME = 3;

    public Match(UserInterface userInterface, Players players, GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        this.players = players;
        this.userInterface = userInterface;
        counter = 0;
    }

    public void run(Player player, DashBoard dashBoard) {
        counter++;
        GameBoard gameBoard = new GameBoard(gameConfiguration, userInterface);
        Judge judge = new Judge(gameBoard.getBoardSize(), gameConfiguration.getSymbolsToWin());
        do {
            Turn turn = new Turn(userInterface, player, gameBoard);
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
            userInterface.accept(LanguageName.MATCH_DRAW, new ReplacePattern("points", getPoints(dashBoard)));
        } else {
            Player winner = players.getPlayer(gameBoard.getSymbolAtPosition(gameBoard.getNewestPosition()));
            dashBoard.addPointsToWinner(winner);
            userInterface.accept(LanguageName.MATCH_WINNER, new ReplacePattern("player", winner.getSymbol().toString()), new ReplacePattern("points", getPoints(dashBoard)));
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
