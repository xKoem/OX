package pl.xkoem.gamestates;

import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;
import pl.xkoem.Symbol;
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
        userInterface.accept("Podaj nazwe gracza " + player + ": ");
        String playerName = "";
        while (playerName.length() == 0) {
            playerName = userInterface.get();
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
            userInterface.accept("Podaj kto zaczyna O czy X");
            String userData = userInterface.get();
            if (userData.equals("X") || userData .equals("O")) {
                gameConfiguration.setBeginner(Symbol.valueOf(userData));
                return;
            }
        }
    }

    private void askForLanguage() {
        userInterface.accept("Wybierz jezyk: "); //todo obsluga wprowadzania jezykow
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
            userInterface.accept("Podaj min ilosc symboli do wygrania");
            String userData = userInterface.get();
            symbolsToWin = tryChangeStringToIntBetweenMinAndMaxValue(userData, gameConfiguration.getMinSymbolsToWin() , maxSymbolsToWin);
        }
        return symbolsToWin.get();
    }

    private int askForWidth() {
        Optional<Integer> width = Optional.empty();
        while(gameConfiguration.getMinX() > width.orElse(0)) {
            userInterface.accept("Podaj szerokosc planszy");
            String userData = userInterface.get();
            width = tryChangeStringToIntWithMinValue(userData, gameConfiguration.getMinX());
        }
        return width.get();
    }

    private int askForHeight() {
        Optional<Integer> height = Optional.empty();
        while(gameConfiguration.getMinY() > height.orElse(0)) {
            userInterface.accept("Podaj wysokosc planszy");
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
            userInterface.accept("Za mało ziom");
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
            userInterface.accept("Za mało ziom");
            return Optional.empty();
        }
        if(value.get() > maxValue) {
            userInterface.accept("Za duzo ziom");
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
            userInterface.accept("Bledny parametr, sprobuj jeszcze raz. Nastepnym razem wez wpisz liczbe...");
            return value;
        }
        return value;
    }

}
