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
        userInterface.accept(LanguageName.player_name_question, new ReplacePattern("symbol", player));
        String playerName = "";
        while (playerName.length() == 0) {
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
            userInterface.accept(LanguageName.starting_player_question);
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
            userInterface.accept(LanguageName.symbols_amount_question);
            String userData = userInterface.get();
            symbolsToWin = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinSymbolsToWin() , maxSymbolsToWin);
        }
        return symbolsToWin.get();
    }

    private int askForWidth() {
        Optional<Integer> width = Optional.empty();
        while(gameConfiguration.getMinX() > width.orElse(0)) {
            userInterface.accept(LanguageName.width_question);
            String userData = userInterface.get();
            width = tryChangeStringToIntWithMinValue(userData, gameConfiguration.getMinX());
        }
        return width.get();
    }

    private int askForHeight() {
        Optional<Integer> height = Optional.empty();
        while(gameConfiguration.getMinY() > height.orElse(0)) {
            userInterface.accept(LanguageName.height_question);
            String userData = userInterface.get();
            height = tryChangeStringToIntWithMinValue(userData, gameConfiguration.getMinY());
        }
        return height.get();
    }

    Optional<Integer> tryChangeStringToIntWithMinValue(String string, int minValue) {
        Optional<Integer> value = tryChangeStringToInt(string);
        if (!value.isPresent()) {
            return value;
        }
        if(value.get() < minValue) {
            userInterface.accept(LanguageName.too_low_number);
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
            userInterface.accept(LanguageName.too_low_number);
            return Optional.empty();
        }
        if(value.get() > maxValue) {
            userInterface.accept(LanguageName.too_high_number);
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
            userInterface.accept(LanguageName.bad_parameter_should_be_number);
            return value;
        }
        return value;
    }

}
