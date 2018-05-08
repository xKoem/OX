package pl.xkoem.gamestates;

import pl.xkoem.GameBoard;
import pl.xkoem.Player;
import pl.xkoem.userinterface.language.LanguageName;
import pl.xkoem.userinterface.ReplacePattern;
import pl.xkoem.userinterface.UserInterface;

import java.security.InvalidParameterException;

class Turn {
    private static UserInterface userInterface;
    private Player player;
    private GameBoard gameBoard;
    private int newestPosition;
    private boolean gameQuit;

    Turn(UserInterface userInterface, Player player, GameBoard gameBoard) {
        this.userInterface = userInterface;
        this.player = player;
        this.gameBoard = gameBoard;
        gameQuit = false;
    }

    void run() {
        gameBoard.drawBoard();
        setSymbolAtPositionPosition();
    }

    private void setSymbolAtPositionPosition() {
        userInterface.accept(LanguageName.PLAYER_MOVE, new ReplacePattern("player", player.getName()), new ReplacePattern("symbol", player.getSymbol().toString()));
        String playerOutputPosition = userInterface.get();
        if (playerOutputPosition.equals("exit")) {
            gameQuit = true;
            return;
        }

        try {
            int position = Integer.valueOf(playerOutputPosition);
            gameBoard.setSymbolAtPosition(player.getSymbol(), position);
            newestPosition = position;
        } catch (InvalidParameterException|NumberFormatException e) {
            userInterface.accept(LanguageName.BAD_POSITION);
            setSymbolAtPositionPosition();
        }
    }

    public boolean isGameQuit() {
        return gameQuit;
    }
}
