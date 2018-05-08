package pl.xkoem.userinterface.language;

public enum LanguageName {
    PLAYER_NAME_QUESTION("Select player name %symbol%"),
    STARTING_PLAYER_QUESTION("Who goes first O or X?"),
    WIDTH_QUESTION("Specify board width"),
    HEIGHT_QUESTION("Specify board height"),
    SYMBOLS_AMOUNT_QUESTION("Specify minimal amount of symbols to win"),
    BAD_PARAMETER_SHOULD_BE_NUMBER("Bad parameter, try again. Next time try to use a number..."),
    TOO_LOW_NUMBER("Too low value"),
    TOO_HIGH_NUMBER("Too high value"),
    PLAYER_MOVE("%player% where do you wanna place your %symbol%?"),
    BAD_POSITION("Bad position. Try again."),
    MATCH_DRAW("Draw %points%"),
    MATCH_WINNER("Winner %player%. %points%"),
    GAME_WINNER("Winner: %winner% points: %winner_points% \nLoser: %loser% points: %loser_points%\n"),
    GAME_DRAW("Draw! Points: %points%");

    private String value;

    LanguageName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
