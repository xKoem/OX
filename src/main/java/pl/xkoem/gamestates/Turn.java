package pl.xkoem.gamestates;

import pl.xkoem.GameBoard;
import pl.xkoem.Player;

import java.security.InvalidParameterException;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Turn {
    private Consumer<String> userOutput;
    private Supplier<String> userInput;
    private Player player;
    private GameBoard gameBoard;
    private Integer newestPosition;

    Turn(Consumer<String> userOutput, Supplier<String> userInput, Player player, GameBoard gameBoard) {
        this.userOutput = userOutput;
        this.userInput = userInput;
        this.player = player;
        this.gameBoard = gameBoard;
    }


    void run() {
        gameBoard.drawBoard();
        setSymbolAtPositionPosition();
    }

    private void setSymbolAtPositionPosition() {
        userOutput.accept(player.getName() + " podaj pole z na ktorym chcesz postawic znak " + player.getSymbol());
        String playerOutputPosition = userInput.get();
        try {
            Integer position = Integer.valueOf(playerOutputPosition);
            gameBoard.setSymbolAtPosition(player.getSymbol(), position);
            newestPosition = position;
        } catch (InvalidParameterException|NumberFormatException e) {
            userOutput.accept("Bledne pole, sprobuj jeszcze raz");
            setSymbolAtPositionPosition();
        }
    }
}
