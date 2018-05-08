package pl.xkoem.userinterface.language;

public enum LanguageName {
    player_name_question("Select player name %symbol%"),
    starting_player_question("Who goes first O or X?"),
    width_question("Specify board width"),
    height_question("Specify board height"),
    symbols_amount_question("Specify minimal amount of symbols to win"),
    bad_parameter_should_be_number("Bad parameter, try again. Next time try to use a number..."),
    too_low_number("Too low value"),
    too_high_number("Too high value"),
    player_move("%player% where do you wanna place your %symbol%?"),
    bad_position("Bad position. Try again."),
    match_draw("Draw %points%"),
    match_winner("Winner %player%. %points%"),
    game_winner("Winner: %winner% points: %winner_points% \nLoser: %loser% points: %loser_points%\n"),
    game_draw("Draw! Points: %points%");

    private String value;

    LanguageName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
