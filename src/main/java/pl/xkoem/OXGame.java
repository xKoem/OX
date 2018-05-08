package pl.xkoem;

import pl.xkoem.gamestates.EndOfGame;
import pl.xkoem.gamestates.Init;
import pl.xkoem.gamestates.Match;
import pl.xkoem.userinterface.UserInterface;

public class OXGame {

    private final UserInterface userInterface;
    private Players players;
    private GameConfiguration gameConfiguration;


    public OXGame(Players players, UserInterface userInterface, GameConfiguration gameConfiguration) {
        this.players = players;
        this.userInterface = userInterface;
        this.gameConfiguration = gameConfiguration;
    }



    /**
     * Główna metoda uruchamiająca cały proces gry po kolei
     */
    void run() {
        initGame();
        Match match = new Match(userInterface, players, gameConfiguration);
        DashBoard dashBoard = new DashBoard(players);
        do {
            Player player = players.getPlayer(gameConfiguration.getBeginner());
            gameConfiguration.changeBeginner();
            match.run(player, dashBoard);
        } while (!match.isFinished());

        EndOfGame endOfGame = new EndOfGame(userInterface, dashBoard);
        endOfGame.printResults();
    }

    private void initGame() {
        Init init = new Init(userInterface, players, gameConfiguration);
        players = init.askForNames();
        gameConfiguration = init.askForConfiguration(gameConfiguration);
    }


}
