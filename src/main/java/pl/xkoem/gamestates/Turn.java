package pl.xkoem.gamestates;

import pl.xkoem.GameBoard;
import pl.xkoem.Player;
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
        userInterface.accept(player.getName() + " podaj pole z na ktorym chcesz postawic znak " + player.getSymbol());
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
            userInterface.accept("Bledne pole, sprobuj jeszcze raz");
            setSymbolAtPositionPosition();
        }
    }

    public boolean isGameQuit() {
        return gameQuit;
    }
}
