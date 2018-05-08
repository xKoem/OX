package pl.xkoem.gamestates;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.xkoem.DashBoard;
import pl.xkoem.FileReader;
import pl.xkoem.GameConfiguration;
import pl.xkoem.Players;
import pl.xkoem.userinterface.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Test
public class MatchTest {

    Match match;
    private ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        match = new Match(new UserInterface(new Scanner(System.in)::nextLine, System.out::println,
                FileReader.readLanguageFile("PL", System.out::println)), new Players(), new GameConfiguration());
    }

    public void checkEmptyPoints() {
        Players players = new Players();
        players.setPlayers("","");
        Assert.assertEquals(match.getPoints(new DashBoard(players)), "O: 0 X: 0");
    }
}
