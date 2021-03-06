package pl.xkoem.gamestates;

import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;
import pl.xkoem.Symbol;
import pl.xkoem.userinterface.language.LanguageName;
import pl.xkoem.userinterface.ReplacePattern;
import pl.xkoem.userinterface.UserInterface;

import java.util.Optional;

public class Init {

    private final GameConfiguration gameConfiguration;
    private Players players;
    private final UserInterface userInterface;

    public Init(UserInterface userInterface, Players players, GameConfiguration gameConfiguration) {
        this.players = players;
        this.userInterface = userInterface;
        this.gameConfiguration = gameConfiguration;
    }


    public Players askForNames() {
        String playerO = askForName("O");
        String playerX = askForName("X");
        players.setPlayers(playerO, playerX);
        return players;
    }

    private String askForName(String player) {
        userInterface.accept(LanguageName.PLAYER_NAME_QUESTION, new ReplacePattern("symbol", player));
        String playerName = userInterface.get();
        while (playerName.length() == 0) {
            userInterface.accept(LanguageName.EMPTY_PLAYER_NAME);
            playerName = userInterface.get();
        }
        return playerName;
    }
    
    public GameConfiguration askForConfiguration(GameConfiguration gameConfiguration) {
        askForBoardSettings();
        askWhoBegins();
        return gameConfiguration;
    }

    private void askWhoBegins() {
        while(true) {
            userInterface.accept(LanguageName.STARTING_PLAYER_QUESTION);
            String userData = userInterface.get();
            if (userData.equals("X") || userData .equals("O")) {
                gameConfiguration.setBeginner(Symbol.valueOf(userData));
                return;
            }
        }
    }

    private void askForBoardSettings() {
        int width = askForWidth();
        int height = askForHeight();
        int minSymbolsToWin = askForMinSymbolsToWin(Math.min(width, height));
        gameConfiguration.setBoardSettings(width, height, minSymbolsToWin);
    }

    private int askForMinSymbolsToWin(int maxSymbolsToWin) {
        maxSymbolsToWin = Math.max(maxSymbolsToWin, gameConfiguration.getMinSymbolsToWin());
        Optional<Integer> symbolsToWin = Optional.empty();
        while(!symbolsToWin.isPresent()) {
            userInterface.accept(LanguageName.SYMBOLS_AMOUNT_QUESTION);
            String userData = userInterface.get();
            symbolsToWin = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinSymbolsToWin() , maxSymbolsToWin);
        }
        return symbolsToWin.get();
    }

    private int askForWidth() {
        Optional<Integer> width = Optional.empty();
        while(gameConfiguration.getMinX() > width.orElse(0) && width.orElse(Integer.MAX_VALUE) > gameConfiguration.getMAX_X()) {
            userInterface.accept(LanguageName.WIDTH_QUESTION);
            String userData = userInterface.get();
            width = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinX(),gameConfiguration.getMAX_X());
        }
        return width.get();
    }

    private int askForHeight() {
        Optional<Integer> height = Optional.empty();
        while(gameConfiguration.getMinY() > height.orElse(0) && height.orElse(Integer.MAX_VALUE) > gameConfiguration.getMAX_Y()) {
            userInterface.accept(LanguageName.HEIGHT_QUESTION);
            String userData = userInterface.get();
            height = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinY(), gameConfiguration.getMAX_Y());
        }
        return height.get();
    }

    Optional<Integer> tryChangeStringToIntWithMinValue(String string, int minValue) {
        Optional<Integer> value = tryChangeStringToInt(string);
        if (!value.isPresent()) {
            return value;
        }
        if(value.get() < minValue) {
            userInterface.accept(LanguageName.TOO_LOW_NUMBER);
            return Optional.empty();
        }
        return value;

    }

    Optional<Integer> tryChangeStringToIntBetweenMinAndMaxValue(String string, int minValue, int maxValue) {
        Optional<Integer> value = tryChangeStringToInt(string);
        if (!value.isPresent()) {
            return value;
        }
        if(value.get() < minValue) {
            userInterface.accept(LanguageName.TOO_LOW_NUMBER);
            return Optional.empty();
        }
        if(value.get() > maxValue) {
            userInterface.accept(LanguageName.TOO_HIGH_NUMBER);
            return Optional.empty();
        }
        return value;
    }

    Optional<Integer> tryChangeStringToInt (String string) {
        Optional<Integer> value = Optional.empty();
        try {
            Integer intValue = Integer.valueOf(string);
            value = Optional.of(intValue);
        } catch (NumberFormatException e) {
            userInterface.accept(LanguageName.BAD_PARAMETER_SHOULD_BE_NUMBER);
            return value;
        }
        return value;
    }

}
