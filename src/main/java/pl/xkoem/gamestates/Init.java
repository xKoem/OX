package pl.xkoem.gamestates;

import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;
import pl.xkoem.Symbol;

import java.util.Optional;
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
        String playerO = askForName("O");
        String playerX = askForName("X");
        players.setPlayers(playerO, playerX);
        return players;
    }

    private String askForName(String player) {
        userOutput.accept("Podaj nazwe gracza " + player + ": ");
        String playerName = "";
        while (playerName.length() == 0) {
            playerName = userInput.get();
        }
        return playerName;
    }
    
    public GameConfiguration askForConfiguration(GameConfiguration gameConfiguration) {
        askForBoardSettings();
        askForLanguage();
        askWhoBegins();
        return gameConfiguration;
    }

    private void askWhoBegins() {
        while(true) {
            userOutput.accept("Podaj kto zaczyna O czy X");
            String userData = userInput.get();
            if (userData.equals("X") || userData .equals("O")) {
                gameConfiguration.setBeginner(Symbol.valueOf(userData));
                return;
            }
        }
    }

    private void askForLanguage() {
        userOutput.accept("Wybierz jezyk: "); //todo obsluga wprowadzania jezykow
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
            userOutput.accept("Podaj min ilosc symboli do wygrania");
            String userData = userInput.get();
            symbolsToWin = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinSymbolsToWin() , maxSymbolsToWin);
        }
        return symbolsToWin.get();
    }

    private int askForWidth() {
        Optional<Integer> width = Optional.empty();
        while(gameConfiguration.getMinX() > width.orElse(0)) {
            userOutput.accept("Podaj szerokosc planszy");
            String userData = userInput.get();
            width = tryChangeStringToIntWithMinValue(userData, gameConfiguration.getMinX());
        }
        return width.get();
    }

    private int askForHeight() {
        Optional<Integer> height = Optional.empty();
        while(gameConfiguration.getMinY() > height.orElse(0)) {
            userOutput.accept("Podaj wysokosc planszy");
            String userData = userInput.get();
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
            userOutput.accept("Za mało ziom");
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
            userOutput.accept("Za mało ziom");
            return Optional.empty();
        }
        if(value.get() > maxValue) {
            userOutput.accept("Za duzo ziom");
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
            userOutput.accept("Bledny parametr, sprobuj jeszcze raz. Nastepnym razem wez wpisz liczbe...");
            return value;
        }
        return value;
    }

}
